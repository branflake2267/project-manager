package org.gonevertical.pm.directory.client.application.widgets.category.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.CategoryJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;
import org.gonevertical.pm.directory.client.rest.util.RestList;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.Change;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.ChildTreeStoreBinding;
import com.sencha.gxt.data.shared.loader.TreeLoader;

public class CategoryListPresenter extends PresenterWidget<CategoryListPresenter.MyView> implements
    CategoryListUiHandlers {

  public interface MyView extends View, HasUiHandlers<CategoryListUiHandlers> {
    void initTreeGrid(TreeStore<CategoryJso> treeStore, TreeLoader<CategoryJso> loader,
        CategoryProperties categoryProperties);

    void add(CategoryJso categoryJso);
  }

  private final CategoryJsoDao categoryJsoDao;
  private final CategoryProperties categoryProperties;
  
  private boolean initialized;
  private TreeStore<CategoryJso> treeStore;

  @Inject
  public CategoryListPresenter(EventBus eventBus, MyView view, LoginPresenter loginPresenter,
      ArchetypeJsoDao archetypeJsoDao, CategoryProperties categoryProperties, CategoryJsoDao categoryJsoDao) {
    super(eventBus, view);

    this.categoryProperties = categoryProperties;
    this.categoryJsoDao = categoryJsoDao;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    if (!initialized) {
      initialized = true;

      TreeStore<CategoryJso> treeStore = createTreeStore();
      TreeLoader<CategoryJso> loader = createLoader(treeStore);

      getView().initTreeGrid(treeStore, loader, categoryProperties);
    }
  }

  private TreeStore<CategoryJso> createTreeStore() {
    treeStore = new TreeStore<CategoryJso>(categoryProperties.key());
    return treeStore;
  }

  private TreeLoader<CategoryJso> createLoader(TreeStore<CategoryJso> treeStore) {
    RpcProxy<CategoryJso, List<CategoryJso>> rpcProxy = new RpcProxy<CategoryJso, List<CategoryJso>>() {
      @Override
      public void load(CategoryJso parent, AsyncCallback<List<CategoryJso>> callback) {
        fetchCategories(parent, callback);
      }
    };

    final TreeLoader<CategoryJso> loader = new TreeLoader<CategoryJso>(rpcProxy) {
      @Override
      public boolean hasChildren(CategoryJso parent) {
        boolean hasChildren = false;
        if (parent.hasChildren() != null) {
          hasChildren = true;
        }
        return hasChildren;
      }
    };
    loader.addLoadHandler(new ChildTreeStoreBinding<CategoryJso>(treeStore));

    return loader;
  }

  private void fetchCategories(CategoryJso parent, final AsyncCallback<List<CategoryJso>> callback) {
    HashMap<String, String> parameters = new HashMap<String, String>();
    if (parent != null) {
      parameters.put("parentKey", parent.getKey());
    }

    categoryJsoDao.getList(parameters, new RestListHandler<CategoryJso>() {
      @Override
      public void onSuccess(RestList<CategoryJso> result) {
        List<CategoryJso> list = new ArrayList<CategoryJso>();
        if (result.getList() != null) {
          list = result.getList();
        }
        callback.onSuccess(list);
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void createNew() {
    CategoryJso jso = JavaScriptObject.createObject().cast();
    jso.setName("New Category");
    categoryJsoDao.put(jso, new RestHandler<CategoryJso>() {
      @Override
      public void onSuccess(CategoryJso categoryJso) {
        getView().add(categoryJso);
      }
      
      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void save() {
    Collection<Store<CategoryJso>.Record> records = treeStore.getModifiedRecords();
    
    for (Store<CategoryJso>.Record record : records) {
      Collection<Change<CategoryJso, ?>> changes = record.getChanges();
      String value = (String) changes.iterator().next().getValue();
      CategoryJso category = record.getModel();
      category.setName(value);
      save(category);
      record.commit(true);
    }
    
  }

  private void save(CategoryJso category) {
    categoryJsoDao.put(category, new RestHandler<CategoryJso>() {
      @Override
      public void onSuccess(CategoryJso object) {
        treeStore.update(object);
      }
      
      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

}

package org.gonevertical.pm.directory.client.application.widgets.category.list;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CategoryListPresenter extends PresenterWidget<CategoryListPresenter.MyView> implements
    CategoryListUiHandlers {

  public interface MyView extends View, HasUiHandlers<CategoryListUiHandlers> {
  }

  // private final CategoryJsoDao categoryJsoDao;
  private final CategoryProperties categoryProperties;

  private boolean initialized;

  @Inject
  public CategoryListPresenter(EventBus eventBus, MyView view, LoginPresenter loginPresenter,
      ArchetypeJsoDao archetypeJsoDao, CategoryProperties categoryProperties) {
    super(eventBus, view);
    
    this.categoryProperties = categoryProperties;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();
    //
    // if (initialized) {
    // initialized = true;
    //
    // TreeStore<CategoryJso> treeStore = createTreeStore();
    // TreeLoader<CategoryJso> loader = createLoader(treeStore);
    //
    // getView().initTreeGrid(treeStore, loader);
    // }
  }

  //
  // private TreeStore<CategoryJso> createTreeStore() {
  // TreeStore<CategoryJso> treeStore = new TreeStore<CategoryJso>(categoryProperties.key());
  // return treeStore;
  // }
  //
  // private TreeLoader<CategoryJso> createLoader(TreeStore<CategoryJso> treeStore) {
  // RpcProxy<CategoryJso, List<CategoryJso>> rpcProxy = new RpcProxy<CategoryJso, List<CategoryJso>>() {
  // @Override
  // public void load(CategoryJso parent, AsyncCallback<List<CategoryJso>> callback) {
  // fetchCategories(parent, callback);
  // }
  // };
  //
  // final TreeLoader<CategoryJso> loader = new TreeLoader<CategoryJso>(rpcProxy) {
  // @Override
  // public boolean hasChildren(CategoryJso parent) {
  // if (parent.hasChildren() != null && parent.hasChildren()) {
  // return true;
  // }
  // return false;
  // }
  // };
  // loader.addLoadHandler(new ChildTreeStoreBinding<CategoryJso>(treeStore));
  //
  // return loader;
  // }
  //
  // private void fetchCategories(CategoryJso parent, final AsyncCallback<List<CategoryJso>> callback) {
  // HashMap<String, String> parameters = new HashMap<String, String>();
  // if (parent != null) {
  // parameters.put("parentKey", parent.getKey());
  // }
  //
  // categoryJsoDao.getList(parameters, new RestListHandler<CategoryJso>() {
  // @Override
  // public void onSuccess(RestList<CategoryJso> result) {
  // callback.onSuccess(result.getList());
  // }
  //
  // @Override
  // public void onFailure(Throwable e) {
  // e.printStackTrace();
  // }
  // });
  // }

}

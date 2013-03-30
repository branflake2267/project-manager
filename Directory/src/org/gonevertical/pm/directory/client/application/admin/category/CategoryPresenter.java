package org.gonevertical.pm.directory.client.application.admin.category;

import java.util.HashMap;
import java.util.List;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.rest.CategoryJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.TreeLoader;

public class CategoryPresenter extends PresenterWidget<CategoryPresenter.MyView> implements CategoryUiHandlers {

  public interface MyView extends View, HasUiHandlers<CategoryUiHandlers> {
  }

  private final CategoryJsoDao categoryJsoDao;
  private final CategoryProperties categoryProperties;

  @Inject
  public CategoryPresenter(EventBus eventBus, MyView view, LoginPresenter loginPresenter,
      CategoryJsoDao categoryJsoDao, CategoryProperties categoryProperties) {
    super(eventBus, view);

    this.categoryJsoDao = categoryJsoDao;
    this.categoryProperties = categoryProperties;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    createTreeStore();
  }

  private void createTreeStore() {
    TreeStore<CategoryJso> listStore = new TreeStore<CategoryJso>(categoryProperties.key());

    RpcProxy<CategoryJso, List<CategoryJso>> rpcProxy = new RpcProxy<CategoryJso, List<CategoryJso>>() {
      @Override
      public void load(CategoryJso loadConfig, AsyncCallback<List<CategoryJso>> callback) {
       
      }
    };

    TreeLoader<CategoryJso> loader = new TreeLoader<CategoryJso>(rpcProxy);
  }

  private void fetchCategories(final AsyncCallback<PagingLoadResult<CategoryJso>> callback) {
    HashMap<String, String> parameters = new HashMap<String, String>();

    categoryJsoDao.getList(parameters, new RestListHandler<ArchetypeJso>() {
      @Override
      public void onSuccess(RestList<ArchetypeJso> list) {
        renderListSuccess(list);
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  private void renderListSuccess(RestList<ArchetypeJso> list) {
    // TODO Auto-generated method stub

  }

}

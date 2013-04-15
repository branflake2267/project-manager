package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeUpdateEvent;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;

public class ArchetypeListPresenter extends PresenterWidget<ArchetypeListPresenter.MyView> implements
    ArchtypeListUiHandlers, ArchetypeUpdateEvent.UpdateArchetypeHandler {

  public interface MyView extends View, HasUiHandlers<ArchtypeListUiHandlers> {
    void init(ListStore<ArchetypeJso> listStore,
        PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> pagingLoader);

    void updateSelected(ArchetypeJso archetypeJso);
  }

  private final ArchetypeObserver archetypeObserver;
  private final ArchetypeJsoDao archetypeJsoDao;
  private PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> pagingLoader;

  private boolean initialized;

  @Inject
  public ArchetypeListPresenter(EventBus eventBus, MyView view, ArchetypeObserver archetypeObserver,
      LoginPresenter loginPresenter, ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);

    this.archetypeObserver = archetypeObserver;
    this.archetypeJsoDao = archetypeJsoDao;
    initialized = false;

    getView().setUiHandlers(this);
  }

  @Override
  public void onBind() {
    super.onBind();

    registerHandler(archetypeObserver.addHandler(ArchetypeUpdateEvent.getType(), this));
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    if (!initialized) {
      initialized = true;

      ListStore<ArchetypeJso> listStore = createListStore();
      pagingLoader = createPagingLoader(listStore);
      getView().init(listStore, pagingLoader);
      
    } else if (pagingLoader != null) {
      pagingLoader.load();
    }
  }

  @Override
  public void gotoEdit(ArchetypeJso selectedArchetype) {
    archetypeObserver.display(selectedArchetype);
  }

  @Override
  public void onArchetypeUpdate(ArchetypeUpdateEvent event) {
    getView().updateSelected(event.get());
  }

  @Override
  public void load() {
    pagingLoader.load();
  }

  private void fetchArchetypes(final int offset, int length,
      final AsyncCallback<PagingLoadResult<ArchetypeJso>> callback) {
    HashMap<String, String> parameters = new HashMap<String, String>();
    parameters.put("offset", Integer.toString(offset));
    parameters.put("limit", Integer.toString(length));

    archetypeJsoDao.getList(parameters, new RestListHandler<ArchetypeJso>() {
      @Override
      public void onSuccess(RestList<ArchetypeJso> list) {
        callback.onSuccess(list.getPageLoadResult(offset));
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  private ListStore<ArchetypeJso> createListStore() {
    ListStore<ArchetypeJso> listStore = new ListStore<ArchetypeJso>(new ModelKeyProvider<ArchetypeJso>() {
      @Override
      public String getKey(ArchetypeJso item) {
        return item.getKey();
      }
    });

    return listStore;
  }

  private PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> createPagingLoader(
      ListStore<ArchetypeJso> listStore) {
    // Data Provider
    RpcProxy<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> rpcProxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<ArchetypeJso>>() {
      @Override
      public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<ArchetypeJso>> callback) {
        fetchArchetypes(loadConfig.getOffset(), loadConfig.getLimit(), callback);
      }
    };

    // Paging Loader
    final PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> pagingLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>>(
        rpcProxy);
    pagingLoader.setRemoteSort(true);
    pagingLoader
        .addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, ArchetypeJso, PagingLoadResult<ArchetypeJso>>(
            listStore));

    return pagingLoader;
  }

}

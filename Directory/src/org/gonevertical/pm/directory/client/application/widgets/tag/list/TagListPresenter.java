package org.gonevertical.pm.directory.client.application.widgets.tag.list;

import java.util.Collection;
import java.util.HashMap;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.events.tag.TagSelectEvent;
import org.gonevertical.pm.directory.client.rest.TagJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.TagJso;
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
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.Store.Change;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;

public class TagListPresenter extends PresenterWidget<TagListPresenter.MyView> implements TagListUiHandlers {

  public interface MyView extends View, HasUiHandlers<TagListUiHandlers> {
    void init(TagProperties tagProperties, ListStore<TagJso> listStore, PagingLoader<PagingLoadConfig, PagingLoadResult<TagJso>> pagingLoader);

    void add(TagJso tagJso);
    
    void edit(boolean edit);
  }

  private final TagJsoDao tagJsoDao;
  private final TagProperties tagProperties;
  private final EventBus eventBus;
  
  private PagingLoader<PagingLoadConfig, PagingLoadResult<TagJso>> pagingLoader;
  private ListStore<TagJso> listStore;
  private boolean initialized;

  @Inject
  public TagListPresenter(EventBus eventBus, MyView view, LoginPresenter loginPresenter, TagJsoDao archetypeJsoDao,
      TagProperties TagProperties, TagJsoDao TagJsoDao) {
    super(eventBus, view);

    this.eventBus = eventBus;
    this.tagProperties = TagProperties;
    this.tagJsoDao = TagJsoDao;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    display();
  }
  
  public void display() {
    if (!initialized) {
      initialized = true;

      ListStore<TagJso> listStore = createListStore();
      pagingLoader = createPagingLoader(listStore);
      getView().init(tagProperties, listStore, pagingLoader);

    } else if (pagingLoader != null) {
      pagingLoader.load();
    }
  }

  public void edit(boolean edit) {
    getView().edit(edit);
  }

  @Override
  public void createNew() {
    TagJso jso = JavaScriptObject.createObject().cast();
    jso.setName("New Tag");
    tagJsoDao.put(jso, new RestHandler<TagJso>() {
      @Override
      public void onSuccess(TagJso tagJso) {
        listStore.add(tagJso);
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void save() {
    Collection<Store<TagJso>.Record> records = listStore.getModifiedRecords();

    for (Store<TagJso>.Record record : records) {
      Collection<Change<TagJso, ?>> changes = record.getChanges();
      String value = (String) changes.iterator().next().getValue();
      TagJso Tag = record.getModel();
      Tag.setName(value);
      save(Tag);
      record.commit(true);
    }
  }
  
  @Override
  public void load() {
    pagingLoader.load();
  }

  private void save(TagJso Tag) {
    tagJsoDao.put(Tag, new RestHandler<TagJso>() {
      @Override
      public void onSuccess(TagJso object) {
        listStore.update(object);
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void setSelected(TagJso selected) {
    try {
      eventBus.fireEvent(new TagSelectEvent(selected));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private ListStore<TagJso> createListStore() {
    ListStore<TagJso> listStore = new ListStore<TagJso>(new ModelKeyProvider<TagJso>() {
      @Override
      public String getKey(TagJso item) {
        return item.getKey();
      }
    });

    return listStore;
  }

  private PagingLoader<PagingLoadConfig, PagingLoadResult<TagJso>> createPagingLoader(ListStore<TagJso> listStore) {
    // Data Provider
    RpcProxy<PagingLoadConfig, PagingLoadResult<TagJso>> rpcProxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<TagJso>>() {
      @Override
      public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<TagJso>> callback) {
        fetchArchetypes(loadConfig.getOffset(), loadConfig.getLimit(), callback);
      }
    };

    // Paging Loader
    final PagingLoader<PagingLoadConfig, PagingLoadResult<TagJso>> pagingLoader = new PagingLoader<PagingLoadConfig, PagingLoadResult<TagJso>>(
        rpcProxy);
    pagingLoader.setRemoteSort(true);
    pagingLoader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, TagJso, PagingLoadResult<TagJso>>(
        listStore));

    return pagingLoader;
  }

  private void fetchArchetypes(final int offset, int length, final AsyncCallback<PagingLoadResult<TagJso>> callback) {
    HashMap<String, String> parameters = new HashMap<String, String>();
    parameters.put("offset", Integer.toString(offset));
    parameters.put("limit", Integer.toString(length));

    tagJsoDao.getList(parameters, new RestListHandler<TagJso>() {
      @Override
      public void onSuccess(RestList<TagJso> list) {
        callback.onSuccess(list.getPageLoadResult(offset));
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

}

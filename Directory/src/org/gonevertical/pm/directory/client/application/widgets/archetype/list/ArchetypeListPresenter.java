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
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public class ArchetypeListPresenter extends PresenterWidget<ArchetypeListPresenter.MyView> implements
    ArchtypeListUiHandlers, ArchetypeUpdateEvent.UpdateArchetypeHandler {

  public interface MyView extends View, HasUiHandlers<ArchtypeListUiHandlers> {
    void init();

    void updateSelected(ArchetypeJso archetypeJso);
  }

  private final ArchetypeObserver archetypeObserver;
  private final ArchetypeJsoDao archetypeJsoDao;

  @Inject
  public ArchetypeListPresenter(EventBus eventBus, MyView view, ArchetypeObserver archetypeObserver,
      LoginPresenter loginPresenter, ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);

    this.archetypeObserver = archetypeObserver;
    this.archetypeJsoDao = archetypeJsoDao;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    registerHandler(archetypeObserver.addHandler(ArchetypeUpdateEvent.getType(), this));
  }

  @Override
  public void onReveal() {
    super.onReveal();

    getView().init();
  }

  @Override
  public void gotoEdit(ArchetypeJso selectedArchetype) {
    archetypeObserver.display(selectedArchetype);
  }

  @Override
  public void fetchArchetypes(final int offset, int length, final AsyncCallback<PagingLoadResult<ArchetypeJso>> callback) {
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

  @Override
  public void onArchetypeUpdate(ArchetypeUpdateEvent event) {
    getView().updateSelected(event.get());
  }

}

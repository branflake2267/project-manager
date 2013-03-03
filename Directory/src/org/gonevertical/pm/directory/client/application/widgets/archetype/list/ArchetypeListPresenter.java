package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ArchetypeListPresenter extends PresenterWidget<ArchetypeListPresenter.MyView> implements
    ArchtypeListUiHandlers {

  public interface MyView extends View, HasUiHandlers<ArchtypeListUiHandlers> {
    void displayArchetypes(int start, RestList<ArchetypeJso> list);

    void init();
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
  public void onReveal() {
    super.onReveal();

    getView().init();
  }

  @Override
  public void gotoEdit(ArchetypeJso selectedArchetype) {
    archetypeObserver.display(selectedArchetype);
  }

  @Override
  public void fetchArchetypes(final int start, int length) {
    HashMap<String, String> parameters = new HashMap<String, String>();
    parameters.put("offset", Integer.toString(start));
    parameters.put("limit", Integer.toString(length));

    archetypeJsoDao.getList(parameters, new RestListHandler<ArchetypeJso>() {
      @Override
      public void onSuccess(RestList<ArchetypeJso> list) {
        onFetchSuccessArchetypesList(start, list);
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  private void onFetchSuccessArchetypesList(int start, RestList<ArchetypeJso> list) {
    getView().displayArchetypes(start, list);
  }

}

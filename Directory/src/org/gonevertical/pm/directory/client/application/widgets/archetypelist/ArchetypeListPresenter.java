package org.gonevertical.pm.directory.client.application.widgets.archetypelist;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
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

  private ArchetypeJsoDao archetypeJsoDao;

  public interface MyView extends View, HasUiHandlers<ArchtypeListUiHandlers> {
    void displayArchetypes(int start, RestList<ArchetypeJso> list);

    void init();
  }

  @Inject
  public ArchetypeListPresenter(final EventBus eventBus, final MyView view, final LoginPresenter loginPresenter,
      final ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);

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
    // TODO
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

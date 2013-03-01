package org.gonevertical.pm.directory.client.application.widgets.archetypelist;

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
  }

  @Inject
  public ArchetypeListPresenter(final EventBus eventBus, final MyView view, final LoginPresenter loginPresenter,
      final ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);
    
    this.archetypeJsoDao = archetypeJsoDao;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    fetchArchetypes();
  }

  private void fetchArchetypes() {
    archetypeJsoDao.getList(new RestListHandler<ArchetypeJso>() {
      @Override
      public void onSuccess(RestList<ArchetypeJso> list) {
        onFetchSuccessArchetypesList(list);
      }
      
      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  private void onFetchSuccessArchetypesList(RestList<ArchetypeJso> list) {
    
    System.out.println("test");
  }

}

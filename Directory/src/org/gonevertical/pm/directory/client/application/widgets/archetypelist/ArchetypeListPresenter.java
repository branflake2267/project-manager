package org.gonevertical.pm.directory.client.application.widgets.archetypelist;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ArchetypeListPresenter extends PresenterWidget<ArchetypeListPresenter.MyView> implements ArchtypeListUiHandlers {
  
  public interface MyView extends View, HasUiHandlers<ArchtypeListUiHandlers> {
  }

  @Inject
  public ArchetypeListPresenter(final EventBus eventBus, final MyView view, final LoginPresenter loginPresenter) {
    super(eventBus, view);

    getView().setUiHandlers(this);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    fetchArchetypes();
  }

  private void fetchArchetypes() {
    
  }
  
}

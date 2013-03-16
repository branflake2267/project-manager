package org.gonevertical.pm.directory.client.application.widgets.header;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.events.login.LoggedInEvent;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView> implements HeaderUiHandlers,
    LoggedInEvent.LoggedInHandler {

  public interface MyView extends View, HasUiHandlers<HeaderUiHandlers> {
    public void setAdmin(boolean visible);
  }

  public static final Object TYPE_LoginPresenter = new Object();

  private final LoginPresenter loginPresenter;
  private final LoggedInUser loggedInUser;

  @Inject
  public HeaderPresenter(EventBus eventBus, MyView view, LoginPresenter loginPresenter, LoggedInUser loggedInUser) {
    super(eventBus, view);

    this.loginPresenter = loginPresenter;
    this.loggedInUser = loggedInUser;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    registerHandler(getEventBus().addHandler(LoggedInEvent.getType(), this));

    setInSlot(TYPE_LoginPresenter, loginPresenter);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    displayAdmin();
  }

  @Override
  public void onLoggedIn(LoggedInEvent event) {
    displayAdmin();
  }
  
  private void displayAdmin() {
    getView().setAdmin(loggedInUser.getIsAdmin());    
  }

}

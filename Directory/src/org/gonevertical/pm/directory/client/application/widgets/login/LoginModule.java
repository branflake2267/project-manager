package org.gonevertical.pm.directory.client.application.widgets.login;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class LoginModule extends AbstractPresenterModule {
  
  @Override
  protected void configure() {
    bindPresenterWidget(LoginPresenter.class, LoginPresenter.MyView.class, LoginView.class);
  }
  
}

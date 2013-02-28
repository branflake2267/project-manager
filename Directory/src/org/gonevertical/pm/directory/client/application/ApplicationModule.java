package org.gonevertical.pm.directory.client.application;

import org.gonevertical.pm.directory.client.application.admin.AdminModule;
import org.gonevertical.pm.directory.client.application.home.HomeModule;
import org.gonevertical.pm.directory.client.application.widgets.WidgetsModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
  
  @Override
  protected void configure() {
    install(new WidgetsModule());
    install(new HomeModule());
    install(new AdminModule());

    bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
        ApplicationPresenter.MyProxy.class);
  }
  
}

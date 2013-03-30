package org.gonevertical.pm.directory.client.application.admin;

import org.gonevertical.pm.directory.client.application.admin.category.CategoryModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AdminModule extends AbstractPresenterModule {
  @Override
  protected void configure() {
    install(new CategoryModule());
    bindPresenter(AdminPresenter.class, AdminPresenter.MyView.class, AdminView.class, AdminPresenter.MyProxy.class);
  }
}

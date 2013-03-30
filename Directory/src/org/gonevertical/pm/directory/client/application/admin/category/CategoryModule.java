package org.gonevertical.pm.directory.client.application.admin.category;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CategoryModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(CategoryPresenter.class, CategoryPresenter.MyView.class, CategoryView.class);
  }
  
}

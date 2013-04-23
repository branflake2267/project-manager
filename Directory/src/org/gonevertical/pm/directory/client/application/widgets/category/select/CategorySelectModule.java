package org.gonevertical.pm.directory.client.application.widgets.category.select;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CategorySelectModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(CategorySelectPresenter.class, CategorySelectPresenter.MyView.class, CategorySelectView.class);
  }
  
}

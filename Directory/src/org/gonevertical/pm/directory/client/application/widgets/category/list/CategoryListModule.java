package org.gonevertical.pm.directory.client.application.widgets.category.list;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CategoryListModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(CategoryListPresenter.class, CategoryListPresenter.MyView.class, CategoryListView.class);
  }
  
}

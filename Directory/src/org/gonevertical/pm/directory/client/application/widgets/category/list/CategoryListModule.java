package org.gonevertical.pm.directory.client.application.widgets.category.list;

import org.gonevertical.pm.directory.client.application.widgets.category.editor.CategoryListEditor;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CategoryListModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(CategoryListPresenter.class, CategoryListPresenter.MyView.class, CategoryListView.class);
    
    bind(CategoryListEditor.class).in(Singleton.class);
  }
  
}

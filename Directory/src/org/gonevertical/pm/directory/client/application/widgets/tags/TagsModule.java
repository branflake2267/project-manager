package org.gonevertical.pm.directory.client.application.widgets.tags;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TagsModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(TagsPresenter.class, TagsPresenter.MyView.class, TagsView.class);
  }
  
}

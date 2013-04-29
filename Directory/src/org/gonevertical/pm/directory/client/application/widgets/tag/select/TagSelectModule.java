package org.gonevertical.pm.directory.client.application.widgets.tag.select;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TagSelectModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(TagSelectPresenter.class, TagSelectPresenter.MyView.class, TagSelectView.class);
  }
  
}

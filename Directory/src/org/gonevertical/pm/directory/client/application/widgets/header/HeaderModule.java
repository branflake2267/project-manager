package org.gonevertical.pm.directory.client.application.widgets.header;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HeaderModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class);
  }
  
}

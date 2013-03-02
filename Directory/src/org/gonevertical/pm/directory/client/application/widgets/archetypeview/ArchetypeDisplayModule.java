package org.gonevertical.pm.directory.client.application.widgets.archetypeview;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ArchetypeDisplayModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(ArchetypeDisplayPresenter.class, ArchetypeDisplayPresenter.MyView.class, ArchetypeDisplayView.class);
  }
  
}

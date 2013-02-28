package org.gonevertical.pm.directory.client.application.widgets.archetypelist;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ArchetypeListModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(ArchetypeListPresenter.class, ArchetypeListPresenter.MyView.class, ArchetypeListView.class);
  }
  
}

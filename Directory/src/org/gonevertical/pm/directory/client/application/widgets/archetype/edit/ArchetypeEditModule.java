package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ArchetypeEditModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bindPresenterWidget(ArchetypeEditPresenter.class, ArchetypeEditPresenter.MyView.class, ArchetypeEditView.class);
  }
  
}

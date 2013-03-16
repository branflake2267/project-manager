package org.gonevertical.pm.directory.client.rest;

import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RestModule extends AbstractPresenterModule {
  
  @Override
  protected void configure() {
    bind(ArchetypeJsoDao.class).in(Singleton.class);
    bind(CurrentUserJsoDao.class).in(Singleton.class);
  }
  
}

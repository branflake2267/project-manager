package org.gonevertical.pm.directory.client.security;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class SecurityModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(LoggedInUser.class).asEagerSingleton();
    bind(OAuthToken.class).asEagerSingleton();
  }
  
}

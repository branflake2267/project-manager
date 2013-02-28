package org.gonevertical.pm.directory.client.application.widgets;

import org.gonevertical.pm.directory.client.application.widgets.archetypelist.ArchetypeListModule;
import org.gonevertical.pm.directory.client.application.widgets.header.HeaderModule;
import org.gonevertical.pm.directory.client.application.widgets.login.LoginModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WidgetsModule extends AbstractPresenterModule {
  
  @Override
  protected void configure() {
    install(new HeaderModule());
    install(new LoginModule());
    install(new ArchetypeListModule());
  }
  
}

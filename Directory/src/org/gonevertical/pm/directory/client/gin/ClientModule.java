package org.gonevertical.pm.directory.client.gin;

import org.gonevertical.pm.directory.client.application.ApplicationModule;
import org.gonevertical.pm.directory.client.events.EventsModule;
import org.gonevertical.pm.directory.client.place.NameTokens;
import org.gonevertical.pm.directory.client.rest.RestModule;
import org.gonevertical.pm.directory.client.security.SecurityModule;

import com.gwtplatform.mvp.client.annotations.DefaultPlace;
import com.gwtplatform.mvp.client.annotations.ErrorPlace;
import com.gwtplatform.mvp.client.annotations.UnauthorizedPlace;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.gwtplatform.mvp.client.gin.DefaultModule;

/**
 * See more on setting up the PlaceManager on <a
 * href="// See more on: https://github.com/ArcBees/GWTP/wiki/PlaceManager">DefaultModule's > DefaultPlaceManager</a>
 */
public class ClientModule extends AbstractPresenterModule {
  
  @Override
  protected void configure() {
    install(new DefaultModule());
    install(new EventsModule());
    install(new SecurityModule());
    install(new RestModule());
    install(new ApplicationModule());

    // TODO DefaultPlaceManager Places
    bindConstant().annotatedWith(DefaultPlace.class).to(NameTokens.home);
    bindConstant().annotatedWith(ErrorPlace.class).to(NameTokens.home);
    bindConstant().annotatedWith(UnauthorizedPlace.class).to(NameTokens.home);

    // Load and inject CSS resources
    bind(ResourceLoader.class).asEagerSingleton();
  }
  
}

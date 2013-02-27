package org.gonevertical.pm.directory.client.security;

import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;

@Singleton
public class LoggedInGatekeeper implements Gatekeeper {

  private final CurrentUserJso currentUserJso;

  @Inject
  public LoggedInGatekeeper(final CurrentUserJso currentUserJso) {
    this.currentUserJso = currentUserJso;
  }

  @Override
  public boolean canReveal() {
    return currentUserJso.getIsLoggedIn();
  }

}

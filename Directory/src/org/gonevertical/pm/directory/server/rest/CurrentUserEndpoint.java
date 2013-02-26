package org.gonevertical.pm.directory.server.rest;

import org.gonevertical.pm.directory.server.domain.CurrentUser;

import com.google.api.server.spi.config.Api;

@Api(name = "currentuserendpoint", version = "v1")
public class CurrentUserEndpoint {

  public CurrentUser getCurrentUsers() {
    
  }

}

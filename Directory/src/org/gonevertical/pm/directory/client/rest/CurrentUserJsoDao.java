package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestService;
import org.gonevertical.pm.directory.client.security.OAuthToken;

import com.google.inject.Inject;

public class CurrentUserJsoDao extends RestService<CurrentUserJso> {
  
  private static String endpointPath = "_ah/api/currentuserendpoint/v1/currentuser";
  
  @Inject
  public CurrentUserJsoDao(OAuthToken oauth) {
    super(endpointPath, oauth);
  }
  
}

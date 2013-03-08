package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestService;

public class CurrentUserJsoDao extends RestService<CurrentUserJso> {
  private static String endpointPath = "_ah/api/currentuserendpoint/v1/currentuser";
  
  public CurrentUserJsoDao() {
    super(endpointPath);
  }
}

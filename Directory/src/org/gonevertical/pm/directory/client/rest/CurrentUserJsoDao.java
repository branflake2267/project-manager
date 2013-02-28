package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;
import org.gonevertical.pm.directory.client.rest.util.RestService;

public class CurrentUserJsoDao extends RestService<CurrentUserJso> {
  private static String url = "_ah/api/currentuserendpoint/v1/currentuser";
  
  public void get(RestHandler<CurrentUserJso> handler) {
    get(url, handler);
  }
  
}

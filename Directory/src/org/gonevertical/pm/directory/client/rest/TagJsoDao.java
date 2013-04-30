package org.gonevertical.pm.directory.client.rest;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.rest.jso.TagJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;
import org.gonevertical.pm.directory.client.rest.util.RestService;
import org.gonevertical.pm.directory.client.security.OAuthToken;

import com.google.inject.Inject;

public class TagJsoDao extends RestService<TagJso> {
  
  private static String endppointPath = "_ah/api/tagendpoint/" + SystemProperties.VERSION + "/tag";

  @Inject
  public TagJsoDao(OAuthToken oauth) {
    super(endppointPath, oauth);
  }
  
  @Override
  public void get(HashMap<String, String> parameters, RestHandler<TagJso> handler) {
    setEndpointPath(endppointPath);
    super.get(parameters, handler);
  }
  
  @Override
  public void getList(HashMap<String, String> parameters, RestListHandler<TagJso> handler) {
    setEndpointPath(endppointPath);
    super.getList(parameters, handler);
  }

  public void getChildren(HashMap<String, String> parameters, RestListHandler<TagJso> handler) {
    setEndpointPath(endppointPath + "/children/" + parameters.get("parentKey"));
    super.getList(parameters, handler);
  }
  
}

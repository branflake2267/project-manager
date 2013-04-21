package org.gonevertical.pm.directory.client.rest;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;
import org.gonevertical.pm.directory.client.rest.util.RestService;
import org.gonevertical.pm.directory.client.security.OAuthToken;

import com.google.inject.Inject;

public class CategoryJsoDao extends RestService<CategoryJso> {
  
  private static String endppointPath = "_ah/api/categoryendpoint/v1/category";

  @Inject
  public CategoryJsoDao(OAuthToken oauth) {
    super(endppointPath, oauth);
  }
  
  @Override
  public void get(HashMap<String, String> parameters, RestHandler<CategoryJso> handler) {
    setEndpointPath(endppointPath);
    super.get(parameters, handler);
  }
  
  @Override
  public void getList(HashMap<String, String> parameters, RestListHandler<CategoryJso> handler) {
    setEndpointPath(endppointPath);
    super.getList(parameters, handler);
  }

  public void getChildren(HashMap<String, String> parameters, RestListHandler<CategoryJso> handler) {
    setEndpointPath(endppointPath + "/children/" + parameters.get("parentKey"));
    super.getList(parameters, handler);
  }
  
}

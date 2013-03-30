package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestService;
import org.gonevertical.pm.directory.client.security.OAuthToken;

import com.google.inject.Inject;

public class CategoryJsoDao extends RestService<ArchetypeJso> {
  
  private static String endppointPath = "_ah/api/categoryendpoint/v1/category";

  @Inject
  public CategoryJsoDao(OAuthToken oauth) {
    super(endppointPath, oauth);
  }

}

package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestService;
import org.gonevertical.pm.directory.client.security.OAuthToken;

import com.google.inject.Inject;

public class ArchetypeJsoDao extends RestService<ArchetypeJso> {
  
  private static String endppointPath = "_ah/api/archetypeendpoint/" + SystemProperties.VERSION + "/archetype";
  
  @Inject
  public ArchetypeJsoDao(OAuthToken oauth) {
    super(endppointPath, oauth);
  }

}

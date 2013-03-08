package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestService;

public class ArchetypeJsoDao extends RestService<ArchetypeJso> {
  
  private static String endppointPath = "_ah/api/archetypeendpoint/v1/archetype";

  public ArchetypeJsoDao() {
    super(endppointPath);
  }

}

package org.gonevertical.pm.directory.client.rest;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;
import org.gonevertical.pm.directory.client.rest.util.RestService;

public class ArchetypeJsoDao extends RestService<ArchetypeJso> {
  private static String url = "_ah/api/archetypeendpoint/v1/archetype";

  public void getList(RestListHandler<ArchetypeJso> handler) {
    getList(url, handler);
  }

}

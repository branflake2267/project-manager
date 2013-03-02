package org.gonevertical.pm.directory.client.rest;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestListHandler;
import org.gonevertical.pm.directory.client.rest.util.RestService;

public class ArchetypeJsoDao extends RestService<ArchetypeJso> {
  private static String url = "_ah/api/archetypeendpoint/v1/archetype";

  public void getList(HashMap<String, String> parameters, RestListHandler<ArchetypeJso> handler) {
    getList(url, parameters, handler);
  }

}

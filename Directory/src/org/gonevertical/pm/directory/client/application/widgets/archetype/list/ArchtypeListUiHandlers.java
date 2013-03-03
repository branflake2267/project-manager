package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ArchtypeListUiHandlers extends UiHandlers {
  
  void fetchArchetypes(int start, int length);

  void gotoEdit(ArchetypeJso selectedArchetype);
  
}

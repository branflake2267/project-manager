package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ArchtypeListUiHandlers extends UiHandlers {

  void gotoEdit(ArchetypeJso selectedArchetype);

  void load();
  
}

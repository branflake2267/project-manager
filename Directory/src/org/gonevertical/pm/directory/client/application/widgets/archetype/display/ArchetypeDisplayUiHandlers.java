package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ArchetypeDisplayUiHandlers extends UiHandlers {

  void gotoEdit(ArchetypeJso archetypeJso);
  
}

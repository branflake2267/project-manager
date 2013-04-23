package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.gwtplatform.mvp.client.UiHandlers;

public interface ArchetypeEditUiHandlers extends UiHandlers {

  void displayList();

  void save(ArchetypeJso archetypeJso);

  void displayCategoryPopup();
  
}

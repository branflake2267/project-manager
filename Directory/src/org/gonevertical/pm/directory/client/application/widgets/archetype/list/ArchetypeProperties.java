package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;
import org.gonevertical.pm.directory.client.rest.jso.TagJso;

import com.google.gwt.core.client.JsArray;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
 
public interface ArchetypeProperties extends PropertyAccess<ArchetypeJso> {
  
  ModelKeyProvider<ArchetypeJso> key();
 
  ValueProvider<ArchetypeJso, String> name();
  
  ValueProvider<ArchetypeJso, JsArray<CategoryJso>> categories();

  ValueProvider<ArchetypeJso, JsArray<TagJso>> tags();
}
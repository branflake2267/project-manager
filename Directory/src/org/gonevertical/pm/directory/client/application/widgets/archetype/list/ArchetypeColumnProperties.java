package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
 
public interface ArchetypeColumnProperties extends PropertyAccess<ArchetypeJso> {
  
  ModelKeyProvider<ArchetypeJso> key();
 
  ValueProvider<ArchetypeJso, String> name();
  
}
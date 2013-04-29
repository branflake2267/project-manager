package org.gonevertical.pm.directory.client.application.widgets.tag.list;

import org.gonevertical.pm.directory.client.rest.jso.TagJso;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
 
public interface TagProperties extends PropertyAccess<TagJso> {
  
  ModelKeyProvider<TagJso> key();
 
  ValueProvider<TagJso, String> name();
  
}
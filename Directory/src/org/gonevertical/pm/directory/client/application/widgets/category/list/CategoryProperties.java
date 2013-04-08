package org.gonevertical.pm.directory.client.application.widgets.category.list;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
 
public interface CategoryProperties extends PropertyAccess<CategoryJso> {
  
  ModelKeyProvider<CategoryJso> key();
 
  ValueProvider<CategoryJso, String> name();
  
}
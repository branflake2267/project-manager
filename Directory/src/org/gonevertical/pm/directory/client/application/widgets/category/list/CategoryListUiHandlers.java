package org.gonevertical.pm.directory.client.application.widgets.category.list;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.gwtplatform.mvp.client.UiHandlers;

public interface CategoryListUiHandlers extends UiHandlers {

  void createNew();

  void save();

  void setSelected(CategoryJso selected);
  
}

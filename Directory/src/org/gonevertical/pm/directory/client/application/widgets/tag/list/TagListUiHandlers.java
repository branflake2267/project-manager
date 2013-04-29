package org.gonevertical.pm.directory.client.application.widgets.tag.list;

import org.gonevertical.pm.directory.client.rest.jso.TagJso;

import com.gwtplatform.mvp.client.UiHandlers;

public interface TagListUiHandlers extends UiHandlers {

  void createNew();

  void save();

  void setSelected(TagJso selected);

  void load();
  
}

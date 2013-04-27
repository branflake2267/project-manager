package org.gonevertical.pm.directory.client.application.widgets.category.editor;

import org.gonevertical.pm.directory.client.events.category.DeleteEvent;
import org.gonevertical.pm.directory.client.events.category.DeleteEvent.DeleteHandler;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class CategoryItemEditor extends Composite implements Editor<CategoryJso> {

  // UiBinder
  private static CategoryItemEditorUiBinder uiBinder = GWT.create(CategoryItemEditorUiBinder.class);
  interface CategoryItemEditorUiBinder extends UiBinder<Widget, CategoryItemEditor> {}

  public CategoryItemEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public HandlerRegistration addDeleteHandler(DeleteHandler handler) {
    return addHandler(handler, DeleteEvent.getType());
  }

}

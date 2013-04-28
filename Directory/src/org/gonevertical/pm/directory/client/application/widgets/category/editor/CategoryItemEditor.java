package org.gonevertical.pm.directory.client.application.widgets.category.editor;

import org.gonevertical.pm.directory.client.events.category.DeleteDataEvent;
import org.gonevertical.pm.directory.client.events.category.DeleteDataEvent.DeleteHandler;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class CategoryItemEditor extends Composite implements Editor<CategoryJso> {

  // UiBinder
  private static CategoryItemEditorUiBinder uiBinder = GWT.create(CategoryItemEditorUiBinder.class);
  interface CategoryItemEditorUiBinder extends UiBinder<Widget, CategoryItemEditor> {}

   @UiField
   HTML name;

  public CategoryItemEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public HandlerRegistration addDeleteHandler(DeleteHandler handler) {
    return addHandler(handler, DeleteDataEvent.getType());
  }

  @UiHandler("delete")
  void onDeleteSelect(SelectEvent event) {
    DeleteDataEvent.fire(this);
  }

}

package org.gonevertical.pm.directory.client.application.widgets.category.editor;

import org.gonevertical.pm.directory.client.events.category.DeleteEvent;
import org.gonevertical.pm.directory.client.events.category.DeleteEvent.DeleteHandler;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

public class CategoryListEditor extends Composite {

  // ListEditor and item editor manager
  private class ItemEditorSource extends EditorSource<CategoryItemEditor> {
    @Override
    public CategoryItemEditor create(final int index) {
      CategoryItemEditor item = new CategoryItemEditor();
      container.insert(item, index);
      item.addDeleteHandler(new DeleteHandler() {
        public void onDelete(DeleteEvent event) {
          removeItem(index);
        }
      });
      return item;
    }

    @Override
    public void dispose(CategoryItemEditor item) {
      item.removeFromParent();
    }

    @Override
    public void setIndex(CategoryItemEditor item, int index) {
      container.insert(item, index);
    }
  }
  private ListEditor<CategoryJso, CategoryItemEditor> listEditor = ListEditor.of(new ItemEditorSource());

  // UiBinder
  private static CategoryListEditorUiBinder uiBinder = GWT.create(CategoryListEditorUiBinder.class);
  interface CategoryListEditorUiBinder extends UiBinder<Widget, CategoryListEditor> {}
  @UiField
  FlowLayoutContainer container;
  
  public CategoryListEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  private void removeItem(int index) {
    listEditor.getList().remove(index);
  }
}

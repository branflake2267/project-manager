package org.gonevertical.pm.directory.client.application.widgets.category.editor;

import java.util.List;

import org.gonevertical.pm.directory.client.events.category.CategorySelectEvent;
import org.gonevertical.pm.directory.client.events.category.CategorySelectEventHandler;
import org.gonevertical.pm.directory.client.events.category.DeleteDataEvent;
import org.gonevertical.pm.directory.client.events.category.DeleteDataEvent.DeleteHandler;
import org.gonevertical.pm.directory.client.events.category.DisplayCategoryPopupEvent;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class CategoryListEditor extends Composite implements IsEditor<Editor<List<CategoryJso>>> {

  // ListEditor item manager
  private class ItemEditorSource extends EditorSource<CategoryItemEditor> {
    @Override
    public CategoryItemEditor create(final int index) {
      CategoryItemEditor item = new CategoryItemEditor();
      list.insert(item, index);
      item.addDeleteHandler(new DeleteHandler() {
        public void onDelete(DeleteDataEvent event) {
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
      list.insert(item, index);
    }
  }
  private ListEditor<CategoryJso, CategoryItemEditor> listEditor = ListEditor.of(new ItemEditorSource());

  // UiBinder
  private static CategoryListEditorUiBinder uiBinder = GWT.create(CategoryListEditorUiBinder.class);
  public interface CategoryListEditorUiBinder extends UiBinder<Widget, CategoryListEditor> {}
  @UiField
  FlowPanel list;
  
  private EventBus eventBus;
  
  @Inject
  public CategoryListEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
    
    eventBus.addHandler(CategorySelectEvent.TYPE, new CategorySelectEventHandler() {
      @Override
      public void onCategorySelectEvent(CategorySelectEvent event) {
        CategoryJso categoryJso = event.getData();
        List<CategoryJso> l = listEditor.getList();
        l.add(categoryJso);
      }
    });
  }

  private void removeItem(int index) {
    listEditor.getList().remove(index);
  }

  @UiHandler("add")
  void onAddSelect(SelectEvent event) {
    eventBus.fireEvent(new DisplayCategoryPopupEvent());
  }

  @Override
  public Editor<List<CategoryJso>> asEditor() {
    return listEditor;
  }
  
}

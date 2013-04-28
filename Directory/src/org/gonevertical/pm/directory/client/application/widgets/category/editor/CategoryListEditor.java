package org.gonevertical.pm.directory.client.application.widgets.category.editor;

import org.gonevertical.pm.directory.client.events.category.CategorySelectEvent;
import org.gonevertical.pm.directory.client.events.category.CategorySelectEventHandler;
import org.gonevertical.pm.directory.client.events.category.DisplayCategoryPopupEvent;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class CategoryListEditor extends Composite implements LeafValueEditor<JsArray<CategoryJso>> {

  // UiBinder
  private static CategoryListEditorUiBinder uiBinder = GWT.create(CategoryListEditorUiBinder.class);
  public interface CategoryListEditorUiBinder extends UiBinder<Widget, CategoryListEditor> {}
  
  @UiField
  FlowPanel categories;
  
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
        addItem(categoryJso);
      }
    });
  }

  @UiHandler("add")
  void onAddSelect(SelectEvent event) {
    eventBus.fireEvent(new DisplayCategoryPopupEvent());
  }

  @Override
  public void setValue(JsArray<CategoryJso> value) {
    categories.clear();
    
    if (value == null) {
      return;
    }
    for (int i=0; i < value.length(); i++) {
      addItem(value.get(i));
    }
  }

  private void addItem(CategoryJso categoryJso) {
    CategoryItemEditor w = new CategoryItemEditor();
    categories.add(w);
    w.setCategoryJso(categoryJso);
    w.display();
  }

  @Override
  public JsArray<CategoryJso> getValue() {
    JsArray<CategoryJso> a = JavaScriptObject.createArray().cast();
    for (int i=0; i < categories.getWidgetCount(); i++) {
      CategoryItemEditor w = (CategoryItemEditor) categories.getWidget(i);
      a.push(w.getCategoryJso());
    }
    return a;
  }
  
}

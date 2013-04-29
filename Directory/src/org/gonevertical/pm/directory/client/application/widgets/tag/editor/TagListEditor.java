package org.gonevertical.pm.directory.client.application.widgets.tag.editor;

import org.gonevertical.pm.directory.client.events.tag.TagSelectEvent;
import org.gonevertical.pm.directory.client.events.tag.TagSelectEventHandler;
import org.gonevertical.pm.directory.client.rest.jso.TagJso;

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

public class TagListEditor extends Composite implements LeafValueEditor<JsArray<TagJso>> {

  // UiBinder
  private static TagListEditorUiBinder uiBinder = GWT.create(TagListEditorUiBinder.class);
  public interface TagListEditorUiBinder extends UiBinder<Widget, TagListEditor> {}
  
  @UiField
  FlowPanel categories;
  
  private EventBus eventBus;
  
  @Inject
  public TagListEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }
  
  public void setEventBus(EventBus eventBus) {
    this.eventBus = eventBus;
    
    eventBus.addHandler(TagSelectEvent.TYPE, new TagSelectEventHandler() {
      @Override
      public void onTagSelectEvent(TagSelectEvent event) {
        TagJso TagJso = event.getData();
        addItem(TagJso);
      }
    });
  }

  @UiHandler("add")
  void onAddSelect(SelectEvent event) {
    //eventBus.fireEvent(new DisplayTagPopupEvent());
  }

  @Override
  public void setValue(JsArray<TagJso> value) {
    categories.clear();
    
    if (value == null) {
      return;
    }
    for (int i=0; i < value.length(); i++) {
      addItem(value.get(i));
    }
  }

  private void addItem(TagJso TagJso) {
    TagItemEditor w = new TagItemEditor();
    categories.add(w);
    w.setTagJso(TagJso);
    w.display();
  }

  @Override
  public JsArray<TagJso> getValue() {
    JsArray<TagJso> a = JavaScriptObject.createArray().cast();
    for (int i=0; i < categories.getWidgetCount(); i++) {
      TagItemEditor w = (TagItemEditor) categories.getWidget(i);
      a.push(w.getTagJso());
    }
    return a;
  }
  
}

package org.gonevertical.pm.directory.client.application.widgets.tag.editor;

import org.gonevertical.pm.directory.client.events.category.DeleteDataEvent;
import org.gonevertical.pm.directory.client.events.category.DeleteDataEvent.DeleteHandler;
import org.gonevertical.pm.directory.client.rest.jso.TagJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.web.bindery.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class TagItemEditor extends Composite implements Editor<TagJso> {

  // UiBinder
  private static TagItemEditorUiBinder uiBinder = GWT.create(TagItemEditorUiBinder.class);
  interface TagItemEditorUiBinder extends UiBinder<Widget, TagItemEditor> {}

  @UiField
  HTML name;

  private TagJso TagJso;

  public TagItemEditor() {
    initWidget(uiBinder.createAndBindUi(this));
  }

  public HandlerRegistration addDeleteHandler(DeleteHandler handler) {
    return addHandler(handler, DeleteDataEvent.getType());
  }

  public TagJso getTagJso() {
    return TagJso;
  }

  public void setTagJso(TagJso TagJso) {
    this.TagJso = TagJso;
  }
  
  public void display() {
    String name = TagJso.getName();
    if (name == null) {
      name = "";
    }
    this.name.setHTML(SimpleHtmlSanitizer.sanitizeHtml(name));
  }

  @UiHandler("delete")
  void onDeleteSelect(SelectEvent event) {
    removeFromParent();
  }

}

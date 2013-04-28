package org.gonevertical.pm.directory.client.application.widgets.archetype.edit.editor;

import org.gonevertical.pm.directory.client.application.widgets.category.editor.CategoryListEditor;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ArchetypeEditor extends Composite implements Editor<ArchetypeJso> {

  // Editor
  private static final ArchetypeDriver driver = GWT.create(ArchetypeDriver.class);
  interface ArchetypeDriver extends SimpleBeanEditorDriver<ArchetypeJso, ArchetypeEditor> {}
  
  // UiBinder
  public interface Binder extends UiBinder<Widget, ArchetypeEditor> {}
  
  @UiField 
  FlowLayoutContainer container;
  @UiField
  TextField name;
  @UiField
  TextField description;
  @UiField
  TextField repository;
  @UiField
  TextField groupId;
  @UiField
  TextField artifactId;
  @UiField
  TextField version;
  @UiField(provided = true)
  CategoryListEditor categories;

  @Inject
  public ArchetypeEditor(Binder binder, CategoryListEditor categories) {
    this.categories = categories;
    
    initWidget(binder.createAndBindUi(this));
    
    driver.initialize(this);
  }

  public void edit(ArchetypeJso archetypeJso) {
    driver.edit(archetypeJso);
  }
  
  public ArchetypeJso getArchTypeJso() {
    return driver.flush();
  }
  
}

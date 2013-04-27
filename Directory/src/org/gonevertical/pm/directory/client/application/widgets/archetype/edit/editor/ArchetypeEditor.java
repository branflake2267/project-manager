package org.gonevertical.pm.directory.client.application.widgets.archetype.edit.editor;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ArchetypeEditor extends Composite implements Editor<ArchetypeJso> {

  // Editor
  private static final ArchetypeDriver driver = GWT.create(ArchetypeDriver.class);
  interface ArchetypeDriver extends SimpleBeanEditorDriver<ArchetypeJso, ArchetypeEditor> {}
  
  // UiBinder
  private static ArchetypeEditorUiBinder uiBinder = GWT.create(ArchetypeEditorUiBinder.class);
  interface ArchetypeEditorUiBinder extends UiBinder<Widget, ArchetypeEditor> {}
  
  @UiField 
  VerticalLayoutContainer container;
  
  TextField name;
  TextField description;
  TextField repository;
  TextField groupId;
  TextField artifactId;
  TextField version;

  public ArchetypeEditor() {
    initWidget(uiBinder.createAndBindUi(this));
        
    TextButton addCategoryButton = new TextButton("Add Category");
    addCategoryButton.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        
      }
    });
     
    name = new TextField();
    description = new TextField();
    repository = new TextField();
    groupId = new TextField();
    artifactId = new TextField();
    version = new TextField(); 
    
    FieldLabel flName = new FieldLabel(name, "Name");
    FieldLabel flDescription = new FieldLabel(description, "Description");
    FieldLabel flRepository = new FieldLabel(repository, "Repository");
    FieldLabel flGroupId = new FieldLabel(groupId, "GroupId");
    FieldLabel flArtifactId = new FieldLabel(artifactId, "ArtifactId");
    FieldLabel flVersion = new FieldLabel(version, "Version");
    
    container.add(flName, new VerticalLayoutData(-1, -1));
    container.add(flDescription, new VerticalLayoutData(-1, -1));
    container.add(flRepository, new VerticalLayoutData(-1, -1));
    container.add(flGroupId, new VerticalLayoutData(-1, -1));
    container.add(flArtifactId, new VerticalLayoutData(-1, -1));
    container.add(flVersion, new VerticalLayoutData(-1, -1));
    
    name.setWidth(200);
    description.setWidth(200);
    repository.setWidth(200);
    groupId.setWidth(200);
    artifactId.setWidth(200);
    version.setWidth(200);
    
    driver.initialize(this);
  }

  public void edit(ArchetypeJso archetypeJso) {
    driver.edit(archetypeJso);
  }
  
  public ArchetypeJso getArchTypeJso() {
    return driver.flush();
  }
  
}

package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ArchetypeEditView extends ViewWithUiHandlers<ArchetypeEditUiHandlers> implements
    ArchetypeEditPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeEditView> {
  }
  
  @UiField
  TextButton goback;
  @UiField
  TextButton save;
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
  
  private ArchetypeJso archetypeJso;

  @Inject
  public ArchetypeEditView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @UiHandler("goback")
  void onGoBack(SelectEvent event) {
    getUiHandlers().displayList();
  }
  
  @UiHandler("save")
  void onSave(SelectEvent event) {
    getUiHandlers().save(getArchetype());
  }
  
  private ArchetypeJso getArchetype() {
    archetypeJso.setName(getName());
    archetypeJso.setDescription(getDescription());
    archetypeJso.setRepository(getRespository());
    archetypeJso.setGroupId(getGroupId());
    archetypeJso.setArtifactId(getArtifactId());
    archetypeJso.setVersion(getVersion());
    return archetypeJso;
  }

  @Override
  public void display(ArchetypeJso archetypeJso) {
    this.archetypeJso = archetypeJso;
    
    displayName(archetypeJso.getName());
    displayDescription(archetypeJso.getDescription());
    displayRepository(archetypeJso.getRepository());
    displayGroupId(archetypeJso.getGroupId());
    displayArtifactId(archetypeJso.getArtifactId());
    displayVersion(archetypeJso.getVersion());
  }
  
  private void displayName(String s) {
    if (s == null) {
      s = "";
    }
    this.name.setText(s);
  }
  
  private String getName() {
    return name.getText().trim();
  }
  
  private void displayDescription(String s) {
    if (s == null) {
      s = "";
    }
    this.description.setText(s);
  }
  
  private String getDescription() {
    return description.getText().trim();
  }
  
  private void displayRepository(String s) {
    if (s == null) {
      s = "";
    }
    this.repository.setText(s);
  }
  
  private String getRespository() {
    return repository.getText().trim();
  }

  private void displayGroupId(String s) {
    if (s == null) {
      s = "";
    }
    this.groupId.setText(s);
  }
  
  private String getGroupId() {
    return groupId.getText().trim();
  }
  
  private void displayArtifactId(String s) {
    if (s == null) {
      s = "";
    }
    this.artifactId.setText(s);
  }
  
  private String getArtifactId() {
    return artifactId.getText().trim();
  }
  
  private void displayVersion(String s) {
    if (s == null) {
      s = "";
    }
    this.version.setText(s);
  }
  
  private String getVersion() {
    return version.getText().trim();
  }
  
}

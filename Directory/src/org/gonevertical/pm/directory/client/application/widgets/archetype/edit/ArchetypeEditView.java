package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.ListView;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;

public class ArchetypeEditView extends ViewWithUiHandlers<ArchetypeEditUiHandlers> implements
    ArchetypeEditPresenter.MyView {

  interface CategoryProperties extends PropertyAccess<CategoryJso> {
    ModelKeyProvider<CategoryJso> key();
    ValueProvider<CategoryJso, String> name();
  }
  
  public interface Binder extends UiBinder<SimpleContainer, ArchetypeEditView> {
  }
  
  @UiField
  TextButton goback;
  @UiField
  TextButton save;
  @UiField
  VerticalLayoutContainer vlc;
  
  final static CategoryProperties categoryProperties = GWT.create(CategoryProperties.class);
  
  private final TextField name;
  private final TextField description;
  private final TextField repository;
  private final TextField groupId;
  private final TextField artifactId;
  private final TextField version;
  private final ListStore<CategoryJso> categoryStore = new ListStore<CategoryJso>(categoryProperties.key());
  
  private ArchetypeJso archetypeJso;

  @Inject
  public ArchetypeEditView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
    
    TextButton addCategoryButton = new TextButton("Add Category");
    addCategoryButton.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        getUiHandlers().displayCategoryPopup();
      }
    });
     
    name = new TextField();
    description = new TextField();
    repository = new TextField();
    groupId = new TextField();
    artifactId = new TextField();
    version = new TextField(); 
    
    ListView<CategoryJso, String> categoryList = new ListView<CategoryJso, String>(categoryStore, categoryProperties.name());
    HorizontalPanel hpCategory = new HorizontalPanel();
    hpCategory.add(categoryList);
    hpCategory.add(new HTML("&nbsp;"));
    hpCategory.add(addCategoryButton);
    
    FieldLabel flName = new FieldLabel(name, "Name");
    FieldLabel flDescription = new FieldLabel(description, "Description");
    FieldLabel flRepository = new FieldLabel(repository, "Repository");
    FieldLabel flGroupId = new FieldLabel(groupId, "GroupId");
    FieldLabel flArtifactId = new FieldLabel(artifactId, "ArtifactId");
    FieldLabel flVersion = new FieldLabel(version, "Version");
    FieldLabel flCategories = new FieldLabel(hpCategory, "Categories");
    
    vlc.add(flName, new VerticalLayoutData(-1, -1));
    vlc.add(flDescription, new VerticalLayoutData(-1, -1));
    vlc.add(flRepository, new VerticalLayoutData(-1, -1));
    vlc.add(flGroupId, new VerticalLayoutData(-1, -1));
    vlc.add(flArtifactId, new VerticalLayoutData(-1, -1));
    vlc.add(flVersion, new VerticalLayoutData(-1, -1));
    vlc.add(flCategories, new VerticalLayoutData(-1, -1));
    
    name.setWidth(200);
    description.setWidth(200);
    repository.setWidth(200);
    groupId.setWidth(200);
    artifactId.setWidth(200);
    version.setWidth(200);
    categoryList.setSize("200px", "150px");
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

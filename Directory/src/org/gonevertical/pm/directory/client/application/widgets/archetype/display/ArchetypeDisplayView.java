package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeDisplayView extends ViewWithUiHandlers<ArchetypeDisplayUiHandlers> implements
    ArchetypeDisplayPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeDisplayView> {
  }
  
  @UiField
  Button edit;
  @UiField
  InlineHTML name;
  @UiField
  InlineHTML description;
  @UiField
  InlineHTML repository;
  @UiField
  InlineHTML groupId;
  @UiField
  InlineHTML artifactId;
  @UiField
  InlineHTML version;
  
  private final LoggedInUser loggedInUser;
  
  private ArchetypeJso archetypeJso;

  @Inject
  public ArchetypeDisplayView(final Binder binder, LoggedInUser loggedInUser) {
    this.loggedInUser = loggedInUser;
    
    initWidget(binder.createAndBindUi(this));
    
    edit.setVisible(false);
  }

  @Override
  public void displayArchetype(ArchetypeJso archetypeJso) {
    this.archetypeJso = archetypeJso;
    
    displayEditButton();
    
    displayName(archetypeJso.getName());
    displayDescription(archetypeJso.getDescription());
    displayRepository(archetypeJso.getRepository());
    displayGroupId(archetypeJso.getGroupId());
    displayArtifactId(archetypeJso.getArtifactid());
    displayVersion(archetypeJso.getVersion());
  }
  
  @UiHandler("edit")
  void onEditClick(ClickEvent event) {
    getUiHandlers().gotoEdit(archetypeJso);
  }

  private void displayEditButton() {
    if (loggedInUser.getIsAdmin()) {
      edit.setVisible(true);
    } else {
      edit.setVisible(false);
    }
  }

  private void displayName(String s) {
    if (s == null) {
      s = "Name";
    }
    this.name.setText(s);
  }
  
  private void displayDescription(String s) {
    if (s == null) {
      s = "Description";
    }
    this.description.setText(s);
  }
  
  private void displayRepository(String s) {
    if (s == null) {
      s = "Repository";
    }
    this.repository.setText(s);
  }

  private void displayGroupId(String s) {
    if (s == null) {
      s = "GroupId";
    }
    this.groupId.setText(s);
  }
  
  private void displayArtifactId(String s) {
    if (s == null) {
      s = "ArtifactId";
    }
    this.artifactId.setText(s);
  }
  
  private void displayVersion(String s) {
    if (s == null) {
      s = "Version";
    }
    this.version.setText(s);
  }
  
}

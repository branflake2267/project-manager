package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class ArchetypeDisplayView extends ViewWithUiHandlers<ArchetypeDisplayUiHandlers> implements
    ArchetypeDisplayPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeDisplayView> {
  }

  @UiField
  TextButton edit;
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
  @UiField
  HTML mvn;

  private final LoggedInUser loggedInUser;
  private final ArchetypeDisplay archetypeDisplay;

  private ArchetypeJso archetypeJso;

  @Inject
  public ArchetypeDisplayView(Binder binder, LoggedInUser loggedInUser, ArchetypeDisplay archetypeDisplay) {
    this.loggedInUser = loggedInUser;
    this.archetypeDisplay = archetypeDisplay;

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
    displayArtifactId(archetypeJso.getArtifactId());
    displayVersion(archetypeJso.getVersion());
    displayMvnCommand(archetypeJso);
  }

  @UiHandler("edit")
  void onEdit(SelectEvent event) {
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

  private void displayMvnCommand(ArchetypeJso archetype) {
    SafeHtml safehtml = archetypeDisplay.displayCommand(archetype);
    mvn.setHTML(safehtml);
  }

}

package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.application.widgets.archetype.display.xtemplate.ArchetypeDisplayXTemplates;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;

public class ArchetypeDisplayView extends ViewWithUiHandlers<ArchetypeDisplayUiHandlers> implements
    ArchetypeDisplayPresenter.MyView {

  public interface Binder extends UiBinder<SimpleContainer, ArchetypeDisplayView> {
  }

  @UiField
  TextButton edit;
  @UiField(provided = true)
  FieldLabel name;
  @UiField(provided = true)
  FieldLabel description;
  @UiField(provided = true)
  FieldLabel repository;
  @UiField(provided = true)
  FieldLabel groupId;
  @UiField(provided = true)
  FieldLabel artifactId;
  @UiField(provided = true)
  FieldLabel version;
  @UiField
  HTML mvn;

  private final LoggedInUser loggedInUser;
  private final ArchetypeDisplayXTemplates archetypeDisplay;

  private ArchetypeJso archetypeJso;

  @Inject
  public ArchetypeDisplayView(Binder binder, LoggedInUser loggedInUser, ArchetypeDisplayXTemplates archetypeDisplay) {
    this.loggedInUser = loggedInUser;
    this.archetypeDisplay = archetypeDisplay;

    name = new FieldLabel();
    description = new FieldLabel();
    repository = new FieldLabel();
    groupId = new FieldLabel();
    artifactId = new FieldLabel();
    version = new FieldLabel();

    name.setText("Name");
    description.setText("Description");
    repository.setText("Repository");
    groupId.setText("GroupId");
    artifactId.setText("ArtifactId");
    version.setText("Version");

    name.setWidget(new HTML("&nbsp;"));
    description.setWidget(new HTML("&nbsp;"));
    repository.setWidget(new HTML("&nbsp;"));
    groupId.setWidget(new HTML("&nbsp;"));
    artifactId.setWidget(new HTML("&nbsp;"));
    version.setWidget(new HTML("&nbsp;"));

    initWidget(binder.createAndBindUi(this));

    edit.setVisible(false);
  }

  @Override
  public void displayArchetype(ArchetypeJso archetypeJso) {
    if (archetypeJso == null) {
      archetypeJso = ArchetypeJso.newInstance();
    }
    this.archetypeJso = archetypeJso;

    displayEditButton();
    displayText(archetypeJso);
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

  private void displayText(ArchetypeJso archetypeJso) {
    displayText(name.getWidget(), archetypeJso.getName());
    displayText(description.getWidget(), archetypeJso.getDescription());
    displayText(repository.getWidget(), archetypeJso.getRepository());
    displayText(groupId.getWidget(), archetypeJso.getGroupId());
    displayText(artifactId.getWidget(), archetypeJso.getArtifactId());
    displayText(version.getWidget(), archetypeJso.getVersion());
  }

  private void displayText(Widget w, String s) {
    if (s == null || s.trim().length() == 0) {
      s = "&nbsp;";
    }
    HTML html = (HTML) w;
    html.setHTML(SimpleHtmlSanitizer.sanitizeHtml(s));
  }

  private void displayMvnCommand(ArchetypeJso archetype) {
    if (archetype.getArtifactId() == null) {
      archetype.setArtifactId("");
    }

    if (archetype.getDescription() == null) {
      archetype.setDescription("");
    }

    if (archetype.getGroupId() == null) {
      archetype.setGroupId("");
    }

    if (archetype.getName() == null) {
      archetype.setName("");
    }
    
    if (archetype.getRepository() == null) {
      archetype.setRepository("");
    }
    
    if (archetype.getVersion() == null) {
      archetype.setVersion("");
    }

    SafeHtml safehtml = archetypeDisplay.displayCommand(archetype);
    mvn.setHTML(safehtml);
  }

}

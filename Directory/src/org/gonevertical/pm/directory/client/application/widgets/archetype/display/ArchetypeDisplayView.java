package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeDisplayView extends ViewWithUiHandlers<ArchetypeDisplayUiHandlers> implements
    ArchetypeDisplayPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeDisplayView> {
  }
  
  @UiField
  HTML name;

  @Inject
  public ArchetypeDisplayView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void displayArchetype(ArchetypeJso archetypeJso) {
    displayName(archetypeJso.getName());
  }

  private void displayName(String name) {
    if (name == null) {
      name = "Name";
    }
    this.name.setText(name);
  }

}

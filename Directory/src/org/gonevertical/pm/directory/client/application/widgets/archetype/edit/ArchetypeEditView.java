package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeEditView extends ViewWithUiHandlers<ArchetypeEditUiHandlers> implements
    ArchetypeEditPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeEditView> {
  }
  
  @UiField
  Button goback;
  private ArchetypeObserver archetypeObserver;

  @Inject
  public ArchetypeEditView(final Binder binder, ArchetypeObserver archetypeObserver) {
    this.archetypeObserver = archetypeObserver;
    
    initWidget(binder.createAndBindUi(this));
  }

  @UiHandler("goback")
  void onGoBackClick(ClickEvent event) {
    archetypeObserver.displayList();
  }
  
}

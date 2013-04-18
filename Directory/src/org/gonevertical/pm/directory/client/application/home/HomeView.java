package org.gonevertical.pm.directory.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

  public interface Binder extends UiBinder<Widget, HomeView> {
  }
  
  @UiField
  FlowPanel displayPanel;
  @UiField
  SimplePanel editPanel;
  @UiField
  SimplePanel archetypeList;
  @UiField
  SimplePanel archetypeDisplay;
  @UiField
  TextButton add;

  @Inject
  public HomeView(final Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    add.setVisible(false);
    
    displayList();
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == HomePresenter.TYPE_ArchetypeListPresenter) {
      archetypeList.setWidget(content);
    } else if (slot == HomePresenter.TYPE_ArchetypeDisplayPresenter) {
      archetypeDisplay.setWidget(content);
    } else if (slot == HomePresenter.TYPE_ArchetypeEditPresenter) {
      editPanel.setWidget(content);
    }
  }
  
  @Override
  public void displayList() {
    displayPanel.setVisible(true);
    editPanel.setVisible(false);
  }
  
  @Override
  public void displayEdit() {
    displayPanel.setVisible(false);
    editPanel.setVisible(true);
  }
  
  @Override
  public void setAddVisible(boolean visible) {
    this.add.setVisible(visible);
  }

  @UiHandler("add")
  public void onSelectionEvent(SelectEvent event) {
    getUiHandlers().addArchetype();
  }
  
}

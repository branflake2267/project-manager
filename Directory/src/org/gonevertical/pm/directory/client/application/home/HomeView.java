package org.gonevertical.pm.directory.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class HomeView extends ViewWithUiHandlers<HomeUiHandlers> implements HomePresenter.MyView {

  public interface Binder extends UiBinder<Widget, HomeView> {
  }
  
  @UiField
  VerticalLayoutContainer displayPanel;
  @UiField
  VerticalLayoutContainer displayEdit;
  
  @UiField
  SimpleContainer editPanel;
  @UiField
  SimpleContainer archetypeList;
  @UiField
  SimpleContainer archetypeDisplay;
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
    displayEdit.setVisible(false);
  }
  
  @Override
  public void displayEdit() {
    displayPanel.setVisible(false);
    displayEdit.setVisible(true);
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

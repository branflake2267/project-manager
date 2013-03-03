package org.gonevertical.pm.directory.client.application.home;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class HomeView extends ViewImpl implements HomePresenter.MyView {

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

  @Inject
  public HomeView(final Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));
    
    // defaults
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
  
}

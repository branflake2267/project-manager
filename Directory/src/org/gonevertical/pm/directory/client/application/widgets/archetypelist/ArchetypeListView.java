package org.gonevertical.pm.directory.client.application.widgets.archetypelist;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeListView extends ViewWithUiHandlers<ArchtypeListUiHandlers> implements ArchetypeListPresenter.MyView {
  
  public interface Binder extends UiBinder<HTMLPanel, ArchetypeListView> {
  }

  @Inject
  public ArchetypeListView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
   
  }

}

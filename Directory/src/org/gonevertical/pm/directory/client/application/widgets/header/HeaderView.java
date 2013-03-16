package org.gonevertical.pm.directory.client.application.widgets.header;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class HeaderView extends ViewWithUiHandlers<HeaderUiHandlers> implements HeaderPresenter.MyView {
  
  public interface Binder extends UiBinder<HTMLPanel, HeaderView> {
  }

  @UiField
  SimplePanel login;
  @UiField
  HTMLPanel admin;

  @Inject
  public HeaderView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
    
    admin.setVisible(false);
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == HeaderPresenter.TYPE_LoginPresenter) {
      login.setWidget(content);
    }
  }
  
  @Override
  public void setAdmin(boolean visible) {
    this.admin.setVisible(visible);
  }

}

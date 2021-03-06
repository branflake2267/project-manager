package org.gonevertical.pm.directory.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
  public interface Binder extends UiBinder<Widget, ApplicationView> {
  } 

  @UiField
  SimpleContainer header;
  @UiField
  SimpleContainer main; 

  @Inject
  public ApplicationView(final Binder uiBinder) {
      initWidget(uiBinder.createAndBindUi(this));
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
      if (slot == ApplicationPresenter.TYPE_HeaderPresenter) {
          header.setWidget(content);
      } else if (slot == ApplicationPresenter.TYPE_SetMainContent) {
          main.setWidget(content);
      } else {
          super.setInSlot(slot, content);
      }
  }
}

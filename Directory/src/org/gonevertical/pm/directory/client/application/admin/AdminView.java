package org.gonevertical.pm.directory.client.application.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

public class AdminView extends ViewImpl implements AdminPresenter.MyView {

  public interface Binder extends UiBinder<Widget, AdminView> {
  }

  @UiField
  FlowPanel urls;
  
  @UiField
  SimplePanel categories;

  @Inject
  public AdminView(Binder uiBinder) {
    initWidget(uiBinder.createAndBindUi(this));

    displayUrls();
  }

  private void displayUrls() {
    String url = GWT.getHostPageBaseURL() + "/_ah/api/explorer";//_ah/api/discovery/v1/apis
    String html = "<a href=\"" + url + "\">Discovery</a>";
    urls.add(new HTML(html));
  }

  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == AdminPresenter.TYPE_CategoryPresenter) {
      categories.setWidget(content);
    }
  }
  
}

package org.gonevertical.pm.directory.client.application.widgets.tag.select;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.Window;

public class TagSelectView extends ViewWithUiHandlers<TagSelectUiHandlers> implements
    TagSelectPresenter.MyView {

  public interface Binder extends UiBinder<Widget, TagSelectView> {
  }
  
  @UiField
  Window container;
  
  @Inject
  public TagSelectView(Binder binder, EventBus eventBus) {
    initWidget(binder.createAndBindUi(this));
    
    container.setMaximizable(false);
    container.setHeadingText("Select Tags");
    container.setPixelSize(500, 500);
    
  }
  
  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == TagSelectPresenter.SLOT_tagList) {
      container.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }

  @Override
  public void center() {
    container.show();
  }

  @Override
  public void hide() {
    container.hide();
  }
  
}

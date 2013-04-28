package org.gonevertical.pm.directory.client.application.widgets.category.select;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.Window;

public class CategorySelectView extends ViewWithUiHandlers<CategorySelectUiHandlers> implements
    CategorySelectPresenter.MyView {

  public interface Binder extends UiBinder<Widget, CategorySelectView> {
  }
  
  @UiField
  Window container;
  
  @Inject
  public CategorySelectView(Binder binder, EventBus eventBus) {
    initWidget(binder.createAndBindUi(this));
    
    container.setMaximizable(false);
    container.setHeadingText("Select Category");
    container.setPixelSize(500, 500);
    
  }
  
  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == CategorySelectPresenter.SLOT_categoryList) {
      container.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }

  @Override
  public void center() {
    container.show();
  }
  
}

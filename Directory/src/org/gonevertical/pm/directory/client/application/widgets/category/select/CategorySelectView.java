package org.gonevertical.pm.directory.client.application.widgets.category.select;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PopupViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class CategorySelectView extends PopupViewWithUiHandlers<CategorySelectUiHandlers> implements
    CategorySelectPresenter.MyView {

  public interface Binder extends UiBinder<PopupPanel, CategorySelectView> {
  }
  
  @UiField
  SimpleContainer container;
  @UiField
  TextButton close;
  
  @Inject
  public CategorySelectView(Binder binder, EventBus eventBus) {
    super(eventBus);
    
    initWidget(binder.createAndBindUi(this));
  }
  
  @Override
  public void setInSlot(Object slot, Widget content) {
    if (slot == CategorySelectPresenter.SLOT_categoryList) {
      container.setWidget(content);
    } else {
      super.setInSlot(slot, content);
    }
  }
  
  @UiHandler("close")
  public void onCloseSelectEvent(SelectEvent event) {
    hide();
  }
}

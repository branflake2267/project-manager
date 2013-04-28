package org.gonevertical.pm.directory.client.application.widgets.category.select;

import org.gonevertical.pm.directory.client.application.widgets.category.list.CategoryListPresenter;
import org.gonevertical.pm.directory.client.events.category.DisplayCategoryPopupEvent;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CategorySelectPresenter extends PresenterWidget<CategorySelectPresenter.MyView> implements
    CategorySelectUiHandlers, DisplayCategoryPopupEvent.AddCategoryHandler {

  public interface MyView extends View, HasUiHandlers<CategorySelectUiHandlers> {
    void center();
  }
  
  public static Object SLOT_categoryList = new Object();

  private final CategoryListPresenter categoryListPresenter;
  
  @Inject
  public CategorySelectPresenter(EventBus eventBus, MyView view, CategoryListPresenter categoryListPresenter) {
    super(eventBus, view);
    
    this.categoryListPresenter = categoryListPresenter;
    
    getView().setUiHandlers(this);
  }
    
  @Override
  protected void onBind() {
    super.onBind();
    
    setInSlot(SLOT_categoryList, categoryListPresenter);
    
    addRegisteredHandler(DisplayCategoryPopupEvent.getType(), this);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    categoryListPresenter.display();
  }

  @Override
  public void onAddCategory(DisplayCategoryPopupEvent event) {
    getView().center();
  }
  
}

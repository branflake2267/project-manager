package org.gonevertical.pm.directory.client.application.widgets.category.select;

import org.gonevertical.pm.directory.client.application.widgets.category.list.CategoryListPresenter;
import org.gonevertical.pm.directory.client.events.category.CategorySelectEventHandler;
import org.gonevertical.pm.directory.client.events.category.DisplayCategoryPopupEvent;
import org.gonevertical.pm.directory.client.events.category.CategorySelectEvent;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CategorySelectPresenter extends PresenterWidget<CategorySelectPresenter.MyView> implements
    CategorySelectUiHandlers, DisplayCategoryPopupEvent.AddCategoryHandler, CategorySelectEventHandler {

  public interface MyView extends View, HasUiHandlers<CategorySelectUiHandlers> {
    void center();
    void hide();
  }
  
  public static Object SLOT_categoryList = new Object();

  private final CategoryListPresenter categoryListPresenter;

  private EventBus eventBus;
  
  @Inject
  public CategorySelectPresenter(EventBus eventBus, MyView view, CategoryListPresenter categoryListPresenter) {
    super(eventBus, view);
    this.eventBus = eventBus;
    
    this.categoryListPresenter = categoryListPresenter;
    
    getView().setUiHandlers(this);
  }
    
  @Override
  protected void onBind() {
    super.onBind();
    
    addRegisteredHandler(DisplayCategoryPopupEvent.getType(), this);
    addRegisteredHandler(CategorySelectEvent.TYPE, this);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    setInSlot(SLOT_categoryList, categoryListPresenter);
  }

  @Override
  public void onAddCategory(DisplayCategoryPopupEvent event) {
    categoryListPresenter.display();
    categoryListPresenter.edit(false);
    
    getView().center();
  }

  @Override
  public void onCategorySelectEvent(CategorySelectEvent event) {
    getView().hide();
  }
 
}

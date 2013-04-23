package org.gonevertical.pm.directory.client.application.widgets.category.select;

import org.gonevertical.pm.directory.client.application.widgets.category.list.CategoryListPresenter;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PopupView;
import com.gwtplatform.mvp.client.PresenterWidget;

public class CategorySelectPresenter extends PresenterWidget<CategorySelectPresenter.MyView> implements
    CategorySelectUiHandlers {

  public interface MyView extends PopupView, HasUiHandlers<CategorySelectUiHandlers> {
  }
  
  public static Object TYPE_categoryList = new Object();

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
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    setInSlot(TYPE_categoryList, categoryListPresenter);
  }

}

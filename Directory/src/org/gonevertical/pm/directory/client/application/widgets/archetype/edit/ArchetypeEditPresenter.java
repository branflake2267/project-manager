package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.application.widgets.category.select.CategorySelectPresenter;
import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeEditEvent;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;
import org.gonevertical.pm.directory.client.events.category.SelectCategoryEvent;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ArchetypeEditPresenter extends PresenterWidget<ArchetypeEditPresenter.MyView> implements
    ArchetypeEditUiHandlers, ArchetypeEditEvent.EditArchetypeHandler, SelectCategoryEvent.SelectSelectHandler<CategoryJso> {

  private final ArchetypeJsoDao archetypeJsoDao;
  private final ArchetypeObserver archetypeObserver;
  private final CategorySelectPresenter categorySelectPresenter;

  public interface MyView extends View, HasUiHandlers<ArchetypeEditUiHandlers> {
    void display(ArchetypeJso archetypeJso);

    void setCategorySelected(CategoryJso selected);
  }

  @Inject
  public ArchetypeEditPresenter(
      EventBus eventBus, 
      MyView view, 
      ArchetypeObserver archetypeObserver,
      LoginPresenter loginPresenter,
      CategorySelectPresenter categorySelectPresenter,
      ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);
    
    this.categorySelectPresenter = categorySelectPresenter;
    this.archetypeObserver = archetypeObserver;
    this.archetypeJsoDao = archetypeJsoDao;

    getView().setUiHandlers(this);
  }
  
  @Override
  protected void onBind() {
    super.onBind();
    
    registerHandler(archetypeObserver.getEventBus().addHandler(ArchetypeEditEvent.getType(), this));
    addRegisteredHandler(SelectCategoryEvent.getType(), this);
  }

  @Override
  public void onArchetypeEdit(ArchetypeEditEvent event) {
    getView().display(event.get());
  }

  @Override
  public void displayList() {
    archetypeObserver.displayList();
  }

  @Override
  public void save(ArchetypeJso archetypeJso) {
    archetypeJsoDao.put(archetypeJso, new RestHandler<ArchetypeJso>() {
      @Override
      public void onSuccess(ArchetypeJso archetypeJso) {
        archetypeObserver.update(archetypeJso);
      }
      
      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  @Override
  public void displayCategoryPopup() {
    addToPopupSlot(categorySelectPresenter, true);
  }

  @Override
  public void onSelect(SelectCategoryEvent<CategoryJso> event) {
    getView().setCategorySelected(event.getData());
  }

}

package org.gonevertical.pm.directory.client.application.widgets.category.list;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class CategoryListPresenter extends PresenterWidget<CategoryListPresenter.MyView> implements
    CategoryListUiHandlers {

  public interface MyView extends View, HasUiHandlers<CategoryListUiHandlers> {
  }

  private boolean initialized;

  @Inject
  public CategoryListPresenter(EventBus eventBus, MyView view, LoginPresenter loginPresenter,
      ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);

    getView().setUiHandlers(this);
  }

}

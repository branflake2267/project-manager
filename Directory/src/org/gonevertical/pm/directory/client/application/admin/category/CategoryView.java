package org.gonevertical.pm.directory.client.application.admin.category;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class CategoryView extends ViewWithUiHandlers<CategoryUiHandlers> implements CategoryPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, CategoryView> {
  }

  private final LoggedInUser loggedInUser;

  private ArchetypeJso archetypeJso;

  @Inject
  public CategoryView(Binder binder, LoggedInUser loggedInUser) {
    this.loggedInUser = loggedInUser;

    initWidget(binder.createAndBindUi(this));
  }

}

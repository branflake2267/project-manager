package org.gonevertical.pm.directory.client.application.widgets.category.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class CategoryListView extends ViewWithUiHandlers<CategoryListUiHandlers> implements
    CategoryListPresenter.MyView {
  private static final int PAGE_SIZE = 15;

  public interface Binder extends UiBinder<HTMLPanel, CategoryListView> {
  }

  @UiField
  FlowPanel tableContanier;

  private Grid<ArchetypeJso> grid;

  @Inject
  public CategoryListView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

}

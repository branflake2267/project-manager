package org.gonevertical.pm.directory.client.application.widgets.category.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

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

  @Override
  public void initTreeGrid(TreeStore<CategoryJso> treeStore, final TreeLoader<CategoryJso> loader,
      CategoryProperties categoryProperties) {
    ColumnConfig<CategoryJso, String> colName = new ColumnConfig<CategoryJso, String>(categoryProperties.name(), 150,
        "Name");

    List<ColumnConfig<CategoryJso, ?>> columns = new ArrayList<ColumnConfig<CategoryJso, ?>>();
    columns.add(colName);

    ColumnModel<CategoryJso> columnModel = new ColumnModel<CategoryJso>(columns);

    FramedPanel framedPanel = new FramedPanel() {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        loader.load();
      }
    };
    framedPanel.setHeadingText("Categories");
    framedPanel.setButtonAlign(BoxLayoutPack.CENTER);
    framedPanel.setPixelSize(600, 300);

    TreeGrid<CategoryJso> treeGrid = new TreeGrid<CategoryJso>(treeStore, columnModel, colName);
    treeGrid.setBorders(true);
    treeGrid.setTreeLoader(loader);
    treeGrid.getView().setTrackMouseOver(false);
    treeGrid.getView().setAutoExpandColumn(colName);
    framedPanel.setWidget(treeGrid);
    
    tableContanier.add(framedPanel);
  }

}

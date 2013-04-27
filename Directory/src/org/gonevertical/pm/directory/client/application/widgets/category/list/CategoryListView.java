package org.gonevertical.pm.directory.client.application.widgets.category.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CategoryListView extends ViewWithUiHandlers<CategoryListUiHandlers> implements
    CategoryListPresenter.MyView {

  public interface Binder extends UiBinder<Widget, CategoryListView> {
  }

  @UiField
  SimpleContainer container;

  private TreeStore<CategoryJso> treeStore;
  private TreeGrid<CategoryJso> grid;
  private ColumnConfig<CategoryJso, String> colName;
  private ToolBar toolBar;

  @Inject
  public CategoryListView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void initTreeGrid(TreeStore<CategoryJso> treeStore, final TreeLoader<CategoryJso> loader,
      CategoryProperties categoryProperties) {
    this.treeStore = treeStore;

    colName = new ColumnConfig<CategoryJso, String>(categoryProperties.name(), 150, "Name");
    List<ColumnConfig<CategoryJso, ?>> columns = new ArrayList<ColumnConfig<CategoryJso, ?>>();
    columns.add(colName);
    ColumnModel<CategoryJso> columnModel = new ColumnModel<CategoryJso>(columns);

    // Panels
    FramedPanel framedPanel = new FramedPanel() {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        loader.load();
      }
    };
    framedPanel.setHeadingText("Categories");
    framedPanel.setCollapsible(false);
    framedPanel.setPixelSize(400, 300);

    grid = new TreeGrid<CategoryJso>(treeStore, columnModel, colName);
    grid.setBorders(true);
    grid.setTreeLoader(loader);
    grid.getView().setTrackMouseOver(false);
    grid.getView().setAutoExpandColumn(colName);

    VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();
    verticalLayoutContainer.setBorders(true);
    verticalLayoutContainer.add(getToolbarWidget(), new VerticalLayoutData(1, -1));
    verticalLayoutContainer.add(grid, new VerticalLayoutData(1, 1));

    framedPanel.setWidget(verticalLayoutContainer);

    container.add(framedPanel);
  }

  @Override
  public void edit(boolean edit) {
    if (edit) {
      // Editor
      GridInlineEditing<CategoryJso> editing = new GridInlineEditing<CategoryJso>(grid);
      editing.addEditor(colName, new TextField());
      editing.addCompleteEditHandler(new CompleteEditHandler<CategoryJso>() {
        @Override
        public void onCompleteEdit(CompleteEditEvent<CategoryJso> event) {
          getUiHandlers().save();
        }
      });

      toolBar.setVisible(true);
      
      grid.setSelectionModel(null);
    } else {
      // selection
      IdentityValueProvider<CategoryJso> identity = new IdentityValueProvider<CategoryJso>();
      CheckBoxSelectionModel<CategoryJso> sm = new CheckBoxSelectionModel<CategoryJso>(identity);
      sm.setSelectionMode(SelectionMode.SIMPLE);
      sm.addSelectionHandler(new SelectionHandler<CategoryJso>() {
        @Override
        public void onSelection(SelectionEvent<CategoryJso> event) {
          CategoryJso selected = event.getSelectedItem();
          getUiHandlers().setSelected(selected);
        }
      });
      grid.setSelectionModel(sm);
    }
  }

  private ToolBar getToolbarWidget() {
    TextButton add = new TextButton("Add");
    add.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        getUiHandlers().createNew();
      }
    });

    TextButton save = new TextButton("Save");
    save.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        getUiHandlers().save();
      }
    });

    toolBar = new ToolBar();
    toolBar.add(add);
    toolBar.add(save);

    toolBar.setVisible(false);

    return toolBar;
  }

  @Override
  public void add(CategoryJso categoryJso) {
    treeStore.add(categoryJso);
  }

}

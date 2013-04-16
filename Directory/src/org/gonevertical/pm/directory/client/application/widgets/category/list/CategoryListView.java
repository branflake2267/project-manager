package org.gonevertical.pm.directory.client.application.widgets.category.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CategoryListView extends ViewWithUiHandlers<CategoryListUiHandlers> implements
    CategoryListPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, CategoryListView> {
  }

  @UiField
  Button add;
  @UiField
  FlowPanel categories;
  
  private TreeStore<CategoryJso> treeStore;

  @Inject
  public CategoryListView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void initTreeGrid(TreeStore<CategoryJso> treeStore, final TreeLoader<CategoryJso> loader,
      CategoryProperties categoryProperties) {
    this.treeStore = treeStore;
    
    ColumnConfig<CategoryJso, String> colName = new ColumnConfig<CategoryJso, String>(categoryProperties.name(), 150,
        "Name");

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
    framedPanel.setButtonAlign(BoxLayoutPack.CENTER);
    framedPanel.setPixelSize(600, 300);

    TreeGrid<CategoryJso> treeGrid = new TreeGrid<CategoryJso>(treeStore, columnModel, colName);
    treeGrid.setBorders(true);
    treeGrid.setTreeLoader(loader);
    treeGrid.getView().setTrackMouseOver(false);
    treeGrid.getView().setAutoExpandColumn(colName);
    framedPanel.setWidget(treeGrid);
    
    // Editor
    GridInlineEditing<CategoryJso> editing = new GridInlineEditing<CategoryJso>(treeGrid);
    editing.addEditor(colName, new TextField());
    
    categories.add(framedPanel);
  }

  @UiHandler("add")
  public void onAddClick(ClickEvent event) {
    CategoryJso jso = JavaScriptObject.createObject().cast();
    jso.setName("test123");
    jso.setKey("123");
    jso.setParent("11");
    jso.setChildren(true);
    treeStore.add(jso);
  }
  
}

package org.gonevertical.pm.directory.client.application.widgets.category.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.data.shared.loader.TreeLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent;
import com.sencha.gxt.widget.core.client.event.CompleteEditEvent.CompleteEditHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.treegrid.TreeGrid;

public class CategoryListView extends ViewWithUiHandlers<CategoryListUiHandlers> implements
    CategoryListPresenter.MyView {

  public interface Binder extends UiBinder<SimpleContainer, CategoryListView> {
  }

  @UiField
  TextButton add;
  @UiField
  TextButton save;
  @UiField
  SimpleContainer container;
  
  private TreeStore<CategoryJso> treeStore;
  private TreeGrid<CategoryJso> grid;

  @Inject
  public CategoryListView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
    
    add.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        getUiHandlers().createNew();
      }
    });
    
    save.addSelectHandler(new SelectHandler() {
      @Override
      public void onSelect(SelectEvent event) {
        getUiHandlers().save();
      }
    });
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
    framedPanel.setCollapsible(false);
    framedPanel.setPixelSize(400, 300);

    grid = new TreeGrid<CategoryJso>(treeStore, columnModel, colName);
    grid.setBorders(true);
    grid.setTreeLoader(loader);
    grid.getView().setTrackMouseOver(false);
    grid.getView().setAutoExpandColumn(colName);
    framedPanel.setWidget(grid);
    
    // Editor
    GridInlineEditing<CategoryJso> editing = new GridInlineEditing<CategoryJso>(grid);
    editing.addEditor(colName, new TextField());
    editing.addCompleteEditHandler(new CompleteEditHandler<CategoryJso>() {
      @Override
      public void onCompleteEdit(CompleteEditEvent<CategoryJso> event) {
        getUiHandlers().save();
      }
    });
    
    container.add(framedPanel);
  }

  @Override
  public void add(CategoryJso categoryJso) {
    treeStore.add(categoryJso);
  }
  
}

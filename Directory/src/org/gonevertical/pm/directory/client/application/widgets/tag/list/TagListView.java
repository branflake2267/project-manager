package org.gonevertical.pm.directory.client.application.widgets.tag.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.TagJso;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style.SelectionMode;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
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
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class TagListView extends ViewWithUiHandlers<TagListUiHandlers> implements
    TagListPresenter.MyView {

  private static final int PAGE_SIZE = 20;
  
  public interface Binder extends UiBinder<Widget, TagListView> {
  }

  @UiField
  SimpleContainer container;

  private Grid<TagJso> grid;
  private ColumnConfig<TagJso, String> colName;
  private ToolBar toolBar;
  private TagProperties tagProperties;
  private ListStore<TagJso> listStore;

  @Inject
  public TagListView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

  public void init(TagProperties tagProperties, ListStore<TagJso> listStore, PagingLoader<PagingLoadConfig, PagingLoadResult<TagJso>> pagingLoader) {
    this.listStore = listStore;
    this.tagProperties = tagProperties;
    
    // Toolbar
    final PagingToolBar toolBar = new PagingToolBar(PAGE_SIZE);
    toolBar.getElement().getStyle().setProperty("borderBottom", "none");
    toolBar.bind(pagingLoader);

    // Name Column
    colName = new ColumnConfig<TagJso, String>(tagProperties.name(), 100, "Name");
    
    // Columns
    List<ColumnConfig<TagJso, ?>> columnConfigList = new ArrayList<ColumnConfig<TagJso, ?>>();
    columnConfigList.add(colName);
    ColumnModel<TagJso> columnModel = new ColumnModel<TagJso>(columnConfigList);
    
    // Grid
    grid = new Grid<TagJso>(listStore, columnModel) {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            getUiHandlers().load();
          }
        });
      }
    };
    
    // Grid properties
    grid.setLoader(pagingLoader);
    grid.getView().setForceFit(true);
    grid.setLoadMask(true);
    grid.setBorders(true);
    grid.getView().setAutoExpandColumn(colName);

    // Frame
    FramedPanel framedPanel = new FramedPanel();
    framedPanel.setCollapsible(false);
    framedPanel.setHeadingText("Tags");
    framedPanel.setPixelSize(400, 300);

    // Layout Container
    VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();
    verticalLayoutContainer.setBorders(true);
    verticalLayoutContainer.add(getToolbarWidget(), new VerticalLayoutData(1, -1));
    verticalLayoutContainer.add(grid, new VerticalLayoutData(1, 1));
    verticalLayoutContainer.add(toolBar, new VerticalLayoutData(1, -1));
    
    framedPanel.setWidget(verticalLayoutContainer);

    container.add(framedPanel);
  }
  
  @Override
  public void edit(boolean edit) {
    if (edit) {
      // Editor
      GridInlineEditing<TagJso> editing = new GridInlineEditing<TagJso>(grid);
      editing.addEditor(colName, new TextField());
      editing.addCompleteEditHandler(new CompleteEditHandler<TagJso>() {
        @Override
        public void onCompleteEdit(CompleteEditEvent<TagJso> event) {
          getUiHandlers().save();
        }
      });

      toolBar.setVisible(true);
      
      grid.setSelectionModel(null);
    } else {
      // selection
      IdentityValueProvider<TagJso> identity = new IdentityValueProvider<TagJso>();
      CheckBoxSelectionModel<TagJso> sm = new CheckBoxSelectionModel<TagJso>(identity);
      sm.setSelectionMode(SelectionMode.SIMPLE);
      sm.addSelectionHandler(new SelectionHandler<TagJso>() {
        @Override
        public void onSelection(SelectionEvent<TagJso> event) {
          TagJso selected = event.getSelectedItem();
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
  public void add(TagJso tagJso) {
    listStore.add(tagJso);
  }
}

package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent.SelectionChangedHandler;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class ArchetypeListView extends ViewWithUiHandlers<ArchtypeListUiHandlers> implements
    ArchetypeListPresenter.MyView {
  private static final int PAGE_SIZE = 15;

  public interface Binder extends UiBinder<SimpleContainer, ArchetypeListView> {
  }

  @UiField
  SimpleContainer container;

  private final ArchetypeProperties columnProperties;
  
  private Grid<ArchetypeJso> grid;

  @Inject
  public ArchetypeListView(Binder binder, ArchetypeProperties columnProperties) {
    this.columnProperties = columnProperties;

    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void init(ListStore<ArchetypeJso> listStore, PagingLoader<PagingLoadConfig, 
      PagingLoadResult<ArchetypeJso>> pagingLoader) {
    initGrid(listStore, pagingLoader);
  }

  private void initGrid(ListStore<ArchetypeJso> listStore, 
      PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> pagingLoader) {
    // Toolbar
    final PagingToolBar toolBar = new PagingToolBar(PAGE_SIZE);
    toolBar.getElement().getStyle().setProperty("borderBottom", "none");
    toolBar.bind(pagingLoader);

    // Name Column
    ColumnConfig<ArchetypeJso, String> nameColumn = new ColumnConfig<ArchetypeJso, String>(columnProperties.name(),
        100, "Name");

    // Columns
    List<ColumnConfig<ArchetypeJso, ?>> columnConfigList = new ArrayList<ColumnConfig<ArchetypeJso, ?>>();
    columnConfigList.add(nameColumn);
    ColumnModel<ArchetypeJso> columnModel = new ColumnModel<ArchetypeJso>(columnConfigList);
    
    // Grid
    grid = new Grid<ArchetypeJso>(listStore, columnModel) {
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
    
    // Selection
    grid.getSelectionModel().addSelectionChangedHandler(new SelectionChangedHandler<ArchetypeJso>() {
      @Override
      public void onSelectionChanged(SelectionChangedEvent<ArchetypeJso> event) {
        if (event.getSelection().size() > 0) {
          getUiHandlers().gotoEdit(event.getSelection().get(0));  
        }
      }
    });
    
    // Grid properties
    grid.setLoader(pagingLoader);
    grid.getView().setForceFit(true);
    grid.setLoadMask(true);
    grid.setBorders(true);
    grid.getView().setAutoExpandColumn(nameColumn);

    // Frame
    FramedPanel framedPanel = new FramedPanel();
    framedPanel.setCollapsible(false);
    framedPanel.setHeadingText("Archetypes");
    framedPanel.setPixelSize(500, 427);

    // Layout Container
    VerticalLayoutContainer vpLayoutCont = new VerticalLayoutContainer();
    vpLayoutCont.setBorders(true);
    vpLayoutCont.add(grid, new VerticalLayoutData(1, 1));
    vpLayoutCont.add(toolBar, new VerticalLayoutData(1, -1));
    framedPanel.setWidget(vpLayoutCont);

    container.add(framedPanel);
  }

  @Override
  public void updateSelected(ArchetypeJso archetypeJso) {
    // TODO instead of reloading, maybe just update the record
    grid.getView().refresh(false);
  }

}

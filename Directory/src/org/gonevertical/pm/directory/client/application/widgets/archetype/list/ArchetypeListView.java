package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.RefreshEvent;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class ArchetypeListView extends ViewWithUiHandlers<ArchtypeListUiHandlers> implements
    ArchetypeListPresenter.MyView {
  private static final int PAGE_SIZE = 15;

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeListView> {
  }

  @UiField
  FlowPanel tableContanier;
  
  private boolean intialized = false;

  private ArchetypeColumnProperties columnProperties;

  @Inject
  public ArchetypeListView(final Binder binder, ArchetypeColumnProperties columnProperties) {
    this.columnProperties = columnProperties;
    
    initWidget(binder.createAndBindUi(this));
  }

  @Override
  public void init() {
    if (intialized) {
      return;
    }
    intialized = true;

    initTable();
  }

  private void initTable() {
    RpcProxy<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<ArchetypeJso>>() {
      @Override
      public void load(PagingLoadConfig loadConfig, AsyncCallback<PagingLoadResult<ArchetypeJso>> callback) {
        getUiHandlers().fetchArchetypes(loadConfig.getOffset(), loadConfig.getLimit(), callback);
      }
    };

    ListStore<ArchetypeJso> store = new ListStore<ArchetypeJso>(new ModelKeyProvider<ArchetypeJso>() {
      @Override
      public String getKey(ArchetypeJso item) {
        return item.getKey();
      }
    });

    final PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<ArchetypeJso>>(proxy);
    loader.setRemoteSort(true);
    loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, ArchetypeJso, PagingLoadResult<ArchetypeJso>>(store));

    final PagingToolBar toolBar = new PagingToolBar(PAGE_SIZE);
    toolBar.getElement().getStyle().setProperty("borderBottom", "none");
    toolBar.bind(loader);

    IdentityValueProvider<ArchetypeJso> identity = new IdentityValueProvider<ArchetypeJso>();
    final CheckBoxSelectionModel<ArchetypeJso> sm = new CheckBoxSelectionModel<ArchetypeJso>(identity) {
      @Override
      protected void onRefresh(RefreshEvent event) {
        // this code selects all rows when paging if the header checkbox is selected
        if (isSelectAllChecked()) {
          selectAll();
        }
        super.onRefresh(event);
      }
    };

    ColumnConfig<ArchetypeJso, String> nameColumn = new ColumnConfig<ArchetypeJso, String>(columnProperties.name(), 100, "Name");

    List<ColumnConfig<ArchetypeJso, ?>> l = new ArrayList<ColumnConfig<ArchetypeJso, ?>>();
    l.add(sm.getColumn());
    l.add(nameColumn);

    ColumnModel<ArchetypeJso> cm = new ColumnModel<ArchetypeJso>(l);

    Grid<ArchetypeJso> grid = new Grid<ArchetypeJso>(store, cm) {
      @Override
      protected void onAfterFirstAttach() {
        super.onAfterFirstAttach();
        Scheduler.get().scheduleDeferred(new ScheduledCommand() {
          @Override
          public void execute() {
            loader.load();
          }
        });
      }
    };
    grid.setSelectionModel(sm);
    grid.getView().setForceFit(true);
    grid.setLoadMask(true);
    grid.setLoader(loader);

    FramedPanel framedPanel = new FramedPanel();
    framedPanel.setCollapsible(true);
    framedPanel.setHeadingText("Paging Grid Example");
    framedPanel.setPixelSize(500, 400);
    framedPanel.addStyleName("margin-10");

    VerticalLayoutContainer vpLayoutCont = new VerticalLayoutContainer();
    vpLayoutCont.setBorders(true);
    vpLayoutCont.add(grid, new VerticalLayoutData(1, 1));
    vpLayoutCont.add(toolBar, new VerticalLayoutData(1, -1));
    framedPanel.setWidget(vpLayoutCont);

    tableContanier.add(framedPanel);
  }

  @Override
  public void updateSelected(ArchetypeJso archetypeJso) {
    // TODO reload tableContanier and select
    // TODO update row data

  }

}

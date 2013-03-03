package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeListView extends ViewWithUiHandlers<ArchtypeListUiHandlers> implements ArchetypeListPresenter.MyView {
  private static final int PAGE_SIZE = 15;
  
  public interface Binder extends UiBinder<HTMLPanel, ArchetypeListView> {
  }

  @UiField(provided = true)
  CellTable<ArchetypeJso> archetypeCellTable = new CellTable<ArchetypeJso>();
  @UiField(provided = true)
  SimplePager pager = new SimplePager();
 
  private boolean intialized = false;
  private AsyncDataProvider<ArchetypeJso> dataProvider;
  private SingleSelectionModel<ArchetypeJso> selectionModel = new SingleSelectionModel<ArchetypeJso>();
  
  @Inject
  public ArchetypeListView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }
  
  @Override
  public void init() {
    if (intialized) {
      return;
    }
    intialized = true;
    
    initCellTable();
    initPager();
  }

  private void initPager() {
    pager.setPageSize(PAGE_SIZE);
    pager.setDisplay(archetypeCellTable);
  }

  private void initCellTable() {
    archetypeCellTable.setPageSize(PAGE_SIZE);
    archetypeCellTable.setHeight("500px");
    
    // name
    archetypeCellTable.addColumn(new TextColumn<ArchetypeJso>() {
      public String getValue(ArchetypeJso object) {
        String s = "";
        if (object.getName() != null) {
          s = object.getName();
        }
        return s;
      }
    }, "Name");
    
    dataProvider = new AsyncDataProvider<ArchetypeJso>() {
      @Override
      protected void onRangeChanged(HasData<ArchetypeJso> display) {
        Range range = display.getVisibleRange();
        getUiHandlers().fetchArchetypes(range.getStart(), range.getLength());
      }
    };
    dataProvider.addDataDisplay(archetypeCellTable);
    
    archetypeCellTable.setSelectionModel(selectionModel);
    selectionModel.addSelectionChangeHandler(new Handler() {
      public void onSelectionChange(SelectionChangeEvent event) {
        getUiHandlers().gotoEdit(selectionModel.getSelectedObject());
      }
    });
  }
  
  @Override
  public void displayArchetypes(int start, RestList<ArchetypeJso> list) {
    dataProvider.updateRowData(start, list.getList());
  }

}

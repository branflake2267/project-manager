package org.gonevertical.pm.directory.client.rest.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class RestList<T extends JavaScriptObject> {

  private int total;
  private String cursor;  
  private JsArray<T> list;
  
  public RestList(int total, JsArray<T> list, String cursor) {
    this.total = total;
    this.list = list;
    this.cursor = cursor;
  }
  
  public int getTotal() {
    return total;
  }

  public String getCursor() {
    return cursor;
  }

  public List<T> getList() {
    if (list == null) {
      return null;
    }
    List<T> alist = new ArrayList<T>();
    for (int i=0; i< list.length(); i++) {
      alist.add(list.get(i));
    }
    return alist;
  }
  
  public JsArray<T> getJsArray() {
    return list;
  }

  /**
   * Convert to gxt PageLoadResult for grid
   */
  public PagingLoadResult<T> getPageLoadResult(int offset) {
    return new PagingLoadResultBean<T>(getList(), total, offset);
  }
  
}

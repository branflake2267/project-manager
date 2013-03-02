package org.gonevertical.pm.directory.client.rest.util;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class RestList<T extends JavaScriptObject> {

  private String cursor;  
  private JsArray<T> list;
  
  public RestList(JsArray<T> list, String cursor) {
    this.list = list;
    this.cursor = cursor;
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
  
}

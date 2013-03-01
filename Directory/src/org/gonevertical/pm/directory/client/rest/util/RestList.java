package org.gonevertical.pm.directory.client.rest.util;

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

  public JsArray<T> getList() {
    return list;
  }
  
}

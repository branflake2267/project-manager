package org.gonevertical.pm.directory.client.rest.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class CategoryJso extends JavaScriptObject {

  protected CategoryJso() {
  }
  
  public native void setParent(String parent) /*-{
    this.parent = parent;
  }-*/;
  
  public native String getParent() /*-{
    return this.parent;
  }-*/;
  
  public native void setKey(String key) /*-{
    this.key = key;
  }-*/;

  public native String getKey() /*-{
    return this.key;
  }-*/;
  
  public native void setName(String name) /*-{
    this.name = name;
  }-*/;

}

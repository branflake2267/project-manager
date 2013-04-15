package org.gonevertical.pm.directory.client.rest.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class CategoryJso extends JavaScriptObject {

  protected CategoryJso() {
  }
  
  public final native void setParent(String parent) /*-{
    this.parent = parent;
  }-*/;
  
  public final native String getParent() /*-{
    return this.parent;
  }-*/;
  
  public final native void setKey(String key) /*-{
    this.key = key;
  }-*/;

  public final native String getKey() /*-{
    return this.key;
  }-*/;
  
  public final native void setName(String name) /*-{
    this.name = name;
  }-*/;
  
  public final native String getName() /*-{
    return this.name;
  }-*/;

  public final native Boolean hasChildren() /*-{
    return this.hasChildren;
  }-*/;

}

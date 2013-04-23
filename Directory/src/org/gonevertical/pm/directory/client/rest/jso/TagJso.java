package org.gonevertical.pm.directory.client.rest.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class TagJso extends JavaScriptObject {
  
  protected TagJso() {
  }
  
  public final native String getKey() /*-{
    return this.key;
  }-*/;

  public final native void setName(String name) /*-{
    this.name = name;
  }-*/;

  public final native String getName() /*-{
    return this.name;
  }-*/;

}

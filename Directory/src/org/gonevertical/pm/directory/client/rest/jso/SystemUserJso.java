package org.gonevertical.pm.directory.client.rest.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class SystemUserJso extends JavaScriptObject {

  protected SystemUserJso() {
  }

  public final native String getKey() /*-{
    return this.key;
  }-*/;

  public final native String getGoogleId() /*-{
    return this.googleId;
  }-*/;

}

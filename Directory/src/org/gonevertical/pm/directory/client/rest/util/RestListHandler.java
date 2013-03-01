package org.gonevertical.pm.directory.client.rest.util;

import com.google.gwt.core.client.JavaScriptObject;

public interface RestListHandler<T extends JavaScriptObject> {

  void onSuccess(RestList<T> list);
  
  void onFailure(Throwable e);
  
}

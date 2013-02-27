package org.gonevertical.pm.directory.client.rest.util;

import com.google.gwt.core.client.JavaScriptObject;

public interface RestHandler<T extends JavaScriptObject> {

  void onSuccess(T object);
  
  void onFailure(Throwable e);
  
}

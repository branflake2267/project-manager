package org.gonevertical.pm.directory.client.rest.util;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Extending GXT callback for using this in gxt load providers 
 */
public interface RestHandler<T extends JavaScriptObject> extends AsyncCallback<T> {

  void onSuccess(T object);
  
  void onFailure(Throwable e);
  
}

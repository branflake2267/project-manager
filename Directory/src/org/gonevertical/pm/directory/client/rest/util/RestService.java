package org.gonevertical.pm.directory.client.rest.util;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONParser;

public abstract class RestService<T extends JavaScriptObject> {
  
  private String getUrl(String url) {
    return GWT.getModuleBaseURL() + url;
  }

  protected void get(String url, final RestHandler<T> handler) {
    url = getUrl(url);
    
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
    builder.setCallback(new RequestCallback() {
      @Override
      public void onResponseReceived(Request request, Response response) {
        if (response.getStatusCode() == 200) {
          String json = response.getText();
          T jso = (T) JSONParser.parseLenient(json).isObject().getJavaScriptObject().cast();
          handler.onSuccess(jso);
        } else {
          handler.onFailure(new Exception("Exception: getStatusText=" + response.getStatusText()));
        }
      }

      @Override
      public void onError(Request request, Throwable e) {
        handler.onFailure(e);
      }
    });
    builder.setHeader(RestHeaders.ACCEPT__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());

    try {
      builder.send();
    } catch (RequestException e) {
      handler.onFailure(e);
      e.printStackTrace();
    }
  }

  public void put() {

  }

}

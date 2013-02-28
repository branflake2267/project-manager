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
    return GWT.getHostPageBaseURL() + url;
  }

  protected void get(String url, final RestHandler<T> handler) {
    url = getUrl(url);
    
    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
    builder.setHeader(RestHeaders.CONTENT_TYPE__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    builder.setHeader(RestHeaders.ACCEPT__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    
    builder.setCallback(new RequestCallback() {
      @Override
      public void onResponseReceived(Request request, Response response) {
        String json = response.getText();
        
        System.out.println("json=" + json);
        
        if (response.getStatusCode() == 200) {
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

package org.gonevertical.pm.directory.client.rest.util;

import java.util.HashMap;
import java.util.Set;

import org.gonevertical.pm.directory.client.rest.SystemProperties;
import org.gonevertical.pm.directory.client.security.OAuthToken;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.user.client.Timer;

public abstract class RestService<T extends JavaScriptObject> {
  
  private String endpointPath = "";
  private OAuthToken oauth;
  
  protected RestService(String endpointPath, OAuthToken oauth) {
    this.endpointPath = endpointPath;
    this.oauth = oauth;
  }
  
  public void setEndpointPath(String endppointPath) {
    this.endpointPath = endppointPath;
  }
  
  public String getBaseAndEndpointPath() {
    String url = GWT.getHostPageBaseURL() + endpointPath;
    if (SystemProperties.getDebugRemote()) {
      url = "https://project-directory.appspot.com/" + endpointPath;
    }
    return url;
  }

  public void get(HashMap<String, String> parameters,  final RestHandler<T> handler) {
    final String url = getBaseAndEndpointPath() + getQueryString(parameters);

    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
    builder.setHeader(RestHeaders.CONTENT_TYPE__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    builder.setHeader(RestHeaders.ACCEPT__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    
    if (oauth.getAccessToken() != null && oauth.getAccessToken().trim().length() > 0) {
      builder.setHeader("Authorization", "Bearer " + oauth.getAccessToken());
    } 

    call(url, builder, handler, new CallNumber());
  }
   
  private void call(final String url, final RequestBuilder builder, final RestHandler<T> handler, final CallNumber callNumber) {
    builder.setCallback(new RequestCallback() {
      @Override
      public void onResponseReceived(Request request, Response response) {
        String json = response.getText();
        if (response.getStatusCode() == 200) {
          T jso = (T) JSONParser.parseLenient(json).isObject().getJavaScriptObject().cast();
          handler.onSuccess(jso);
        } else {
          callNumber.add();
          handler.onFailure(new Exception("Exception: url=" + url + " getStatusText=" + response.getStatusText() + " text=" + response.getText()));
          if (callNumber.get() > 3) {
            Timer t = new Timer() {
              @Override
              public void run() {
                System.out.println("calling again + " + callNumber.get());
                call(url, builder, handler, callNumber);
              }
            };
            t.schedule(500 * callNumber.get() + 1);
          }
        }
      }

      @Override
      public void onError(Request request, Throwable e) {
        handler.onFailure(e);
        callNumber.add();
        if (callNumber.get() > 3) {
          Timer t = new Timer() {
            @Override
            public void run() {
              System.out.println("calling again + " + callNumber.get());
              call(url, builder, handler, callNumber);
            }
          };
          t.schedule(500 * callNumber.get() + 1);
        }
      }
    });

    try {
      builder.send();
    } catch (RequestException e) {
      handler.onFailure(e);
      e.printStackTrace();
    }
  }

  public void getList(HashMap<String, String> parameters, final RestListHandler<T> handler) {
    String url = getBaseAndEndpointPath() + getQueryString(parameters);

    RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
    builder.setHeader(RestHeaders.CONTENT_TYPE__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    builder.setHeader(RestHeaders.ACCEPT__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    
    if (oauth.getAccessToken() != null && oauth.getAccessToken().trim().length() > 0) {
      builder.setHeader("Authorization", "Bearer " + oauth.getAccessToken());
    }

    builder.setCallback(new RequestCallback() {
      @Override
      public void onResponseReceived(Request request, Response response) {
        String json = response.getText();
        if (response.getStatusCode() == 200) {
          JSONObject jsonObject = JSONParser.parseLenient(json).isObject();

          String cursor = null;
          try {
            cursor = jsonObject.get("nextPageToken").isString().stringValue();
          } catch (Exception e) {
          }
          
          int total = -1;
          try {
            total = (int) jsonObject.get("total").isNumber().doubleValue();
          } catch (Exception e) {
          }

          JsArray<T> list = null;
          try {
            list = (JsArray<T>) JSONParser.parseLenient(json).isObject().get("items").isArray().getJavaScriptObject().cast();
          } catch (Exception e) {
          }

          handler.onSuccess(new RestList(total, list, cursor));
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

  public void put(T object, final RestHandler<T> handler) {
    String url = getBaseAndEndpointPath();
    
    String json = new JSONObject(object).toString();

    RequestBuilder builder = new RequestBuilder(RequestBuilder.PUT, url);
    builder.setHeader(RestHeaders.CONTENT_TYPE__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    builder.setHeader(RestHeaders.ACCEPT__JSON.getKey(), RestHeaders.ACCEPT__JSON.getValue());
    
    if (oauth.getAccessToken() != null && oauth.getAccessToken().trim().length() > 0) {
      builder.setHeader("Authorization", "Bearer " + oauth.getAccessToken());
    }
    
    builder.setRequestData(json);
    
    builder.setCallback(new RequestCallback() {
      @Override
      public void onResponseReceived(Request request, Response response) {
        String json = response.getText();
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

  private String getQueryString(HashMap<String, String> parameters) {
    if (parameters == null) {
      return "";
    }

    String qs = "";

    Set<String> keys = parameters.keySet();

    if (keys.size() > 0) {
      qs = "?";
    }

    int i = 0;
    for (String key : keys) {
      qs += key + "=" + parameters.get(key);
      if (i <= keys.size() - 1) {
        qs += "&";
      }
      i++;
    }

    return qs;
  }
}

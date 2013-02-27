package org.gonevertical.pm.directory.client.rest.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class CurrentUserJso extends JavaScriptObject {
  
  protected CurrentUserJso() {
  }
  
  public final native boolean getIsAdmin() /*-{
    return this.isAdmin;
  }-*/;
  
  private final native void setIsAdmin(boolean isAdmin) /*-{
    this.isAdmin = isAdmin;
  }-*/;
  
  public final native boolean getIsLoggedIn() /*-{
    return this.isLoggedIn;
  }-*/;
  
  public final native void setIsLoggedIn(boolean isLoggedIn) /*-{
    this.isLoggedIn = isLoggedIn;
  }-*/;
  
  public final native SystemUserJso getSystemUser() /*-{
    return this.systemUser;
  }-*/;
  
  public final native void setSystemUser(SystemUserJso systemUserJso) /*-{
    this.systemUser = systemUser;
  }-*/;
  
  public final native String getLogoutUrl() /*-{
    return this.logoutUrl;
  }-*/;
  
  public final native void setLogoutUrl(String logoutUrl) /*-{
    this.logoutUrl = logoutUrl;
  }-*/;
  
  public final native String getLoginUrl() /*-{
    return this.loginUrl;
  }-*/;
  
  public final native void setLoginUrl(String loginUrl) /*-{
    this.loginUrl = loginUrl;
  }-*/;
  
  public final native String getNickname() /*-{
    return this.nickname;
  }-*/;
  
  public final native void setNickname(String nickname) /*-{
    this.nickname = nickname;
  }-*/;

  public final void copyFrom(CurrentUserJso currentUserJso) {
    setIsAdmin(currentUserJso.getIsAdmin());
    setIsLoggedIn(currentUserJso.getIsLoggedIn());
    setSystemUser(currentUserJso.getSystemUser());
    setLogoutUrl(currentUserJso.getLogoutUrl());
    setLoginUrl(currentUserJso.getLoginUrl());
    setNickname(currentUserJso.getNickname());
  }
  
}

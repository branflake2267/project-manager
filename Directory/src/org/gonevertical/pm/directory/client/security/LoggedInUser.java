package org.gonevertical.pm.directory.client.security;

import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.jso.SystemUserJso;

public class LoggedInUser {
  
  private boolean isLoggedIn;
  private String logoutUrl;
  private String loginUrl;
  private String nickname;
  private SystemUserJso systemUser;
  
  public boolean getIsAdmin() {
    boolean admin = false;
    if (systemUser != null) {
      admin = systemUser.getIsAdmin();
    }
    return admin;
  }
  
  public boolean getIsLoggedIn() {
    return isLoggedIn;
  }

  public SystemUserJso getSystemUser() {
    return systemUser;
  }

  public String getLogoutUrl() {
    return logoutUrl;
  }

  public String getLoginUrl() {
    return loginUrl;
  }

  public String getNickname() {
    return nickname;
  }

  public void copyFrom(CurrentUserJso currentUserJso) {
    isLoggedIn = currentUserJso.getIsLoggedIn();
    logoutUrl = currentUserJso.getLogoutUrl();
    loginUrl = currentUserJso.getLoginUrl();
    
    if (isLoggedIn) {
      systemUser = currentUserJso.getSystemUser();
      nickname = currentUserJso.getNickname();
    }
  }

}

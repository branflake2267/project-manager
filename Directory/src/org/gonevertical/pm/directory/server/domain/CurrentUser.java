package org.gonevertical.pm.directory.server.domain;

public class CurrentUser {
  
  private boolean isLoggedIn;
  private String logoutUrl;
  private String loginUrl;
  private String nickname;
  private SystemUser systemUser;

  public CurrentUser() {
    isLoggedIn = false;
    systemUser = new SystemUser();
    loginUrl = "";
    logoutUrl = "";
  }

  public CurrentUser(Boolean isLoggedIn) {
    this.isLoggedIn = isLoggedIn;
  }

  public Boolean getIsLoggedIn() {
    return isLoggedIn;
  }

  public void setSystemUser(SystemUser systemUser) {
    this.systemUser = systemUser;
  }
  
  public SystemUser getSystemUser() {
    return systemUser;
  }

  public void setLogoutUrl(String logoutUrl) {
    this.logoutUrl = logoutUrl;
  }

  public String getLogoutUrl() {
    return logoutUrl;
  }

  public void setLoginUrl(String loginUrl) {
    this.loginUrl = loginUrl;
  }

  public String getLoginUrl() {
    return loginUrl;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getNickname() {
    return nickname;
  }
  
  public Boolean getIsAdmin() {
    boolean admin = false;
    if (systemUser != null) {
      admin = systemUser.getIsAdmin();
    }
    return admin;
  }

  public void copyFrom(CurrentUser currentUser) {
    isLoggedIn = currentUser.isLoggedIn;
    systemUser = currentUser.systemUser;
    logoutUrl = currentUser.logoutUrl;
    loginUrl = currentUser.loginUrl;
    nickname = currentUser.nickname;
  }

  @Override
  public String toString() {
    String s = "CurrentUser(";
    s += "isAdmin=" + getIsAdmin() + ", ";
    s += "isLoggedIn=" + isLoggedIn + ", ";
    s += "systemUser=" + systemUser + ", ";
    s += "nickname=" + nickname;
    s += ")";
    return s;
  }

}

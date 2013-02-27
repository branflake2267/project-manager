package org.gonevertical.pm.directory.server.domain.dto;

public class CurrentUser {
    private boolean isAdmin;
    private boolean isLoggedIn;
    private SystemUser systemUser;
    private String logoutUrl;
    private String loginUrl;
    private String nickname;

    public CurrentUser() {
    }

    public Boolean getIsAdmin() {
      return isAdmin;
    }

    public Boolean getIsLoggedIn() {
      return isLoggedIn;
    }

    public SystemUser getSystemUser() {
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
    
}

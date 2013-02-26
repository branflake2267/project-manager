package org.gonevertical.pm.directory.server.domain;

public class CurrentUser {
    private Boolean isAdmin;
    private Boolean isLoggedIn;
    private SystemUser systemUser;
    private String logoutUrl;
    private String loginUrl;
    private String nickname;

    public CurrentUser() {
        isAdmin = false;
        isLoggedIn = false;
        systemUser = new SystemUser();
        loginUrl = "";
        logoutUrl = "";
    }

    public CurrentUser(Boolean isLoggedIn, SystemUser systemUser) {
        this.isLoggedIn = isLoggedIn;
        this.systemUser = systemUser;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }
    
    public Boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public SystemUser getUser() {
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

    public void copyFrom(CurrentUser currentUser) {
        isAdmin = currentUser.isAdmin;
        isLoggedIn = currentUser.isLoggedIn;
        systemUser = currentUser.systemUser;
        logoutUrl = currentUser.logoutUrl;
        loginUrl = currentUser.loginUrl;
        nickname = currentUser.nickname;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

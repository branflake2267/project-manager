package org.gonevertical.pm.directory.server.domain.dto;

public class CurrentUser {
    private Boolean isAdmin;
    private Boolean isLoggedIn;
    private User user;
    private String logoutUrl;
    private String loginUrl;
    private String nickname;

    public CurrentUser() {
        isAdmin = false;
        isLoggedIn = false;
        user = new User();
        loginUrl = "";
        logoutUrl = "";
    }

    public CurrentUser(Boolean isLoggedIn, User user) {
        this.isLoggedIn = isLoggedIn;
        this.user = user;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }
    
    public Boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public User getUser() {
        return user;
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
        user = currentUser.user;
        logoutUrl = currentUser.logoutUrl;
        loginUrl = currentUser.loginUrl;
        nickname = currentUser.nickname;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

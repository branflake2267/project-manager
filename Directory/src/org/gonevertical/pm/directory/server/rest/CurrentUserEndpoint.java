package org.gonevertical.pm.directory.server.rest;

import org.gonevertical.pm.directory.server.domain.CurrentUser;
import org.gonevertical.pm.directory.server.domain.SystemUser;

import com.google.api.server.spi.config.Api;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Api(name = "currentuserendpoint", version = "v1")
public class CurrentUserEndpoint {
  
  private final UserService userService = UserServiceFactory.getUserService();
  private final SystemUserEndpoint systemUserEndpoint = new SystemUserEndpoint();

  public CurrentUser getCurrentUser() {
    Boolean isLoggedIn = userService.isUserLoggedIn();

    CurrentUser currentUser = new CurrentUser(isLoggedIn, getUser());
    currentUser.setLogoutUrl(userService.createLogoutURL("/"));
    currentUser.setLoginUrl(userService.createLoginURL("/"));

    if (isLoggedIn) {
      currentUser.setIsAdmin(userService.isUserAdmin());
      currentUser.setNickname(userService.getCurrentUser().getNickname());
    }

    return currentUser;
  }

  private SystemUser getUser() {
    Boolean isLoggedIn = userService.isUserLoggedIn();

    SystemUser systemUser = new SystemUser();
    if (isLoggedIn) {
      String googleId = userService.getCurrentUser().getUserId();

      systemUser = systemUserEndpoint.findByGoogleId(googleId);
      if (systemUser == null) {
        systemUser = createSystemUser(googleId);
      }
    }
    
    if (systemUser == null) {
      systemUser = new SystemUser();
    }
    
    return systemUser;
  }

  private SystemUser createSystemUser(String googleId) {
    SystemUser systemUser = new SystemUser();
    systemUser.setGoogleId(googleId);
    
    return systemUserEndpoint.updateSystemUser(systemUser);
  }

}

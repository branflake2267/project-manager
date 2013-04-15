package org.gonevertical.pm.directory.server.rest;

import javax.annotation.Nullable;

import org.gonevertical.pm.directory.server.domain.Session;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.inject.name.Named;

//@Api(name = "sessionendpoint", version = "v1")
public class SessionEndpoint {
  private UserService userService = UserServiceFactory.getUserService();

  public Session getSession(@Named("url") @Nullable String url) {
    String surl = userService.createLoginURL("/");
    if (url != null) {
      surl = userService.createLoginURL(url);
    }

    Session session = new Session();
    session.setLoginUrl(surl);
    return session;
  }

}

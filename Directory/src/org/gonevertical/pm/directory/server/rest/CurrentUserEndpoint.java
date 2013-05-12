package org.gonevertical.pm.directory.server.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import org.gonevertical.pm.directory.client.rest.SystemProperties;
import org.gonevertical.pm.directory.server.domain.CurrentUser;
import org.gonevertical.pm.directory.server.domain.dao.JdoUtils;

import com.google.api.server.spi.config.Api;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@Api(
    name = "currentuserendpoint",
    version = SystemProperties.VERSION,
    clientIds = { "433818979508.apps.googleusercontent.com" },
    scopes = { "https://www.googleapis.com/auth/userinfo.email" })
public class CurrentUserEndpoint {

  private static final Logger logger = Logger.getLogger(CurrentUserEndpoint.class.getSimpleName());

  private final UserService userService = UserServiceFactory.getUserService();

  public CurrentUser getCurrentUser(@Nullable @Named("siteUrl") String siteUrl,
      com.google.appengine.api.users.User guser) {
    siteUrl = encodeUrl(siteUrl);

    logger.info("CurrentUserEndpoint.getCurrentUser(): encoded siteUrl=" + siteUrl);

    CurrentUser currentUser = JdoUtils.getCurrentUser(guser);
    currentUser.setLoginUrl(getLoginString(siteUrl));
    currentUser.setLogoutUrl(userService.createLogoutURL("/"));

    logger.info("CurrentUserEndpoint.getCurrentUser(): currentUser=" + currentUser);

    // TODO remove
    logger.info("*******URL=" + getLoginString(siteUrl));

    return currentUser;
  }

  private String encodeUrl(String siteUrl) {
    try {
      siteUrl = URLEncoder.encode(siteUrl, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    return siteUrl;
  }

  private String getLoginString(String siteUrl) {
    if (siteUrl == null) {
      return "";
    }

    String oauthurl = "";
    oauthurl += "https://accounts.google.com/o/oauth2/auth";
    oauthurl += "?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email";
    oauthurl += "&state=login";
    oauthurl += "&redirect_uri=" + siteUrl;
    oauthurl += "&response_type=token";
    oauthurl += "&client_id=433818979508.apps.googleusercontent.com";
    // oauthurl += "&approval_prompt=force";
    return oauthurl;
  }

}

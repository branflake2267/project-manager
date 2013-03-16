package org.gonevertical.pm.directory.client.application.widgets.login;

import java.util.HashMap;

import org.gonevertical.pm.directory.client.events.login.LoggedInEvent;
import org.gonevertical.pm.directory.client.rest.CurrentUserJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;
import org.gonevertical.pm.directory.client.security.LoggedInUser;
import org.gonevertical.pm.directory.client.security.OAuthToken;
import org.gonevertical.pm.directory.client.utils.QueryStringData;
import org.gonevertical.pm.directory.client.utils.QueryStringUtils;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class LoginPresenter extends PresenterWidget<LoginPresenter.MyView> implements LoginUiHandlers {

  public interface MyView extends View, HasUiHandlers<LoginUiHandlers> {
    void displayLoggedIn(String string);

    void displayLoggedOut(String string);

    void displayNickname(String nickname);
  }

  private final CurrentUserJsoDao currentUserJsoDao;
  private final LoggedInUser loggedInUser;
  private final OAuthToken oauth;

  @Inject
  public LoginPresenter(EventBus eventBus, MyView view, CurrentUserJsoDao currentUserJsoDao, LoggedInUser loggedInUser,
      OAuthToken oauth) {
    super(eventBus, view);

    this.currentUserJsoDao = currentUserJsoDao;
    this.loggedInUser = loggedInUser;
    this.oauth = oauth;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    initAccess_Token();

    fetchCurrentUser();
  }

  private void fetchCurrentUser() {
    HashMap<String, String> parameters = new HashMap<String, String>();
    parameters.put("siteUrl", getSiteUrl());

    currentUserJsoDao.get(parameters, new RestHandler<CurrentUserJso>() {
      @Override
      public void onSuccess(CurrentUserJso object) {
        onFetchCurrentUserSuccess(object);
      }

      @Override
      public void onFailure(Throwable e) {
        e.printStackTrace();
      }
    });
  }

  private void onFetchCurrentUserSuccess(CurrentUserJso currentUserJso) {
    loggedInUser.copyFrom(currentUserJso);

    displayLogin();

    LoggedInEvent.fire(this);
  }

  private void displayLogin() {
    if (loggedInUser.getIsLoggedIn()) {
      String url = replaceReturnPath(loggedInUser.getLogoutUrl());
      getView().displayNickname(loggedInUser.getNickname());
      getView().displayLoggedIn(url);
    } else {
      String url = loggedInUser.getLoginUrl();
      getView().displayNickname("");
      getView().displayLoggedOut(url);
    }
  }

  private String replaceReturnPath(String url) {
    String path = Window.Location.getPath();

    String queryString = Window.Location.getQueryString();
    String token = History.getToken();
    if (queryString != null) {
      url += URL.encode(queryString);
    }

    if (token != null && token.length() > 0) {
      url += "%23" + path + URL.encode(token);
    }

    return url;
  }

  private String getSiteUrl() {
    String s = GWT.getHostPageBaseURL() + Window.Location.getQueryString();
    s = URL.encodeQueryString(s);
    return s;
  }

  private void initAccess_Token() {
    QueryStringData qsd = QueryStringUtils.getQueryStringData();
    String access_token = qsd.getParameter_String("access_token");
    System.out.println("access_token=" + access_token);
    oauth.setAccessToken(access_token);
  }

}

package org.gonevertical.pm.directory.client.application.widgets.login;

import org.gonevertical.pm.directory.client.events.login.LoggedInEvent;
import org.gonevertical.pm.directory.client.rest.CurrentUserJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

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

  @Inject
  public LoginPresenter(final EventBus eventBus, final MyView view, final CurrentUserJsoDao currentUserJsoDao,
      final LoggedInUser loggedInUser) {
    super(eventBus, view);

    this.currentUserJsoDao = currentUserJsoDao;
    this.loggedInUser = loggedInUser;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    fetchCurrentUser();
  }

  private void fetchCurrentUser() {
    currentUserJsoDao.get(new RestHandler<CurrentUserJso>() {
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
      String url = replaceReturnPath(loggedInUser.getLoginUrl());
      getView().displayNickname("");
      getView().displayLoggedOut(url);
    }
  }

  private String replaceReturnPath(String url) {
    String path = Window.Location.getPath();
    if (path != null && path.length() > 0) {
      path = path.replace("/", "%2F");
      url = url.replace("%2F", path);
    }

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

}

/**
 * Copyright 2012 ArcBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.gonevertical.pm.directory.client.application.widgets.login;

import org.gonevertical.pm.directory.client.rest.CurrentUserJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;

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
  private final CurrentUserJso currentUserJso;

  @Inject
  public LoginPresenter(final EventBus eventBus, final MyView view, final CurrentUserJsoDao currentUserJsoDao,
      final CurrentUserJso currentUserJso) {
    super(eventBus, view);

    this.currentUserJsoDao = currentUserJsoDao;
    this.currentUserJso = currentUserJso;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    fetchCurrentUser();
  }

  private void fetchCurrentUser() {
    currentUserJsoDao.getCurrentUser(new RestHandler<CurrentUserJso>() {
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
    currentUserJso.copyFrom(currentUserJso);
    
    displayLogin();
  }

  private void displayLogin() {
    if (currentUserJso.getIsLoggedIn()) {
      String url = replaceReturnPath(currentUserJso.getLogoutUrl());
      getView().displayNickname(currentUserJso.getNickname());
      getView().displayLoggedIn(url);
    } else {
      String url = replaceReturnPath(currentUserJso.getLoginUrl());
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

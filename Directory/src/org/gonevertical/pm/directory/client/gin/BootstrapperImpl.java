package org.gonevertical.pm.directory.client.gin;

import javax.inject.Inject;

import org.gonevertical.pm.directory.client.rest.CurrentUserJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.CurrentUserJso;
import org.gonevertical.pm.directory.client.rest.util.RestHandler;

import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.annotations.Bootstrap;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

@Bootstrap
public class BootstrapperImpl implements Bootstrapper {

  private final PlaceManager placeManager;
  private final CurrentUserJso currentUser;
  private final CurrentUserJsoDao currentUserJsoDao;

  @Inject
  public BootstrapperImpl(final PlaceManager placeManager, final CurrentUserJsoDao currentUserJsoDao,
      final CurrentUserJso currentUser) {
    this.placeManager = placeManager;
    this.currentUserJsoDao = currentUserJsoDao;
    this.currentUser = currentUser;
  }

  @Override
  public void onBootstrap() {
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
    currentUser.copyFrom(currentUserJso);
    placeManager.revealCurrentPlace();
  }

}

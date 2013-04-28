package org.gonevertical.pm.directory.client.application.admin;

import org.gonevertical.pm.directory.client.application.ApplicationPresenter;
import org.gonevertical.pm.directory.client.application.widgets.category.list.CategoryListPresenter;
import org.gonevertical.pm.directory.client.place.NameTokens;
import org.gonevertical.pm.directory.client.security.LoggedInGatekeeper;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class AdminPresenter extends Presenter<AdminPresenter.MyView, AdminPresenter.MyProxy> {

  public interface MyView extends View {
  }

  public static final Object TYPE_CategoryPresenter = new Object();

  private final LoggedInUser loggedInuser;
  private final CategoryListPresenter categoryListPresenter;

  @ProxyStandard
  @NameToken(NameTokens.admin)
  @UseGatekeeper(LoggedInGatekeeper.class)
  public interface MyProxy extends ProxyPlace<AdminPresenter> {
  }

  @Inject
  public AdminPresenter(EventBus eventBus, MyView view, MyProxy proxy, LoggedInUser loggedInuser,
      CategoryListPresenter categoryListPresenter) {
    super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    
    this.loggedInuser = loggedInuser;
    this.categoryListPresenter = categoryListPresenter;
  }

  @Override
  protected void onBind() {
    super.onBind();
    
    setInSlot(TYPE_CategoryPresenter, categoryListPresenter);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    categoryListPresenter.display();
    categoryListPresenter.edit(true);
  }
}

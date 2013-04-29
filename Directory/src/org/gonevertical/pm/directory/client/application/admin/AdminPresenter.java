package org.gonevertical.pm.directory.client.application.admin;

import org.gonevertical.pm.directory.client.application.ApplicationPresenter;
import org.gonevertical.pm.directory.client.application.widgets.category.list.CategoryListPresenter;
import org.gonevertical.pm.directory.client.application.widgets.tag.list.TagListPresenter;
import org.gonevertical.pm.directory.client.place.NameTokens;
import org.gonevertical.pm.directory.client.security.LoggedInGatekeeper;

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

  public static final Object TYPE_CategoryListPresenter = new Object();
  public static final Object TYPE_TagListPresenter = new Object();

  @ProxyStandard
  @NameToken(NameTokens.admin)
  @UseGatekeeper(LoggedInGatekeeper.class)
  public interface MyProxy extends ProxyPlace<AdminPresenter> {
  }
  
  private final CategoryListPresenter categoryListPresenter;
  private final TagListPresenter tagsListPresenter;

  @Inject
  public AdminPresenter(EventBus eventBus, MyView view, MyProxy proxy, 
      CategoryListPresenter categoryListPresenter,
      TagListPresenter tagListPresenter) {
    super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    
    this.categoryListPresenter = categoryListPresenter;
    this.tagsListPresenter = tagListPresenter;
  }

  @Override
  protected void onBind() {
    super.onBind();
    
    setInSlot(TYPE_CategoryListPresenter, categoryListPresenter);
    setInSlot(TYPE_TagListPresenter, tagsListPresenter);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    categoryListPresenter.display();
    categoryListPresenter.edit(true);
    
    tagsListPresenter.display();
    tagsListPresenter.edit(true);
  }
}

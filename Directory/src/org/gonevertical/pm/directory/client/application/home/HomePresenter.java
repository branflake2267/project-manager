package org.gonevertical.pm.directory.client.application.home;

import org.gonevertical.pm.directory.client.application.ApplicationPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetypelist.ArchetypeListPresenter;
import org.gonevertical.pm.directory.client.place.NameTokens;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> {

  public interface MyView extends View {
  }

  public static final Object TYPE_ArchetypeListPresenter = new Object();
  
  private ArchetypeListPresenter archetypeListPresenter;

  @ProxyStandard
  @NameToken(NameTokens.home)
  public interface MyProxy extends ProxyPlace<HomePresenter> {
  }

  @Inject
  public HomePresenter(final EventBus eventBus, final MyView view, final MyProxy proxy,
      final ArchetypeListPresenter archetypeListPresenter) {
    super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);
    
    this.archetypeListPresenter = archetypeListPresenter;
  }

  @Override
  protected void onBind() {
    super.onBind();

    setInSlot(TYPE_ArchetypeListPresenter, archetypeListPresenter);
  }

}

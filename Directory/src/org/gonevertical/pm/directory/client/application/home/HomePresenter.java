package org.gonevertical.pm.directory.client.application.home;

import org.gonevertical.pm.directory.client.application.ApplicationPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.display.ArchetypeDisplayPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.list.ArchetypeListPresenter;
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
  public static final Object TYPE_ArchetypeDisplayPresenter = new Object();

  private final ArchetypeListPresenter archetypeListPresenter;
  private final ArchetypeDisplayPresenter archetypeDisplayPresenter;

  @ProxyStandard
  @NameToken(NameTokens.home)
  public interface MyProxy extends ProxyPlace<HomePresenter> {
  }

  @Inject
  public HomePresenter(EventBus eventBus, MyView view, MyProxy proxy, ArchetypeListPresenter archetypeListPresenter,
      ArchetypeDisplayPresenter archetypeDisplayPresenter) {
    super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

    this.archetypeListPresenter = archetypeListPresenter;
    this.archetypeDisplayPresenter = archetypeDisplayPresenter;
  }

  @Override
  protected void onBind() {
    super.onBind();

    setInSlot(TYPE_ArchetypeListPresenter, archetypeListPresenter);
    setInSlot(TYPE_ArchetypeDisplayPresenter, archetypeDisplayPresenter);
  }

}

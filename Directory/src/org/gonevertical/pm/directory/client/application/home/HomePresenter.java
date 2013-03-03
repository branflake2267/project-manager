package org.gonevertical.pm.directory.client.application.home;

import org.gonevertical.pm.directory.client.application.ApplicationPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.display.ArchetypeDisplayPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.list.ArchetypeListPresenter;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeDisplayListEvent;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeEditEvent;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;
import org.gonevertical.pm.directory.client.place.NameTokens;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements
    ArchetypeEditEvent.EditArchetypeHandler, ArchetypeDisplayListEvent.DisplayListArchetypeHandler {

  public interface MyView extends View {
    void displayList();

    void displayEdit();
  }

  public static final Object TYPE_ArchetypeListPresenter = new Object();
  public static final Object TYPE_ArchetypeDisplayPresenter = new Object();

  private ArchetypeObserver archetypeObserver;
  private final ArchetypeListPresenter archetypeListPresenter;
  private final ArchetypeDisplayPresenter archetypeDisplayPresenter;
  

  @ProxyStandard
  @NameToken(NameTokens.home)
  public interface MyProxy extends ProxyPlace<HomePresenter> {
  }

  @Inject
  public HomePresenter(EventBus eventBus, MyView view, MyProxy proxy, ArchetypeObserver archetypeObserver,
      ArchetypeListPresenter archetypeListPresenter, ArchetypeDisplayPresenter archetypeDisplayPresenter) {
    super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

    this.archetypeObserver = archetypeObserver;
    this.archetypeListPresenter = archetypeListPresenter;
    this.archetypeDisplayPresenter = archetypeDisplayPresenter;
  }

  @Override
  protected void onBind() {
    super.onBind();

    registerHandler(archetypeObserver.getEventBus().addHandler(ArchetypeEditEvent.getType(), this));
    registerHandler(archetypeObserver.getEventBus().addHandler(ArchetypeDisplayListEvent.getType(), this));

    setInSlot(TYPE_ArchetypeListPresenter, archetypeListPresenter);
    setInSlot(TYPE_ArchetypeDisplayPresenter, archetypeDisplayPresenter);
  }

  @Override
  public void onArchetypeEdit(ArchetypeEditEvent event) {
    getView().displayEdit();
  }

  @Override
  public void onArchetypeDisplayList(ArchetypeDisplayListEvent event) {
    getView().displayList();
  }

}

package org.gonevertical.pm.directory.client.application.home;

import org.gonevertical.pm.directory.client.application.ApplicationPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.display.ArchetypeDisplayPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.edit.ArchetypeEditPresenter;
import org.gonevertical.pm.directory.client.application.widgets.archetype.list.ArchetypeListPresenter;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeDisplayListEvent;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeEditEvent;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;
import org.gonevertical.pm.directory.client.events.login.LoggedInEvent;
import org.gonevertical.pm.directory.client.place.NameTokens;
import org.gonevertical.pm.directory.client.security.LoggedInUser;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

public class HomePresenter extends Presenter<HomePresenter.MyView, HomePresenter.MyProxy> implements HomeUiHandlers,
    ArchetypeEditEvent.EditArchetypeHandler, ArchetypeDisplayListEvent.DisplayListArchetypeHandler,
    LoggedInEvent.LoggedInHandler {

  public interface MyView extends View, HasUiHandlers<HomeUiHandlers> {
    void displayList();

    void displayEdit();

    void setAddVisible(boolean visible);
  }

  public static final Object TYPE_ArchetypeListPresenter = new Object();
  public static final Object TYPE_ArchetypeDisplayPresenter = new Object();
  public static final Object TYPE_ArchetypeEditPresenter = new Object();

  private final ArchetypeObserver archetypeObserver;
  private final ArchetypeListPresenter archetypeListPresenter;
  private final ArchetypeDisplayPresenter archetypeDisplayPresenter;
  private final ArchetypeEditPresenter archetypeEditPresenter;
  private final LoggedInUser loggedInUser;

  @ProxyStandard
  @NameToken(NameTokens.home)
  public interface MyProxy extends ProxyPlace<HomePresenter> {
  }

  @Inject
  public HomePresenter(EventBus eventBus, MyView view, MyProxy proxy, ArchetypeObserver archetypeObserver,
      ArchetypeListPresenter archetypeListPresenter, ArchetypeDisplayPresenter archetypeDisplayPresenter,
      ArchetypeEditPresenter archetypeEditPresenter, LoggedInUser loggedInUser) {
    super(eventBus, view, proxy, ApplicationPresenter.TYPE_SetMainContent);

    this.archetypeObserver = archetypeObserver;
    this.archetypeListPresenter = archetypeListPresenter;
    this.archetypeDisplayPresenter = archetypeDisplayPresenter;
    this.archetypeEditPresenter = archetypeEditPresenter;
    this.loggedInUser = loggedInUser;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();

    registerHandler(archetypeObserver.getEventBus().addHandler(ArchetypeEditEvent.getType(), this));
    registerHandler(archetypeObserver.getEventBus().addHandler(ArchetypeDisplayListEvent.getType(), this));
    registerHandler(getEventBus().addHandler(LoggedInEvent.getType(), this));

    setInSlot(TYPE_ArchetypeListPresenter, archetypeListPresenter);
    setInSlot(TYPE_ArchetypeDisplayPresenter, archetypeDisplayPresenter);
    setInSlot(TYPE_ArchetypeEditPresenter, archetypeEditPresenter);
  }

  @Override
  protected void onReveal() {
    super.onReveal();

    updateView();
  }

  private void updateView() {
    getView().setAddVisible(loggedInUser.getIsAdmin());
  }

  @Override
  public void onArchetypeEdit(ArchetypeEditEvent event) {
    getView().displayEdit();
  }

  @Override
  public void onArchetypeDisplayList(ArchetypeDisplayListEvent event) {
    getView().displayList();
  }

  @Override
  public void addArchetype() {
    archetypeObserver.addNewArchetype();
  }

  @Override
  public void onLoggedIn(LoggedInEvent event) {
    updateView();
  }

}

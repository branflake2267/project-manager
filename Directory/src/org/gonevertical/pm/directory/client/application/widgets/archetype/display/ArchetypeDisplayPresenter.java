package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeDisplayEvent;
import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ArchetypeDisplayPresenter extends PresenterWidget<ArchetypeDisplayPresenter.MyView> implements
    ArchetypeDisplayUiHandlers, ArchetypeDisplayEvent.DisplayArchetypeHandler {

  public interface MyView extends View, HasUiHandlers<ArchetypeDisplayUiHandlers> {
    void displayArchetype(ArchetypeJso archetypeJso);
  }
  
  private final ArchetypeObserver archetypeObserver;
  private final ArchetypeJsoDao archetypeJsoDao;

  @Inject
  public ArchetypeDisplayPresenter(EventBus eventBus, MyView view, ArchetypeObserver archetypeObserver,
      LoginPresenter loginPresenter, final ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);
    this.archetypeObserver = archetypeObserver;

    this.archetypeJsoDao = archetypeJsoDao;

    getView().setUiHandlers(this);
  }

  @Override
  protected void onBind() {
    super.onBind();
    
    registerHandler(archetypeObserver.addHandler(ArchetypeDisplayEvent.getType(), this));
  }

  @Override
  public void onDisplay(ArchetypeDisplayEvent event) {
    getView().displayArchetype(event.get());
  }
    
}

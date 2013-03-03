package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ArchetypeEditPresenter extends PresenterWidget<ArchetypeEditPresenter.MyView> implements
    ArchetypeEditUiHandlers {

  private ArchetypeJsoDao archetypeJsoDao;

  public interface MyView extends View, HasUiHandlers<ArchetypeEditUiHandlers> {
  }

  @Inject
  public ArchetypeEditPresenter(final EventBus eventBus, final MyView view, final LoginPresenter loginPresenter,
      final ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);

    this.archetypeJsoDao = archetypeJsoDao;

    getView().setUiHandlers(this);
  }
 
}

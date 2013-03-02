package org.gonevertical.pm.directory.client.application.widgets.archetypeview;

import org.gonevertical.pm.directory.client.application.widgets.login.LoginPresenter;
import org.gonevertical.pm.directory.client.rest.ArchetypeJsoDao;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.util.RestList;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class ArchetypeDisplayPresenter extends PresenterWidget<ArchetypeDisplayPresenter.MyView> implements
    ArchetypeDisplayUiHandlers {

  private ArchetypeJsoDao archetypeJsoDao;

  public interface MyView extends View, HasUiHandlers<ArchetypeDisplayUiHandlers> {
  }

  @Inject
  public ArchetypeDisplayPresenter(final EventBus eventBus, final MyView view, final LoginPresenter loginPresenter,
      final ArchetypeJsoDao archetypeJsoDao) {
    super(eventBus, view);

    this.archetypeJsoDao = archetypeJsoDao;

    getView().setUiHandlers(this);
  }
 
}

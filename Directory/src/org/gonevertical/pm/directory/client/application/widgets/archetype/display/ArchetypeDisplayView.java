package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeDisplayView extends ViewWithUiHandlers<ArchetypeDisplayUiHandlers> implements
    ArchetypeDisplayPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeDisplayView> {
  }

  @Inject
  public ArchetypeDisplayView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

}

package org.gonevertical.pm.directory.client.application.widgets.archetypeedit;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ArchetypeEditView extends ViewWithUiHandlers<ArchetypeEditUiHandlers> implements
    ArchetypeEditPresenter.MyView {

  public interface Binder extends UiBinder<HTMLPanel, ArchetypeEditView> {
  }

  @Inject
  public ArchetypeEditView(final Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }

}

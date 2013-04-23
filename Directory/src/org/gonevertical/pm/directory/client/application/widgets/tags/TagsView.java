package org.gonevertical.pm.directory.client.application.widgets.tags;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

public class TagsView extends ViewWithUiHandlers<TagsUiHandlers> implements
    TagsPresenter.MyView {

  public interface Binder extends UiBinder<SimpleContainer, TagsView> {
  }

  @Inject
  public TagsView(Binder binder) {
    initWidget(binder.createAndBindUi(this));
  }
  
}

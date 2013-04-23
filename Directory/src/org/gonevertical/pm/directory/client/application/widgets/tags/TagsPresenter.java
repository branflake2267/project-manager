package org.gonevertical.pm.directory.client.application.widgets.tags;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class TagsPresenter extends PresenterWidget<TagsPresenter.MyView> implements
    TagsUiHandlers {

  public interface MyView extends View, HasUiHandlers<TagsUiHandlers> {
  }
  
  @Inject
  public TagsPresenter(EventBus eventBus, MyView view) {
    super(eventBus, view);
    
    getView().setUiHandlers(this);
  }
    
}

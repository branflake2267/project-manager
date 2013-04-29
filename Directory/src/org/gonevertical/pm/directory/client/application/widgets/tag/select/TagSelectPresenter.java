package org.gonevertical.pm.directory.client.application.widgets.tag.select;

import org.gonevertical.pm.directory.client.application.widgets.tag.list.TagListPresenter;
import org.gonevertical.pm.directory.client.events.tag.DisplayTagPopupEvent;
import org.gonevertical.pm.directory.client.events.tag.TagSelectEvent;
import org.gonevertical.pm.directory.client.events.tag.TagSelectEventHandler;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

public class TagSelectPresenter extends PresenterWidget<TagSelectPresenter.MyView> implements
    TagSelectUiHandlers, DisplayTagPopupEvent.AddTagHandler, TagSelectEventHandler {

  public interface MyView extends View, HasUiHandlers<TagSelectUiHandlers> {
    void center();
    void hide();
  }
  
  public static Object SLOT_tagList = new Object();

  private final TagListPresenter tagListPresenter;

  private EventBus eventBus;
  
  @Inject
  public TagSelectPresenter(EventBus eventBus, MyView view, TagListPresenter tagListPresenter) {
    super(eventBus, view);
    this.eventBus = eventBus;
    
    this.tagListPresenter = tagListPresenter;
    
    getView().setUiHandlers(this);
  }
    
  @Override
  protected void onBind() {
    super.onBind();
    
    addRegisteredHandler(DisplayTagPopupEvent.getType(), this);
    addRegisteredHandler(TagSelectEvent.TYPE, this);
  }
  
  @Override
  protected void onReveal() {
    super.onReveal();
    
    setInSlot(SLOT_tagList, tagListPresenter);
  }

  @Override
  public void onAddTag(DisplayTagPopupEvent event) {
    tagListPresenter.display();
    tagListPresenter.edit(false);
    
    getView().center();
  }

  @Override
  public void onTagSelectEvent(TagSelectEvent event) {
    getView().hide();
  }
 
}

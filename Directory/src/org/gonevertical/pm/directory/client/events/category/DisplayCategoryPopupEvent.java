package org.gonevertical.pm.directory.client.events.category;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class DisplayCategoryPopupEvent extends GwtEvent<DisplayCategoryPopupEvent.AddCategoryHandler> {

  public DisplayCategoryPopupEvent() {
  }

  public static <T> void fire(HasHandlers source) {
    DisplayCategoryPopupEvent eventInstance = new DisplayCategoryPopupEvent();
    source.fireEvent(eventInstance);
  }

  public interface HasSelectCategoryDataHandlers extends HasHandlers {
    HandlerRegistration addSelectCategoryHandler(AddCategoryHandler handler);
  }

  public interface AddCategoryHandler extends EventHandler {
    public void onAddCategory(DisplayCategoryPopupEvent event);
  }

  private static final Type<AddCategoryHandler> TYPE = new Type<AddCategoryHandler>();

  public static Type<AddCategoryHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<AddCategoryHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddCategoryHandler handler) {
    handler.onAddCategory(this);
  }

}

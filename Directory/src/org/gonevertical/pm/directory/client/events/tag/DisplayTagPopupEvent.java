package org.gonevertical.pm.directory.client.events.tag;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class DisplayTagPopupEvent extends GwtEvent<DisplayTagPopupEvent.AddTagHandler> {

  public DisplayTagPopupEvent() {
  }

  public static <T> void fire(HasHandlers source) {
    DisplayTagPopupEvent eventInstance = new DisplayTagPopupEvent();
    source.fireEvent(eventInstance);
  }

  public interface HasSelectTagDataHandlers extends HasHandlers {
    HandlerRegistration addSelectTagHandler(AddTagHandler handler);
  }

  public interface AddTagHandler extends EventHandler {
    public void onAddTag(DisplayTagPopupEvent event);
  }

  private static final Type<AddTagHandler> TYPE = new Type<AddTagHandler>();

  public static Type<AddTagHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<AddTagHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(AddTagHandler handler) {
    handler.onAddTag(this);
  }

}

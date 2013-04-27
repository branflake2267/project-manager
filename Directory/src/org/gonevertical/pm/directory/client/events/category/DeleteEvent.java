package org.gonevertical.pm.directory.client.events.category;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class DeleteEvent extends GwtEvent<DeleteEvent.DeleteHandler> {

  public DeleteEvent() {
  }

  public static void fire(HasHandlers source) {
    DeleteEvent eventInstance = new DeleteEvent();
    source.fireEvent(eventInstance);
  }

  public interface HasGlobalDataHandlers extends HasHandlers {
    HandlerRegistration addDeleteHandler(DeleteHandler handler);
  }

  public interface DeleteHandler extends EventHandler {
    public void onDelete(DeleteEvent event);
  }

  private static final Type<DeleteHandler> TYPE = new Type<DeleteHandler>();

  public static Type<DeleteHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<DeleteHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DeleteHandler handler) {
    handler.onDelete(this);
  }

}

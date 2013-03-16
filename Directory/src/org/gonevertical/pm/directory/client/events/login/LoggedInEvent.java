package org.gonevertical.pm.directory.client.events.login;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class LoggedInEvent extends GwtEvent<LoggedInEvent.LoggedInHandler> {

  public LoggedInEvent() {
    // Possibly for serialization.
  }

  public static void fire(HasHandlers source) {
    LoggedInEvent eventInstance = new LoggedInEvent();
    source.fireEvent(eventInstance);
  }

  public static void fire(HasHandlers source, LoggedInEvent eventInstance) {
    source.fireEvent(eventInstance);
  }

  public interface HasGlobalDataHandlers extends HasHandlers {
    HandlerRegistration addGlobalHandler(LoggedInHandler handler);
  }

  public interface LoggedInHandler extends EventHandler {
    public void onLoggedIn(LoggedInEvent event);
  }

  private static final Type<LoggedInHandler> TYPE = new Type<LoggedInHandler>();

  public static Type<LoggedInHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<LoggedInHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(LoggedInHandler handler) {
    handler.onLoggedIn(this);
  }

}

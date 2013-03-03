package org.gonevertical.pm.directory.client.events.archetypes;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ArchetypeDisplayListEvent extends GwtEvent<ArchetypeDisplayListEvent.DisplayListArchetypeHandler> {
  
  public interface DisplayListArchetypeHandler extends EventHandler {
    void onArchetypeDisplayList(ArchetypeDisplayListEvent event);
  }

  private static final Type<DisplayListArchetypeHandler> TYPE = new Type<DisplayListArchetypeHandler>();

  public static Type<DisplayListArchetypeHandler> getType() {
    return TYPE;
  }

  public ArchetypeDisplayListEvent() {
  }

  @Override
  public Type<DisplayListArchetypeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DisplayListArchetypeHandler handler) {
    handler.onArchetypeDisplayList(this);
  }
  
}

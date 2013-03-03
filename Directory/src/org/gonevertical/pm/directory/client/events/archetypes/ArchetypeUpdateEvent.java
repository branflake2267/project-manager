package org.gonevertical.pm.directory.client.events.archetypes;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ArchetypeUpdateEvent extends GwtEvent<ArchetypeUpdateEvent.UpdateArchetypeHandler> {
  
  public interface UpdateArchetypeHandler extends EventHandler {
    void onArchetypeUpdate(ArchetypeUpdateEvent event);
  }

  private static final Type<UpdateArchetypeHandler> TYPE = new Type<UpdateArchetypeHandler>();

  private final ArchetypeJso archetype;

  public static Type<UpdateArchetypeHandler> getType() {
    return TYPE;
  }

  public ArchetypeUpdateEvent(final ArchetypeJso archetypeJso) {
    this.archetype = archetypeJso;
  }

  public ArchetypeJso get() {
    return archetype;
  }

  @Override
  public Type<UpdateArchetypeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(UpdateArchetypeHandler handler) {
    handler.onArchetypeUpdate(this);
  }
  
}

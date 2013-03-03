package org.gonevertical.pm.directory.client.events.archetypes;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ArchetypeDisplayEvent extends GwtEvent<ArchetypeDisplayEvent.DisplayArchetypeHandler> {
  public interface DisplayArchetypeHandler extends EventHandler {
    void onDisplay(ArchetypeDisplayEvent event);
  }

  private static final Type<DisplayArchetypeHandler> TYPE = new Type<DisplayArchetypeHandler>();

  private final ArchetypeJso archetype;

  public static Type<DisplayArchetypeHandler> getType() {
    return TYPE;
  }

  public ArchetypeDisplayEvent(final ArchetypeJso archetypeJso) {
    this.archetype = archetypeJso;
  }

  public ArchetypeJso get() {
    return archetype;
  }

  @Override
  public Type<DisplayArchetypeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(DisplayArchetypeHandler handler) {
    handler.onDisplay(this);
  }
}

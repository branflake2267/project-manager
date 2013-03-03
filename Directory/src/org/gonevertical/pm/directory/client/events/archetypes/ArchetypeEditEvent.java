package org.gonevertical.pm.directory.client.events.archetypes;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class ArchetypeEditEvent extends GwtEvent<ArchetypeEditEvent.EditArchetypeHandler> {
  
  public interface EditArchetypeHandler extends EventHandler {
    void onArchetypeEdit(ArchetypeEditEvent event);
  }

  private static final Type<EditArchetypeHandler> TYPE = new Type<EditArchetypeHandler>();

  private final ArchetypeJso archetype;

  public static Type<EditArchetypeHandler> getType() {
    return TYPE;
  }

  public ArchetypeEditEvent(final ArchetypeJso archetypeJso) {
    this.archetype = archetypeJso;
  }

  public ArchetypeJso get() {
    return archetype;
  }

  @Override
  public Type<EditArchetypeHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(EditArchetypeHandler handler) {
    handler.onArchetypeEdit(this);
  }
  
}

package org.gonevertical.pm.directory.client.events.archetypes;

import javax.inject.Inject;
import javax.inject.Named;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class ArchetypeObserver {

  private final EventBus eventBus;

  @Inject
  public ArchetypeObserver(@Named("ArchetypeEventBus") final EventBus eventBus) {
    this.eventBus = eventBus;
  }

  public void fireEvent(GwtEvent<?> event) {
    eventBus.fireEventFromSource(event, this);
  }

  public final <H extends EventHandler> HandlerRegistration addHandler(GwtEvent.Type<H> type, H handler) {
    return eventBus.addHandler(type, handler);
  }

  public EventBus getEventBus() {
    return eventBus;
  }

  public void edit(ArchetypeJso archetypeJso) {
    eventBus.fireEvent(new ArchetypeEditEvent(archetypeJso));
  }
  
  public void display(ArchetypeJso archetypeJso) {
    eventBus.fireEvent(new ArchetypeDisplayEvent(archetypeJso));
  }

}

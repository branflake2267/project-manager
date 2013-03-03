package org.gonevertical.pm.directory.client.events;

import javax.inject.Singleton;

import org.gonevertical.pm.directory.client.events.archetypes.ArchetypeObserver;

import com.google.inject.name.Names;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class EventsModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    bind(ArchetypeObserver.class).in(Singleton.class);
    bind(EventBus.class).annotatedWith(Names.named("ArchetypeEventBus")).to(SimpleEventBus.class).in(Singleton.class);
  }

}

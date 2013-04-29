package org.gonevertical.pm.directory.client.events.tag;

import com.google.gwt.event.shared.EventHandler;

public interface TagSelectEventHandler extends EventHandler {
  void onTagSelectEvent(TagSelectEvent event);
}
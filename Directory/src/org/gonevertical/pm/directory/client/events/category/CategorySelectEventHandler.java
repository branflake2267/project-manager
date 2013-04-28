package org.gonevertical.pm.directory.client.events.category;

import com.google.gwt.event.shared.EventHandler;

public interface CategorySelectEventHandler extends EventHandler {
  void onCategorySelectEvent(CategorySelectEvent event);
}
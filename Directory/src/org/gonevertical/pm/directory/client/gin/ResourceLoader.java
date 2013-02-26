package org.gonevertical.pm.directory.client.gin;

import org.gonevertical.pm.directory.client.resources.AppResources;

import com.google.inject.Inject;

public class ResourceLoader {
  
  @Inject
  public ResourceLoader(AppResources resources) {
    resources.styles().ensureInjected();
    resources.sprites().ensureInjected();

    // ... Inject more css into the document here on boot
  }
  
}

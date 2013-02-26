package org.gonevertical.pm.directory.client.resources;

import com.google.gwt.i18n.client.Constants;

/**
 * Constants to allow translation of various strings. Note that the translations themselves are not part of this
 * example.
 */
public interface AppConstants extends Constants {
    @DefaultStringValue("News")
    String news();

    @DefaultStringValue("Home")
    String home();
    
    String gwtpPlatformTitle();
}

package org.gonevertical.pm.directory.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

/**
 * {@link ResourceLoader} injects the css on boostrapping from the {@Link SecurityModule} and the resources exist
 * in src/main/resources/com.gwtplatform.samples.tab.client.resources/*
 */
public interface AppResources extends ClientBundle {
  public interface Styles extends CssResource {
    String divBorder();

    String logo();
  }

  public interface Sprites extends CssResource {
    String logo();
  }

  public Styles styles();

  public Sprites sprites();

  @Source("images/logo.png")
  @ImageResource.ImageOptions(repeatStyle = ImageResource.RepeatStyle.Horizontal, width = 50)
  ImageResource logo();
}

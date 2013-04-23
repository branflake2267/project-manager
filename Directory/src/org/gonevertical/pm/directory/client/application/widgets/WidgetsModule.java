package org.gonevertical.pm.directory.client.application.widgets;

import org.gonevertical.pm.directory.client.application.widgets.archetype.display.ArchetypeDisplayModule;
import org.gonevertical.pm.directory.client.application.widgets.archetype.edit.ArchetypeEditModule;
import org.gonevertical.pm.directory.client.application.widgets.archetype.list.ArchetypeListModule;
import org.gonevertical.pm.directory.client.application.widgets.category.list.CategoryListModule;
import org.gonevertical.pm.directory.client.application.widgets.category.select.CategorySelectModule;
import org.gonevertical.pm.directory.client.application.widgets.header.HeaderModule;
import org.gonevertical.pm.directory.client.application.widgets.login.LoginModule;
import org.gonevertical.pm.directory.client.application.widgets.tags.TagsModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class WidgetsModule extends AbstractPresenterModule {
  
  @Override
  protected void configure() {
    install(new HeaderModule());
    install(new LoginModule());
    install(new ArchetypeListModule());
    install(new ArchetypeDisplayModule());
    install(new ArchetypeEditModule());
    install(new CategoryListModule());
    install(new CategorySelectModule());
    install(new TagsModule());
  }
  
}

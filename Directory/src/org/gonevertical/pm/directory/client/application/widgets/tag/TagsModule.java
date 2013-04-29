package org.gonevertical.pm.directory.client.application.widgets.tag;

import org.gonevertical.pm.directory.client.application.widgets.tag.list.TagListPresenter;
import org.gonevertical.pm.directory.client.application.widgets.tag.list.TagListView;
import org.gonevertical.pm.directory.client.application.widgets.tag.select.TagSelectModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TagsModule extends AbstractPresenterModule {

  @Override
  protected void configure() {
    install(new TagSelectModule());
    
    bindPresenterWidget(TagListPresenter.class, TagListPresenter.MyView.class, TagListView.class);
  }
  
}

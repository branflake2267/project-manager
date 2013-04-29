package org.gonevertical.pm.directory.client.events.tag;

import org.gonevertical.pm.directory.client.rest.jso.TagJso;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

public class TagSelectEvent extends GwtEvent<TagSelectEventHandler> {
  
  public static Type<TagSelectEventHandler> TYPE = new Type<TagSelectEventHandler>();
  private Widget source;
  
  private TagJso cateogryJso;

  public TagSelectEvent(Widget source) {
    this.source = source;
  }

  public TagSelectEvent() {
  }
  
  public TagSelectEvent(TagJso cateogryJso) {
    this.cateogryJso = cateogryJso;
  }

  @Override
  public Type<TagSelectEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(TagSelectEventHandler handler) {
    handler.onTagSelectEvent(this);
  }

  public Widget getSource() {
    return source;
  }
  
  public TagJso getData() {
    return cateogryJso;
  }
}

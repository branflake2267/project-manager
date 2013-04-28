package org.gonevertical.pm.directory.client.events.category;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Widget;

public class CategorySelectEvent extends GwtEvent<CategorySelectEventHandler> {
  
  public static Type<CategorySelectEventHandler> TYPE = new Type<CategorySelectEventHandler>();
  private Widget source;
  
  private CategoryJso cateogryJso;

  public CategorySelectEvent(Widget source) {
    this.source = source;
  }

  public CategorySelectEvent() {
  }
  
  public CategorySelectEvent(CategoryJso cateogryJso) {
    this.cateogryJso = cateogryJso;
  }

  @Override
  public Type<CategorySelectEventHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(CategorySelectEventHandler handler) {
    handler.onCategorySelectEvent(this);
  }

  public Widget getSource() {
    return source;
  }
  
  public CategoryJso getData() {
    return cateogryJso;
  }
}

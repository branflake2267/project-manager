package org.gonevertical.pm.directory.client.events.category;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class CategorySelectEvent extends GwtEvent<CategorySelectEvent.SelectSelectHandler> {

  private CategoryJso data;

  public CategorySelectEvent() {
    // Possibly for serialization.
  }

  public CategorySelectEvent(CategoryJso data) {
    this.data = data;
  }

  public static void fire(HasHandlers source, CategoryJso data) {
    CategorySelectEvent eventInstance = new CategorySelectEvent(data);
    source.fireEvent(eventInstance);
  }

  public interface HasSelectCategoryDataHandlers extends HasHandlers {
    HandlerRegistration addSelectCategoryHandler(SelectSelectHandler handler);
  }

  public interface SelectSelectHandler extends EventHandler {
    public void onCategorySelect(CategorySelectEvent event);
  }

  private static final Type<SelectSelectHandler> TYPE = new Type<SelectSelectHandler>();

  public static Type<SelectSelectHandler> getType() {
    return TYPE;
  }

  @Override
  public Type<SelectSelectHandler> getAssociatedType() {
    return TYPE;
  }

  @Override
  protected void dispatch(SelectSelectHandler handler) {
    handler.onCategorySelect(this);
  }

  public CategoryJso getData() {
    return data;
  }
  
}

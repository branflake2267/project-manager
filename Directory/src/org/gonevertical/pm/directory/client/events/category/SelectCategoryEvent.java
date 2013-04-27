package org.gonevertical.pm.directory.client.events.category;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class SelectCategoryEvent<T> extends GwtEvent<SelectCategoryEvent.SelectSelectHandler> {

  private T data;

  public SelectCategoryEvent() {
    // Possibly for serialization.
  }

  public SelectCategoryEvent(T data) {
    this.data = data;
  }

  public static <T> void fire(HasHandlers source, T data) {
    SelectCategoryEvent eventInstance = new SelectCategoryEvent(data);
    source.fireEvent(eventInstance);
  }

  public interface HasSelectCategoryDataHandlers extends HasHandlers {
    HandlerRegistration addSelectCategoryHandler(SelectSelectHandler handler);
  }

  public interface SelectSelectHandler<T> extends EventHandler {
    public void onSelect(SelectCategoryEvent<T> event);
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
    handler.onSelect(this);
  }

  public T getData() {
    return data;
  }
  
}

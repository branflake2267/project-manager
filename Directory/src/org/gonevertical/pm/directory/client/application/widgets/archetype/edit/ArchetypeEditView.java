package org.gonevertical.pm.directory.client.application.widgets.archetype.edit;

import org.gonevertical.pm.directory.client.application.widgets.archetype.edit.editor.ArchetypeEditor;
import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;
import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;

public class ArchetypeEditView extends ViewWithUiHandlers<ArchetypeEditUiHandlers> implements
    ArchetypeEditPresenter.MyView {

  interface CategoryProperties extends PropertyAccess<CategoryJso> {
    ModelKeyProvider<CategoryJso> key();
    ValueProvider<CategoryJso, String> name();
  }
  public interface Binder extends UiBinder<Widget, ArchetypeEditView> {}
  
  @UiField
  TextButton goback;
  @UiField
  TextButton save;
  @UiField
  ArchetypeEditor editor;
  
  @Inject
  public ArchetypeEditView(Binder binder, EventBus eventBus) {
    initWidget(binder.createAndBindUi(this));
    
    editor.setEventBus(eventBus);
  }

  @UiHandler("goback")
  void onGoBack(SelectEvent event) {
    getUiHandlers().displayList();
  }
  
  @UiHandler("save")
  void onSave(SelectEvent event) {
    getUiHandlers().save(editor.getArchTypeJso());
  }
  
  @Override
  public void display(ArchetypeJso archetypeJso) {
    editor.edit(archetypeJso);
  }

}

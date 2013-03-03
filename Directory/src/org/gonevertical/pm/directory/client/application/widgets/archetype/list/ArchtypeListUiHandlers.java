package org.gonevertical.pm.directory.client.application.widgets.archetype.list;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.mvp.client.UiHandlers;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface ArchtypeListUiHandlers extends UiHandlers {
  
  void fetchArchetypes(int offset, int length, AsyncCallback<PagingLoadResult<ArchetypeJso>> callback);

  void gotoEdit(ArchetypeJso selectedArchetype);
  
}

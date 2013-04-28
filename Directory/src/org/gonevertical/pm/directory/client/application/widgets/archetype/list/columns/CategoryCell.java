package org.gonevertical.pm.directory.client.application.widgets.archetype.list.columns;

import org.gonevertical.pm.directory.client.rest.jso.CategoryJso;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;

public class CategoryCell extends AbstractCell<JsArray<CategoryJso>> {

  @Override
  public void render(com.google.gwt.cell.client.Cell.Context context, JsArray<CategoryJso> value, SafeHtmlBuilder sb) {
    if (value == null) {
      sb.appendHtmlConstant("");
      return;
    }
    
    for (int i=0; i < value.length(); i++) {
      CategoryJso category = value.get(i);
      String s = category.getName();
      SafeHtml sh = SimpleHtmlSanitizer.sanitizeHtml(s);
      sb.append(sh);
    }
  }

}
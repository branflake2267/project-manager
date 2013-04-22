package org.gonevertical.pm.directory.client.application.widgets.archetype.display.xtemplate;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface ArchetypeDisplayXTemplates extends XTemplates {
  
  @XTemplate(source = "archetype.html")
  SafeHtml displayCommand(ArchetypeJso archetype);

}

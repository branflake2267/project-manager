package org.gonevertical.pm.directory.client.application.widgets.archetype.display;

import org.gonevertical.pm.directory.client.rest.jso.ArchetypeJso;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.sencha.gxt.core.client.XTemplates;

public interface ArchetypeDisplay extends XTemplates {
  
  @XTemplate("mvn archetype:generate \\<br/>\n" +
              "-DarchetypeRepository={archetype.repository} \\<br/>\n" +
  		        "-DarchetypeGroupId={archetype.groupId} \\<br/>\n" +
              "-DarchetypeArtifactId={archetype.artifactId} \\<br/>\n" +
              "-DarchetypeVersion={archetype.version} \\<br/>\n" +
              "-DgroupId=com.projectname.project \\<br/>\n" +
              "-DartifactId=new-project-name \\<br/>\n" +
              "-Dmodule=Project")
  SafeHtml displayCommand(ArchetypeJso archetype);

}

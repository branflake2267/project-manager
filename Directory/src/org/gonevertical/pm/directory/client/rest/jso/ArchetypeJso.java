package org.gonevertical.pm.directory.client.rest.jso;

import com.google.gwt.core.client.JavaScriptObject;

public class ArchetypeJso extends JavaScriptObject {

  protected ArchetypeJso() {
  }

  public final native void setKey(String key) /*-{
    this.key = key;
  }-*/;

  public final native String getKey() /*-{
    return this.key;
  }-*/;

  public final native void setName(String name) /*-{
    this.name = name;
  }-*/;
  
  public final native String getName() /*-{
    return this.name;
  }-*/;
  
  public final native void setDescription(String description) /*-{
    this.description = description;
  }-*/;
  
  public final native String getDescription() /*-{
    return this.description;
  }-*/;
  
  public final native void setRepository(String repository) /*-{
    this.repository = repository;
  }-*/;
  
  public final native String getRepository() /*-{
    return this.repository;
  }-*/;
  
  public final native void setGroupId(String groupId) /*-{
    this.groupId = groupId;
  }-*/;
  
  public final native String getGroupId() /*-{
    return this.groupId;
  }-*/;
  
  public final native void setArtifactId(String artifactId) /*-{
    this.artifactId = artifactId;
  }-*/;
  
  public final native String getArtifactId() /*-{
    return this.artifactId;
  }-*/;
  
  public final native void setVersion(String version) /*-{
    this.version = version;
  }-*/;
  
  public final native String getVersion() /*-{
    return this.version;
  }-*/;
  
}

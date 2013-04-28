package org.gonevertical.pm.directory.client.rest.jso;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;

public class ArchetypeJso extends JavaScriptObject {

  protected ArchetypeJso() {
  }
  
  public static ArchetypeJso newInstance() {
    ArchetypeJso jso = JavaScriptObject.createObject().cast();
    jso.setArtifactId("");
    jso.setDescription("");
    jso.setGroupId("");
    jso.setName("");
    jso.setRepository("");
    jso.setVersion("");
    
    return jso;
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
  
  private final native JsArray<CategoryJso> getCategoriesJso() /*-{
    return this.categories;
  }-*/;
  
  public final List<CategoryJso> getCategories() {
    JsArray<CategoryJso> categories = getCategoriesJso();
    if (categories == null) {
      return new ArrayList<CategoryJso>();
    }
    List<CategoryJso> list = new ArrayList<CategoryJso>();    
    for (int i=0; i < categories.length(); i++) {
      list.add(categories.get(i));
    }
    return list;
  }
  
  private final native void setCategoriesJso(JsArray<CategoryJso> categories) /*-{
    this.categories = categories;
  }-*/;
  
  public final void setCategories(List<CategoryJso> categories) {
    if (categories == null) {
      categories = new ArrayList<CategoryJso>();
    }
    JsArray<CategoryJso> list = JavaScriptObject.createArray().cast();
    for (CategoryJso categoryJso : categories) {
      list.push(categoryJso);
    }
    setCategoriesJso(list);
  }
  
  public final native List<TagJso> getTags() /*-{
    return this.tags;
  }-*/;
  
  public final native void setTags(List<TagJso> tags) /*-{
    this.tags = tags;
  }-*/;
  
}

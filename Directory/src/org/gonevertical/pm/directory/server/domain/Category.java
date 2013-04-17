package org.gonevertical.pm.directory.server.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Transient;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
public class Category {

  private Key parent;

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  private String name;
  
  @Transient
  private boolean hasChildren;

  public Category() {
  }
  
  public String getKey() {
    if (key == null) {
      return null;
    }
    return KeyFactory.keyToString(key);
  }

  public void setKey(String key) {
    if (key == null) {
      return;
    }
    this.key = KeyFactory.stringToKey(key);
  }

  public void setParent(String parent) {
    if (parent == null) {
      return;
    }
    this.parent = KeyFactory.stringToKey(parent);
  }

  public String getParent() {
    if (parent == null) {
      return null;
    }
    return KeyFactory.keyToString(parent);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean hasChildren() {
    return hasChildren;
  }

  public void setHasChildren(boolean hasChildren) {
    this.hasChildren = hasChildren;
  }
  
}

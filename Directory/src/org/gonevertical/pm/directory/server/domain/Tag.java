package org.gonevertical.pm.directory.server.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
public class Tag {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private String name;

  public String getKey() {
    return KeyFactory.keyToString(key);
  }

  public void setKey(String key) {
    if (key == null) {
      return;
    }
    this.key = KeyFactory.stringToKey(key);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Tag other = (Tag) obj;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    return true;
  }

}

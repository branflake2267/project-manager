package org.gonevertical.archetypes.server.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Tag {

  @Id
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;
  
  private Category name;

  public String getKey() {
    return KeyFactory.keyToString(key);
  }

  public void setKey(String key) {
    this.key = KeyFactory.stringToKey(key);
  }

  public Category getName() {
    return name;
  }

  public void setName(Category name) {
    this.name = name;
  }
  
}

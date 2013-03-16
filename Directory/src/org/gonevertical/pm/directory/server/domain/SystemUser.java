package org.gonevertical.pm.directory.server.domain;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@PersistenceCapable(detachable = "true")
public class SystemUser {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;
  private String userId;
  private Boolean isAdmin;
  private String nameFirst;
  private String nameLast;

  public SystemUser() {
    userId = "";
    isAdmin = false;
  }

  public String getKey() {
    if (key == null) {
      return "";
    }
    return KeyFactory.keyToString(key);
  }

  public void setKey(String key) {
    if (key != null) {
      this.key = KeyFactory.stringToKey(key);
    }
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Boolean getIsAdmin() {
    return isAdmin;
  }

  public void setIsAdmin(Boolean isAdmin) {
    this.isAdmin = isAdmin;
  }

  public String getNameFirst() {
    return nameFirst;
  }

  public void setNameFirst(String nameFirst) {
    this.nameFirst = nameFirst;
  }

  public String getNameLast() {
    return nameLast;
  }

  public void setNameLast(String nameLast) {
    this.nameLast = nameLast;
  }

  @Override
  public String toString() {
    String s = "SystemUser(";
    s += "key=" + key + ", ";
    s += "userId=" + userId;
    s += ")";
    return s;
  }
}

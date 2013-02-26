package org.gonevertical.pm.directory.server.domain;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(detachable = "true")
public class SystemUser {

  @PrimaryKey
  private String googleId;

  public SystemUser() {
    googleId = "";
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }

}

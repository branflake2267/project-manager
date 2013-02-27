package org.gonevertical.pm.directory.server.domain.dto;

public class SystemUser {

  private String key;
  private String googleId;

  public SystemUser() {
  }
  
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }
  
}

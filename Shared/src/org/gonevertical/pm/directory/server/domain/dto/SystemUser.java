package org.gonevertical.pm.directory.server.domain.dto;

public class SystemUser {

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

package org.gonevertical.pm.directory.server.domain.dto;

public class User {

  private String googleId;

  public User() {
    googleId = "";
  }

  public String getGoogleId() {
    return googleId;
  }

  public void setGoogleId(String googleId) {
    this.googleId = googleId;
  }
  
}

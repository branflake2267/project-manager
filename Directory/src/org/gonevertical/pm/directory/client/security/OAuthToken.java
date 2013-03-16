package org.gonevertical.pm.directory.client.security;

public class OAuthToken {

  private String access_token;
  
  public OAuthToken() {
  }
  
  public void setAccessToken(String access_token) {
    this.access_token = access_token;
  }
  
  public String getAccessToken() {
    return access_token;
  }
}

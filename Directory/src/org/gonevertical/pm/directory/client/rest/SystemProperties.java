package org.gonevertical.pm.directory.client.rest;

public class SystemProperties {
  
  public final static String VERSION = "v1";
  
  /**
   * Turn on the remote server for rest requests instead of locally.
   */
  public final static boolean DEBUG = true;
  
  public String getVersion() {
    return VERSION;
  }
  
  public static boolean getDebugRemote() {
    return DEBUG;
  }
  
}

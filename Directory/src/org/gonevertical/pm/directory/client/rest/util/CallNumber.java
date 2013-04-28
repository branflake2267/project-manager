package org.gonevertical.pm.directory.client.rest.util;

public class CallNumber {
  private int call = 0;
  
  public final void add() {
    call++;
  }
  
  public int get() {
    return call;
  }
}

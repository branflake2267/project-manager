package org.gonevertical.pm.directory.server.rest.dto;

import java.util.Collection;

import com.google.api.server.spi.response.CollectionResponse;

public class JsonCollection<T> extends CollectionResponse<T> {
  
  public JsonCollection(Collection<T> items, String nextPageToken, int total) {
    super(items, nextPageToken);
    
    this.total = total;
  }

  private int total;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }
  
}

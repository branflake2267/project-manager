package org.gonevertical.pm.directory.client.rest.util;

public enum RestHeaders {
    
    ACCEPT__JSON("Accept", "application/json"),
    CONTENT_TYPE__JSON("Content-Type", "application/json");
    
    String key = "";
    String value = "";
    
    RestHeaders(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return getKey() + "=" + getValue();
    }
    
    public String getKey() {
        return key;
    }
    
    public String getValue() {
        return value;
    }
    
}
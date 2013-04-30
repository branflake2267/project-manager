package org.gonevertical.pm.directory.server.rest;

import java.util.ArrayList;

import javax.inject.Named;

import org.gonevertical.pm.directory.client.rest.SystemProperties;
import org.gonevertical.pm.directory.server.domain.SystemUser;
import org.gonevertical.pm.directory.server.domain.dao.JdoUtils;
import org.gonevertical.pm.directory.server.domain.dao.SimpleFilter;

import com.google.api.server.spi.config.Api;
import com.google.appengine.api.datastore.Query.FilterOperator;

@Api(
     name = "systemuserendpoint", 
     version = SystemProperties.VERSION)
public class SystemUserEndpoint {

  public SystemUser getSystemUser(@Named("key") String encodedKey) {
    return JdoUtils.find(SystemUser.class, encodedKey);
  }

  public SystemUser insertSystemUser(SystemUser systemUser) {
    return JdoUtils.persist(systemUser);
  }

  public SystemUser updateSystemUser(SystemUser systemUser) {
    return JdoUtils.persist(systemUser);
  }

  public SystemUser findByUserId(@Named("userId") String googleId) {
    ArrayList<SimpleFilter> simpleFilter = new ArrayList<SimpleFilter>();
    simpleFilter.add(new SimpleFilter("userId", FilterOperator.EQUAL, googleId));

    return JdoUtils.findFirst(SystemUser.class, simpleFilter);
  }

}

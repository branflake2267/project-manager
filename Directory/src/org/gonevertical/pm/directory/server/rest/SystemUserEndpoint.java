package org.gonevertical.pm.directory.server.rest;

import javax.inject.Named;
import javax.jdo.PersistenceManager;

import org.gonevertical.pm.directory.server.domain.SystemUser;
import org.gonevertical.pm.directory.server.domain.dao.PMF;

import com.google.api.server.spi.config.Api;

@Api(name = "systemuserendpoint", version = "v1")
public class SystemUserEndpoint {

  public SystemUser getSystemUser(@Named("googleId") String googleId) {
    PersistenceManager mgr = getPersistenceManager();
    SystemUser systemUser = null;
    try {
      systemUser = mgr.getObjectById(SystemUser.class, googleId);
    } catch (Exception e) {
    } finally {
      mgr.close();
    }
    return systemUser;
  }

  public SystemUser insertCategory(SystemUser systemUser) {
    PersistenceManager mgr = getPersistenceManager();
    try {
      mgr.makePersistent(systemUser);
    } finally {
      mgr.close();
    }
    return systemUser;
  }

  public SystemUser updateCategory(SystemUser systemUser) {
    PersistenceManager mgr = getPersistenceManager();
    try {
      mgr.makePersistent(systemUser);
    } finally {
      mgr.close();
    }
    return systemUser;
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}

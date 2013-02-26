package org.gonevertical.pm.directory.server.rest;

import org.gonevertical.pm.directory.server.domain.Category;
import org.gonevertical.pm.directory.server.domain.dao.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "categoryendpoint")
public class CategoryEndpoint {

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET method and paging support.
   * 
   * @return A CollectionResponse class containing the list of all entities persisted and a cursor to the next page.
   */
  @SuppressWarnings({ "unchecked", "unused" })
  public CollectionResponse<Category> listCategory(@Nullable @Named("cursor") String cursorString,
      @Nullable @Named("limit") Integer limit) {

    PersistenceManager mgr = null;
    Cursor cursor = null;
    List<Category> execute = null;

    try {
      mgr = getPersistenceManager();
      Query query = mgr.newQuery(Category.class);
      if (cursorString != null && cursorString != "") {
        cursor = Cursor.fromWebSafeString(cursorString);
        HashMap<String, Object> extensionMap = new HashMap<String, Object>();
        extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
        query.setExtensions(extensionMap);
      }

      if (limit != null) {
        query.setRange(0, limit);
      }

      execute = (List<Category>) query.execute();
      cursor = JDOCursorHelper.getCursor(execute);
      if (cursor != null)
        cursorString = cursor.toWebSafeString();

      // Tight loop for fetching all entities from datastore and accomodate
      // for lazy fetch.
      for (Category obj : execute)
        ;
    } finally {
      mgr.close();
    }

    return CollectionResponse.<Category> builder().setItems(execute).setNextPageToken(cursorString).build();
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   * 
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public Category getCategory(@Named("id") Long id) {
    PersistenceManager mgr = getPersistenceManager();
    Category category = null;
    try {
      category = mgr.getObjectById(Category.class, id);
    } finally {
      mgr.close();
    }
    return category;
  }

  /**
   * This inserts a new entity into App Engine datastore. If the entity already exists in the datastore, an exception is
   * thrown. It uses HTTP POST method.
   * 
   * @param category the entity to be inserted.
   * @return The inserted entity.
   */
  public Category insertCategory(Category category) {
    PersistenceManager mgr = getPersistenceManager();
    try {
      if (containsCategory(category)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.makePersistent(category);
    } finally {
      mgr.close();
    }
    return category;
  }

  /**
   * This method is used for updating an existing entity. If the entity does not exist in the datastore, an exception is
   * thrown. It uses HTTP PUT method.
   * 
   * @param category the entity to be updated.
   * @return The updated entity.
   */
  public Category updateCategory(Category category) {
    PersistenceManager mgr = getPersistenceManager();
    try {
      if (!containsCategory(category)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.makePersistent(category);
    } finally {
      mgr.close();
    }
    return category;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE method.
   * 
   * @param id the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public Category removeCategory(@Named("id") Long id) {
    PersistenceManager mgr = getPersistenceManager();
    Category category = null;
    try {
      category = mgr.getObjectById(Category.class, id);
      mgr.deletePersistent(category);
    } finally {
      mgr.close();
    }
    return category;
  }

  private boolean containsCategory(Category category) {
    PersistenceManager mgr = getPersistenceManager();
    boolean contains = true;
    try {
      mgr.getObjectById(Category.class, category.getKey());
    } catch (javax.jdo.JDOObjectNotFoundException ex) {
      contains = false;
    } finally {
      mgr.close();
    }
    return contains;
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}

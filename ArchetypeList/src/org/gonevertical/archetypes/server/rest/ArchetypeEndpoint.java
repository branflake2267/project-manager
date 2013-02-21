package org.gonevertical.archetypes.server.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.gonevertical.archetypes.server.domain.Archetype;
import org.gonevertical.archetypes.server.domain.dao.PMF;
import org.gonevertical.archetypes.server.rest.errors.CustomErrors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.Results;
import com.google.appengine.api.search.ScoredDocument;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

@Api(name = "archetypeendpoint", description = "This entity represents a Archetype.", version = "v1")
public class ArchetypeEndpoint {

  private static final Index INDEX = getIndex();

  private static Index getIndex() {
    IndexSpec indexSpec = IndexSpec.newBuilder().setName("archetype_index").build();
    return SearchServiceFactory.getSearchService().getIndex(indexSpec);
  }

  private static void addToSearchIndex(Archetype o) {
    Document.Builder docBuilder = Document
        .newBuilder()
        .addField(Field.newBuilder().setName("key").setText(o.getKey()))
        .addField(Field.newBuilder().setName("name").setText(o.getName() != null ? o.getName() : ""))
        .addField(
            Field.newBuilder().setName("description")
                .setText(o.getDescription().getValue() != null ? o.getDescription().getValue() : ""))
         .addField(Field.newBuilder().setName("repository").setText(o.getRepository() != null ? o.getRepository() : ""))
         .addField(Field.newBuilder().setName("groupId").setText(o.getGroupId() != null ? o.getGroupId() : ""))
         .addField(Field.newBuilder().setName("artifactId").setText(o.getArtifactId() != null ? o.getArtifactId() : ""))
         .addField(Field.newBuilder().setName("version").setText(o.getVersion() != null ? o.getVersion() : ""));
    docBuilder.setId(o.getKey());
    Document doc = docBuilder.build();
    INDEX.putAsync(doc);
  }

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET method and paging support.
   * 
   * @return A CollectionResponse class containing the list of all entities persisted and a cursor to the next page.
   */
  public CollectionResponse<Archetype> listArchetype(@Nullable @Named("cursor") String cursorString,
      @Nullable @Named("limit") Integer limit) {

    PersistenceManager mgr = null;
    Cursor cursor = null;
    List<Archetype> execute = null;

    try {
      mgr = getPersistenceManager();
      Query query = mgr.newQuery(Archetype.class);
      if (cursorString != null && cursorString != "") {
        cursor = Cursor.fromWebSafeString(cursorString);
        HashMap<String, Object> extensionMap = new HashMap<String, Object>();
        extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
        query.setExtensions(extensionMap);
      }

      if (limit != null) {
        query.setRange(0, limit);
      }

      execute = (List<Archetype>) query.execute();
      cursor = JDOCursorHelper.getCursor(execute);
      if (cursor != null) {
        cursorString = cursor.toWebSafeString();
      }

      // Tight loop for fetching all entities from datastore and accomodate
      // for lazy fetch.
      for (Archetype obj : execute)
        ;
    } finally {
      mgr.close();
    }

    return CollectionResponse.<Archetype> builder().setItems(execute).setNextPageToken(cursorString).build();
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   * 
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public Archetype getArchetype(@Named("id") Long id) {
    PersistenceManager mgr = getPersistenceManager();
    Archetype archetype = null;
    try {
      archetype = mgr.getObjectById(Archetype.class, id);
    } finally {
      mgr.close();
    }
    return archetype;
  }

  /**
   * This inserts a new entity into App Engine datastore. If the entity already exists in the datastore, an exception is
   * thrown. It uses HTTP POST method.
   * 
   * @param archetype the entity to be inserted.
   * @return The inserted entity.
   */
  public Archetype insertArchetype(Archetype archetype, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    PersistenceManager mgr = getPersistenceManager();
    try {
      if (containsArchetype(archetype)) {
        throw new EntityExistsException("Object already exists");
      }
      mgr.makePersistent(archetype);
    } finally {
      mgr.close();
    }
    return archetype;
  }

  /**
   * This method is used for updating an existing entity. If the entity does not exist in the datastore, an exception is
   * thrown. It uses HTTP PUT method.
   * 
   * @param archetype the entity to be updated.
   * @return The updated entity.
   */
  public Archetype updateArchetype(Archetype archetype, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    PersistenceManager mgr = getPersistenceManager();
    try {
      if (!containsArchetype(archetype)) {
        throw new EntityNotFoundException("Object does not exist");
      }
      mgr.makePersistent(archetype);
      addToSearchIndex(archetype);
    } finally {
      mgr.close();
    }
    return archetype;
  }

  /**
   * This method removes the entity with primary key id. It uses HTTP DELETE method.
   * 
   * @param id the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public Archetype removeArchetype(@Named("id") Long id, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }
    
    PersistenceManager mgr = getPersistenceManager();
    Archetype archetype = null;
    try {
      archetype = mgr.getObjectById(Archetype.class, id);
      mgr.deletePersistent(archetype);
      INDEX.delete(archetype.getKey());
    } finally {
      mgr.close();
    }
    return archetype;
  }

  private boolean containsArchetype(Archetype archetype) {
    PersistenceManager mgr = getPersistenceManager();
    boolean contains = true;
    try {
      mgr.getObjectById(Archetype.class, archetype.getKey());
    } catch (javax.jdo.JDOObjectNotFoundException ex) {
      contains = false;
    } finally {
      mgr.close();
    }
    return contains;
  }

  @ApiMethod(httpMethod = "GET", name = "archetype.search", path = "archetype/search/{queryString}")
  public List<Archetype> search(@Named("queryString") String queryString) {
    List<Archetype> returnList = new ArrayList<Archetype>();
    Results<ScoredDocument> searchResults = INDEX.search(queryString);

    for (ScoredDocument scoredDoc : searchResults) {
      Field fieldKey = scoredDoc.getOnlyField("key");
      if (fieldKey == null || fieldKey.getText() == null) {
        continue;
      }

      String skey = fieldKey.getText();
      if (skey != null) {
        Key key = KeyFactory.stringToKey(skey);
        Archetype o = getArchetype(key.getId());
        returnList.add(o);
      }
    }
    return returnList;
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}

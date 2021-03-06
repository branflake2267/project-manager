package org.gonevertical.pm.directory.server.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.gonevertical.pm.directory.client.rest.SystemProperties;
import org.gonevertical.pm.directory.server.domain.Archetype;
import org.gonevertical.pm.directory.server.domain.CurrentUser;
import org.gonevertical.pm.directory.server.domain.dao.JdoUtils;
import org.gonevertical.pm.directory.server.domain.dao.PMF;
import org.gonevertical.pm.directory.server.domain.dao.SimpleFilter;
import org.gonevertical.pm.directory.server.rest.dto.CollectionResponseExtentsion;
import org.gonevertical.pm.directory.server.rest.errors.CustomErrors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
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

@Api(
    name = "archetypeendpoint",
    description = "This entity represents a Archetype.",
    version = SystemProperties.VERSION,
    clientIds = { "433818979508.apps.googleusercontent.com" },
    scopes = { "https://www.googleapis.com/auth/userinfo.email" })
public class ArchetypeEndpoint {
  
  private static Logger logger = Logger.getLogger(ArchetypeEndpoint.class.getName());

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
        .addField(Field.newBuilder().setName("description").setText(o.getDescription() != null ? o.getDescription() : ""))
        .addField(Field.newBuilder().setName("repository").setText(o.getRepository() != null ? o.getRepository() : ""))
        .addField(Field.newBuilder().setName("groupId").setText(o.getGroupId() != null ? o.getGroupId() : ""))
        .addField(Field.newBuilder().setName("artifactId").setText(o.getArtifactId() != null ? o.getArtifactId() : ""))
        .addField(Field.newBuilder().setName("version").setText(o.getVersion() != null ? o.getVersion() : ""))
        .addField(Field.newBuilder().setName("category").setText(o.getCategoriesSearch() != null ? o.getCategoriesSearch() : ""))
        .addField(Field.newBuilder().setName("tags").setText(o.getTagsSearch() != null ? o.getTagsSearch() : ""));

    docBuilder.setId(o.getKey());
    Document doc = docBuilder.build();
    INDEX.putAsync(doc);
  }

  public CollectionResponseExtentsion<Archetype> listArchetype(
      @Nullable @Named("cursor") String cursorString,
      @Nullable @Named("offset") Integer offset, 
      @Nullable @Named("limit") Integer limit) {
    logger.info("listArchetype(): cursorString=" + cursorString + " offset=" + offset + " limit=" + limit);

    PersistenceManager mgr = null;
    Cursor cursor = null;
    List<Archetype> archetypes = new ArrayList<Archetype>();
    
    try {
      mgr = getPersistenceManager();
      Query query = mgr.newQuery(Archetype.class);

      if (cursorString != null && cursorString != "") {
        cursor = Cursor.fromWebSafeString(cursorString);
        HashMap<String, Object> extensionMap = new HashMap<String, Object>();
        extensionMap.put(JDOCursorHelper.CURSOR_EXTENSION, cursor);
        query.setExtensions(extensionMap);
      }

      if (offset != null && limit != null) {
        query.setRange(offset, offset + limit);
      } else if (limit != null) {
        query.setRange(0, limit);
      } else {
        query.setRange(0, 100);
      }

      List<Archetype> items = (List<Archetype>) query.execute();

      cursor = JDOCursorHelper.getCursor(items);
      if (cursor != null) {
        cursorString = cursor.toWebSafeString();
      }

      for (Archetype archetype : items) {
        archetypes.add(archetype);
      }
        
    } finally {
      mgr.close();
    }

    ArrayList<SimpleFilter> simpleFilter = new ArrayList<SimpleFilter>();
    int total = JdoUtils.findCount(Archetype.class, simpleFilter).intValue();

    String cursorWebSafe = null;
    if (cursor != null && cursor.toWebSafeString() != null) {
      cursorWebSafe = cursor.toWebSafeString();
    }

    if (total == 0) {
      Archetype archetype = new Archetype();
      archetype.setName("Nothing exists yet...");
      archetypes.add(archetype);
    }

    return new CollectionResponseExtentsion<Archetype>(archetypes, cursorWebSafe, total);
  }

  public Archetype getArchetype(@Named("key") String key) {
    return getArchetype(KeyFactory.stringToKey(key));
  }

  public Archetype insertArchetype(Archetype archetype, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    CurrentUser currentUser = JdoUtils.getCurrentUser(guser);
    archetype.setSystemUserKey(currentUser.getSystemUser().getKey());

    JdoUtils.persistOrThrow(archetype);
    addToSearchIndex(archetype);

    return archetype;
  }

  public Archetype updateArchetype(Archetype archetype, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    JdoUtils.persistOrThrow(archetype);
    addToSearchIndex(archetype);

    return archetype;
  }

  public Archetype removeArchetype(@Named("key") String key, com.google.appengine.api.users.User guser)
      throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    PersistenceManager mgr = getPersistenceManager();
    Archetype archetype = null;
    try {
      archetype = mgr.getObjectById(Archetype.class, KeyFactory.stringToKey(key).getId());
      INDEX.delete(archetype.getKey());
      mgr.deletePersistent(archetype);
    } finally {
      mgr.close();
    }
    return null;
  }

  @ApiMethod(httpMethod = "GET", name = "archetype.search", path = "archetype/search/{queryString}")
  public CollectionResponseExtentsion<Archetype> search(@Named("queryString") String queryString,
      @Nullable @Named("limit") Integer limit) {
    List<Archetype> items = new ArrayList<Archetype>();
    Results<ScoredDocument> searchResults = INDEX.search(queryString);

    int total = searchResults.getNumberReturned();
    int count = 0;
    for (ScoredDocument scoredDoc : searchResults) {
      Field fieldKey = scoredDoc.getOnlyField("key");
      if (fieldKey == null || fieldKey.getText() == null) {
        continue;
      }

      String skey = fieldKey.getText();
      if (skey != null) {
        Key key = KeyFactory.stringToKey(skey);
        Archetype o = getArchetype(key);
        items.add(o);
      }

      count++;
      if (count >= limit) {
        break;
      }
    }

    return new CollectionResponseExtentsion<Archetype>(items, null, total);
  }

  private Archetype getArchetype(Key key) {
    return JdoUtils.findAndCatchErrors(Archetype.class, key);
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}

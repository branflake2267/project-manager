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
import org.gonevertical.pm.directory.server.domain.Tag;
import org.gonevertical.pm.directory.server.domain.dao.JdoUtils;
import org.gonevertical.pm.directory.server.domain.dao.PMF;
import org.gonevertical.pm.directory.server.domain.dao.SimpleFilter;
import org.gonevertical.pm.directory.server.rest.dto.CollectionResponseExtentsion;
import org.gonevertical.pm.directory.server.rest.errors.CustomErrors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.search.Document;
import com.google.appengine.api.search.Field;
import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.IndexSpec;
import com.google.appengine.api.search.SearchServiceFactory;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

@Api(
    name = "tagendpoint",
    version = SystemProperties.VERSION,
    clientIds = { "433818979508.apps.googleusercontent.com" },
    scopes = { "https://www.googleapis.com/auth/userinfo.email" })
public class TagEndpoint {
  
  private static Logger logger = Logger.getLogger(TagEndpoint.class.getName());
  
  private static final Index INDEX = getIndex();

  private static Index getIndex() {
    IndexSpec indexSpec = IndexSpec.newBuilder().setName("tag_index").build();
    return SearchServiceFactory.getSearchService().getIndex(indexSpec);
  }

  private static void addToSearchIndex(Tag o) {
    Document.Builder docBuilder = Document
        .newBuilder()
        .addField(Field.newBuilder().setName("key").setText(o.getKey()))
        .addField(Field.newBuilder().setName("name").setText(o.getName() != null ? o.getName() : ""));

    docBuilder.setId(o.getKey());
    Document doc = docBuilder.build();
    INDEX.putAsync(doc);
  }
  
  public CollectionResponseExtentsion<Tag> listTag(
      @Nullable @Named("cursor") String cursorString,
      @Nullable @Named("offset") Integer offset, 
      @Nullable @Named("limit") Integer limit) {
    logger.info("listArchetype(): cursorString=" + cursorString + " offset=" + offset + " limit=" + limit);
    PersistenceManager mgr = null;
    Cursor cursor = null;
    List<Tag> tags = new ArrayList<Tag>();
    
    try {
      mgr = getPersistenceManager();
      Query query = mgr.newQuery(Tag.class);

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
        query.setRange(0, 15);
      }

      List<Tag> items = (List<Tag>) query.execute();

      cursor = JDOCursorHelper.getCursor(items);
      if (cursor != null) {
        cursorString = cursor.toWebSafeString();
      }

      for (Tag tag : items) {
        tags.add(tag);
      }
        
    } finally {
      mgr.close();
    }

    ArrayList<SimpleFilter> simpleFilter = new ArrayList<SimpleFilter>();
    int total = JdoUtils.findCount(Tag.class, simpleFilter).intValue();

    String cursorWebSafe = "";
    if (cursor != null && cursor.toWebSafeString() != null) {
      cursorWebSafe = cursor.toWebSafeString();
    }

    if (total == 0) {
      Tag tag = new Tag();
      tag.setName("Nothing exists yet...");
      tags.add(tag);
      total = 1;
    }

    return new CollectionResponseExtentsion<Tag>(tags, cursorWebSafe, total);
  }

  public Tag getTag(@Named("id") Long id) {
    return JdoUtils.find(Tag.class, id);
  }

  public Tag insertTag(Tag tag, com.google.appengine.api.users.User guser) {
    tag = JdoUtils.persist(tag);
    addToSearchIndex(tag);
    return tag;
  }

  public Tag updateTag(Tag tag, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    tag = JdoUtils.persist(tag);
    addToSearchIndex(tag);
    
    return tag;
  }

  public Tag removeTag(@Named("id") Long id, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    Tag tag = getTag(id);
    JdoUtils.remove(tag);
    
    return tag;
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}

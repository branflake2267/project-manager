package org.gonevertical.pm.directory.server.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.gonevertical.pm.directory.server.domain.Category;
import org.gonevertical.pm.directory.server.domain.dao.JdoUtils;
import org.gonevertical.pm.directory.server.domain.dao.PMF;
import org.gonevertical.pm.directory.server.domain.dao.SimpleFilter;
import org.gonevertical.pm.directory.server.rest.dto.CollectionResponseExtentsion;
import org.gonevertical.pm.directory.server.rest.errors.CustomErrors;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.datanucleus.query.JDOCursorHelper;

@Api(name = "categoryendpoint")
public class CategoryEndpoint {

  /**
   * This method lists all the entities inserted in datastore. It uses HTTP GET method and paging support.
   * 
   * @return A CollectionResponse class containing the list of all entities persisted and a cursor to the next page.
   */
  @SuppressWarnings({"unchecked", "unused"})
  public CollectionResponse<Category> listCategory() {
    PersistenceManager mgr = null;
    Cursor cursor = null;
    List<Category> items = null;

    String cursorString = null;
    try {
      mgr = getPersistenceManager();
      Query query = mgr.newQuery(Category.class);

      items = (List<Category>) query.execute();
      cursor = JDOCursorHelper.getCursor(items);

      if (cursor != null) {
        cursorString = cursor.toWebSafeString();
      }

      for (Category obj : items);
    } finally {
      mgr.close();
    }
    
    if (items == null) {
      items = new ArrayList<Category>();
    }

    return CollectionResponse.<Category> builder().setItems(items).setNextPageToken(cursorString).build();
  }

  public Category getCategory(@Named("id") Long id) {
    return JdoUtils.find(Category.class, id);
  }

  public Category insertCategory(Category category, com.google.appengine.api.users.User guser) {
    category = JdoUtils.persist(category);
    return category;
  }

  public Category updateCategory(Category category, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    category = JdoUtils.persist(category);
    return category;
  }

  public Category removeCategory(@Named("id") Long id, com.google.appengine.api.users.User guser) throws Exception {
    if (guser == null) {
      throw new UnauthorizedException(CustomErrors.MUST_LOG_IN.toString());
    }

    Category category = getCategory(id);
    JdoUtils.remove(category);
    return category;
  }

  @ApiMethod(httpMethod = "GET", name = "category.children", path = "category/children")
  public CollectionResponseExtentsion<Category> findChildren(@Nullable @Named("parentKey") String parentKey) {
    Key key = KeyFactory.stringToKey(parentKey);

    ArrayList<SimpleFilter> simpleFilter = new ArrayList<SimpleFilter>();
    simpleFilter.add(new SimpleFilter("parentKey", FilterOperator.EQUAL, key));

    List<Category> items = JdoUtils.findList(Category.class, simpleFilter, 0, 1000);

    if (items == null) {
      items = new ArrayList<Category>();
    }
    
    return new CollectionResponseExtentsion<Category>(items, null, 0);
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}

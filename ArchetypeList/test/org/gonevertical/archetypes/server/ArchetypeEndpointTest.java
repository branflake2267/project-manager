package org.gonevertical.archetypes.server;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.gonevertical.archetypes.server.domain.Archetype;
import org.gonevertical.archetypes.server.domain.Category;
import org.gonevertical.archetypes.utils.TestUtils;
import org.junit.Test;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.gson.Gson;

public class ArchetypeEndpointTest extends TestUtils {
  
  private String url = "http://localhost:8888/_ah/api/archetype/v1/";

  /**
   * curl -H 'Content-Type: application/json' -d '{ json }' http://localhost:8888/_ah/api/archetype/v1/
   * curl -H 'Content-Type: application/json' -d '{ json }' https://demo.appspot.com/_ah/api/archetype/v1/
   */
  @Test
  public void testInsert() {
    // given
    Category cat1 = new Category();
    cat1.setName("cat1");
    Category cat2 = new Category();
    cat2.setName("cat2");
    
    List<Category> tags = new ArrayList<Category>();
    tags.add(cat1);
    tags.add(cat2);
    
    List<Key> categories = new ArrayList<Key>();
    categories.add(KeyFactory.createKey(Archetype.class.getSimpleName(), 1));
    categories.add(KeyFactory.createKey(Archetype.class.getSimpleName(), 2));
    
    Archetype archtype = new Archetype();
    archtype.setArtifactId("artifactId");
    archtype.setCategories(categories);
    archtype.setDescription("description");
    archtype.setGroupId("groupId");
    archtype.setName("name");
    archtype.setRepository("repository");
    archtype.setTags(tags);
    archtype.setVersion("version");
    
    Gson gson = new Gson();
    String json = gson.toJson(archtype);
    
    // when
    String responseJson = postRequest(url, json);
    
    // then
    assertTrue(responseJson.contains("\"id\" :"));
  }
  
//  /**
//   * curl http://localhost:8888/_ah/api/userendpoint/v1/user/list
//   * curl https://democloudpoint.appspot.com/_ah/api/userendpoint/v1/user/list
//   */
//  @Test
//  public void testList() {
//    String content = getRequest("http://localhost:8888/_ah/api/userendpoint/v1/user/list?limit=2");
//    assertTrue(content.contains("\"items\" :"));
//  }
//  
//  @Test
//  public void testGet() {
//    String url = "http://localhost:8888/_ah/api/userendpoint/v1/user/insert";
//    String json = "{ \"nameFirst\": \"Brandon\" }";
//    String content = postRequest(url, json);
//    
//    JSONObject jso = null;
//    try {
//      jso = new JSONObject(content);
//    } catch (JSONException e) {
//      e.printStackTrace();
//      fail();
//    }
//    
//    Long id = -1l;
//    try {
//      id = jso.getLong("id");
//    } catch (JSONException e) {
//      e.printStackTrace();
//    }
//    
//    url = "http://localhost:8888/_ah/api/userendpoint/v1/user/get/" + id;
//    content = getRequest(url);
//    assertTrue(content.contains("\"id\" :"));
//  }
//  
//  @Test
//  public void testUpdate() {
//    String url = "http://localhost:8888/_ah/api/userendpoint/v1/user/insert";
//    String json = "{ \"nameFirst\": \"Brandon\" }";
//    String content = postRequest(url, json);
//    
//    JSONObject jso = null;
//    try {
//      jso = new JSONObject(content);
//    } catch (JSONException e) {
//      e.printStackTrace();
//      fail();
//    }
//    
//    url = "http://localhost:8888/_ah/api/userendpoint/v1/user/update";
//    try {
//      jso.put("nameLast", "Donnelson");
//    } catch (JSONException e1) {
//      e1.printStackTrace();
//      fail();
//    }
//    
//    json = jso.toString();
//    
//    content = postRequest(url, json);
//    
//    try {
//      JSONObject jso2 = new JSONObject(content);
//      String nameFirst = jso2.getString("nameFirst");
//      String nameLast = jso2.getString("nameLast");
//      assertEquals("Brandon", nameFirst);
//      assertEquals("Donnelson", nameLast);
//    } catch (JSONException e) {
//      e.printStackTrace();
//      fail();
//    }
//  }
//  
//  @Test
//  public void testSearch() {
//    String content = getRequest("http://localhost:8888/_ah/api/userendpoint/v1/user/search/brandon");
//    assertTrue(content.contains("\"items\" :"));
//  }
//  
//  @Test
//  public void testRemove() {
//    String url = "http://localhost:8888/_ah/api/userendpoint/v1/user/insert";
//    String json = "{ \"nameFirst\": \"Brandon\" }";
//    String content = postRequest(url, json);
//    
//    JSONObject jso = null;
//    try {
//      jso = new JSONObject(content);
//    } catch (JSONException e) {
//      e.printStackTrace();
//      fail();
//    }
//    
//    long id = 0;
//    try {
//      id = jso.getLong("id");
//    } catch (JSONException e) {
//      e.printStackTrace();
//      fail();
//    }
//    
//    url = "http://localhost:8888/_ah/api/userendpoint/v1/user/remove/" + id;
//    content = getRequest(url);
//    assertTrue(content.contains(Long.toString(id)));
//  }
  
}

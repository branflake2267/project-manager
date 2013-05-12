package org.gonevertical.pm.directory.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gonevertical.pm.directory.server.domain.dto.Archetype;
import org.gonevertical.pm.directory.server.domain.dto.ArchetypeCollection;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class ArchetypeEndpointTest {

  private String url = "http://localhost:8888/_ah/api/archetypeendpoint/v1/archetype";
  private String devAppserverLoginCookie;

  @Before
  public void before() {
    // Response response = RestAssured.given().param("email",
    // "branfake2267@gmail.com").param("continue", "/")
    // .param("action",
    // "Log In").expect().statusCode(302).when().post("http://localhost:8888/_ah/login?continue=%2F");
    //
    // devAppserverLoginCookie = response.getCookie("dev_appserver_login");
  }

  @Test
  public void testListRemote() {
    String url = "https://project-directory.appspot.com/_ah/api/archetypeendpoint/v1/archetype";
    ArchetypeCollection collection = RestAssured.get(url).as(ArchetypeCollection.class);

    System.out.println("test");
  }

  @Test
  public void testListRemote2() {
    String url = "https://project-directory.appspot.com/_ah/api/archetypeendpoint/v1/archetype";
    String json = RestAssured.given().expect().when().get(url).asString();

    System.out.println(json);

    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
      public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
          throws JsonParseException {
        return new Date(json.getAsJsonPrimitive().getAsLong());
      }
    });
    gsonBuilder.registerTypeAdapter(ArchetypeCollection.class, new JsonDeserializer<ArchetypeCollection>() {
      public ArchetypeCollection deserialize(JsonElement json, Type typeOft, JsonDeserializationContext context)
          throws JsonParseException {
        JsonObject parentJson = json.getAsJsonObject();
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
          public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
              throws JsonParseException {
            return new Date(json.getAsJsonPrimitive().getAsLong());
          }
        });
        Gson gson = gsonBuilder.create();
        
        ArchetypeCollection parent = gson.fromJson(json, ArchetypeCollection.class);
        List<Archetype> archetypes = null;

        if (parentJson.get("items").isJsonArray()) {
          JsonElement itemsJson = parentJson.get("items");
          archetypes = gson.fromJson(itemsJson, new TypeToken<List<Archetype>>() {
          }.getType());
        } else {
          Archetype single = gson.fromJson(parentJson.get("items"), Archetype.class);
          archetypes = new ArrayList<Archetype>();
          archetypes.add(single);
        }
        parent.setArchetypes(archetypes);
        return parent;
      }
    });

    Gson gson = gsonBuilder.create();
    ArchetypeCollection ac = gson.fromJson(json, ArchetypeCollection.class);

    System.out.println("items");
  }

  @Test
  public void testList() {
    createArchetypeAndPersist(3);

    List<Archetype> items = RestAssured.given().param("limit", "2").expect().when().get(url).jsonPath()
        .getList("items");

    assertEquals(2, items.size());
  }

  @Test
  public void testListPage2() {
    createArchetypeAndPersist(20);

    List<Archetype> items = RestAssured.given().param("offset", "10").and().param("limit", "10").expect().when()
        .get(url).jsonPath().getList("items");

    assertEquals(10, items.size());
  }

  @Test
  public void testListWithOffset() {
    createArchetypeAndPersist(4);

    List<Archetype> list = RestAssured.given().param("offset", "1").and().param("limit", "2").expect().when().get(url)
        .jsonPath().getList("items");

    assertNotNull(list);
    assertNotNull(list.size() > 1);
  }

  @Test
  public void testInsertWithAuthentication() {
    Archetype archetype = createBasicArchetype();

    // insert
    Archetype newArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(archetype).expect().statusCode(200)
        .when().post(url).as(Archetype.class);

    assertNotNull(newArchetype.getSystemUserKey());
    // assertTrue(newArchetype.getDateCreated() > 0);
  }

  @Test
  public void testInsertWithOutAuthentication() {
    RestAssured.given().contentType(ContentType.JSON).content(new Archetype()).expect().statusCode(401).when()
        .post(url);
  }

  @Test
  public void testUpdateWithCredentials() {
    Archetype archetype = createBasicArchetype();

    // created
    Archetype newArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(archetype).expect().statusCode(200)
        .when().post(url).as(Archetype.class);

    newArchetype.setName("nameUpdated");

    // updated
    Archetype updateArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(newArchetype).expect().statusCode(200)
        .when().put(url).as(Archetype.class);

    assertEquals("nameUpdated", updateArchetype.getName());
  }

  @Test
  public void testUpdateWithOutAuthentication() {
    RestAssured.given().contentType(ContentType.JSON).content(new Archetype()).expect().statusCode(401).when()
        .post(url);
  }

  @Test
  public void testRemoveWithCredentials() {
    Archetype archetype = createBasicArchetype();

    // created
    Archetype newArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(archetype).expect().statusCode(200)
        .when().post(url).as(Archetype.class);

    // removed
    RestAssured.given().contentType(ContentType.JSON).cookie("dev_appserver_login", devAppserverLoginCookie).and()
        .when().delete(url + "/" + newArchetype.getKey()).print();
  }

  @Test
  public void testRemoveWithOutAuthentication() {
    RestAssured.given().contentType(ContentType.JSON).content(new Archetype()).expect().statusCode(401).when()
        .delete(url + "/fakeid");
  }

  @Test
  public void testGetArchetype() {
    Archetype archetype = createBasicArchetype();

    // created
    Archetype newArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(archetype).expect().statusCode(200)
        .when().post(url).as(Archetype.class);

    // retrieve
    Archetype getArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).expect().statusCode(200).when()
        .get(url + "/" + newArchetype.getKey()).as(Archetype.class);

    assertEquals(newArchetype.getKey(), getArchetype.getKey());
  }

  @Test
  public void testSearch() {
    Archetype archetype = createBasicArchetype();
    archetype.setName("zebra");

    // created
    for (int i = 0; i < 4; i++) {
      RestAssured.given().contentType(ContentType.JSON).cookie("dev_appserver_login", devAppserverLoginCookie).and()
          .content(archetype).expect().statusCode(200).when().post(url).as(Archetype.class);
    }

    // retrieve
    List<Archetype> list = RestAssured.given().param("limit", "2").contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).expect().statusCode(200).when()
        .get(url + "/search/zebra").jsonPath().getList("items");

    assertTrue(list.size() == 2);
  }

  @Test
  public void testAllProperties() {
    Archetype archetype = createBasicArchetype();

    // TODO categories
    // TODO tags

    // created
    Archetype newArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(archetype).expect().statusCode(200)
        .when().post(url).as(Archetype.class);

    // retrieve
    Archetype getArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).expect().statusCode(200).when()
        .get(url + "/" + newArchetype.getKey()).as(Archetype.class);

    assertEquals(archetype.getName(), getArchetype.getName());
    assertEquals(archetype.getRepository(), getArchetype.getRepository());
    assertEquals(archetype.getGroupId(), getArchetype.getGroupId());
    assertEquals(archetype.getArtifactId(), getArchetype.getArtifactId());
    assertEquals(archetype.getVersion(), getArchetype.getVersion());

  }

  private Archetype createBasicArchetype() {
    Archetype archetype = new Archetype();
    archetype.setName("testName");
    archetype.setDescription("testDescription");
    archetype.setRepository("testRepository");
    archetype.setGroupId("testGroupid");
    archetype.setArtifactId("testArtifactid");
    archetype.setVersion("testVersion");
    return archetype;
  }

  public void createArchetypeAndPersist(int count) {
    for (int i = 0; i < count; i++) {
      Archetype archetype = createBasicArchetype();

      RestAssured.given().contentType(ContentType.JSON).cookie("dev_appserver_login", devAppserverLoginCookie).and()
          .content(archetype).when().post(url).as(Archetype.class);
    }
  }

}

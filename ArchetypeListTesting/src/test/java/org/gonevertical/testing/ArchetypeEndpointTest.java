package org.gonevertical.testing;

import static org.junit.Assert.assertEquals;

import org.gonevertical.archetypes.server.domain.Archetype;
import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class ArchetypeEndpointTest {

  private String url = "http://localhost:8888/_ah/api/archetypeendpoint/v1/archetype";
  private String devAppserverLoginCookie;

  @Before
  public void before() {
    Response response = RestAssured.given().param("email", "branfake2267@gmail.com").param("continue", "/")
        .param("action", "Log In").expect().statusCode(302).when().post("http://localhost:8888/_ah/login?continue=%2F");

    devAppserverLoginCookie = response.getCookie("dev_appserver_login");
  }

  @Test
  public void testLogin() {
    RestAssured.given().param("email", "branfake2267@gmail.com").param("continue", "/").param("action", "Log In")
        .then().expect().statusCode(302).when().post("http://localhost:8888/_ah/login?continue=%2F");
  }

  @Test
  public void testList() {
    RestAssured.given().param("limit", "2").expect().statusCode(200).when().get(url);
  }

  @Test
  public void testInsertWithAuthentication() {
    Archetype archetype = new Archetype();
    archetype.setName("name");
    archetype.setRepository("repository");
    archetype.setGroupId("groupid");
    archetype.setArtifactId("artifactid");
    archetype.setVersion("version");

    RestAssured.given().contentType(ContentType.JSON).cookie("dev_appserver_login", devAppserverLoginCookie).and()
        .content(archetype).expect().statusCode(200).when().post(url);
  }

  @Test
  public void testInsertWithOutAuthentication() {
    RestAssured.given().contentType(ContentType.JSON).content(new Archetype()).expect().statusCode(401).when()
        .post(url);
  }

  @Test
  public void testUpdateWithCredentials() {
    Archetype archetype = new Archetype();
    archetype.setName("name");
    archetype.setRepository("repository");
    archetype.setGroupId("groupid");
    archetype.setArtifactId("artifactid");
    archetype.setVersion("version");

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
    Archetype archetype = new Archetype();
    archetype.setName("name");
    archetype.setRepository("repository");
    archetype.setGroupId("groupid");
    archetype.setArtifactId("artifactid");
    archetype.setVersion("version");

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
        .delete(url);
  }

  @Test
  public void testGetArchetype() {
    Archetype archetype = new Archetype();
    archetype.setName("name");
    archetype.setRepository("repository");
    archetype.setGroupId("groupid");
    archetype.setArtifactId("artifactid");
    archetype.setVersion("version");

    // created
    Archetype newArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().content(archetype).expect().statusCode(200)
        .when().post(url).as(Archetype.class);

    Archetype getArchetype = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).and().when().get(url + "/" + newArchetype.getKey())
        .as(Archetype.class);

    assertEquals(newArchetype.getKey(), getArchetype.getKey());
  }

  @Test
  public void testSearch() {

  }

}

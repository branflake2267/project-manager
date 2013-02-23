package org.gonevertical.testing;

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
    String json = "{\"name\":\"name\",\"repository\":\"repository\",\"groupId\":\"groupId\","
        + "\"artifactId\":\"artifactId\",\"version\":\"version\"}";

    RestAssured.given().contentType(ContentType.JSON).cookie("dev_appserver_login", devAppserverLoginCookie).and()
        .content(json).expect().statusCode(200).when().post(url);
  }

  @Test
  public void testInsertWithNoAuthentication() {
    String json = "{\"name\":\"name\",\"repository\":\"repository\",\"groupId\":\"groupId\","
        + "\"artifactId\":\"artifactId\",\"version\":\"version\"}";

    RestAssured.given().contentType(ContentType.JSON).content(json).expect().statusCode(401).when().post(url);
  }

}

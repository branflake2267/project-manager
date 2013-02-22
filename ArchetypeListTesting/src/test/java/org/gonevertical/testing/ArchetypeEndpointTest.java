package org.gonevertical.testing;

import junit.framework.TestCase;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class ArchetypeEndpointTest extends TestCase {

  private String url = "http://localhost:8888/_ah/api/archetypeendpoint/v1/archetype";

  public void testLogin() {
    RestAssured.given().param("email", "branfake2267@gmail.com").param("continue", "/").param("action", "Log In")
        .expect().statusCode(200).when().post("http://localhost:8888");
  }

  public void testList() {
    RestAssured.given().param("limit", "2").expect().statusCode(200).when().get(url).print();
  }

  public void testInsert() {
    Response response = RestAssured.given().param("email", "branfake2267@gmail.com").param("continue", "/").param("action", "Log In")
        .expect().statusCode(200).when().post("http://localhost:8888");
    
    String loginCookie = response.getCookie("dev_appserver_login");

    String json = "{\"name\":\"name\",\"repository\":\"repository\",\"groupId\":\"groupId\","
        + "\"artifactId\":\"artifactId\",\"version\":\"version\"}";

    RestAssured.given().contentType(ContentType.JSON).content(json).expect().statusCode(200).when().post(url).print();
  }

}

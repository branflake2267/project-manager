package org.gonevertical.pm.directory.testing;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class CurrentUserEndpointTest {

  private String url = "http://localhost:8888/_ah/api/currentuserendpoint/v1/currentuser";
  private String devAppserverLoginCookie;

  @Before
  public void before() {
    Response response = RestAssured.given().param("email", "branfake2267@gmail.com").param("continue", "/")
        .param("action", "Log In").expect().statusCode(302).when().post("http://localhost:8888/_ah/login?continue=%2F");

    devAppserverLoginCookie = response.getCookie("dev_appserver_login");
  }

  @Test
  public void testGetCurrentUserLoggedIn() {
    RestAssured.given().contentType(ContentType.JSON).cookie("dev_appserver_login", devAppserverLoginCookie).expect()
        .when().get(url).print();

    System.out.println("test");

    // TODO test user was created with the googleid

  }

  @Test
  public void testGetCurrentUserLoggedOut() {

  }

}

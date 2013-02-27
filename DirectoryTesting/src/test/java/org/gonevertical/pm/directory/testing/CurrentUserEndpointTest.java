package org.gonevertical.pm.directory.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.gonevertical.pm.directory.server.domain.dto.CurrentUser;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class CurrentUserEndpointTest {

  private String url = "http://localhost:8888/_ah/api/currentuserendpoint/v1/currentuser";

  @Test
  public void testGetCurrentUserLoggedIn() {
    // given logged in
    Response response = RestAssured.given().param("email", "branfake2267@gmail.com").param("continue", "/")
        .param("action", "Log In").expect().statusCode(302).when().post("http://localhost:8888/_ah/login?continue=%2F");
    String devAppserverLoginCookie = response.getCookie("dev_appserver_login");

    // when current user
    CurrentUser currentUser = RestAssured.given().contentType(ContentType.JSON)
        .cookie("dev_appserver_login", devAppserverLoginCookie).expect().statusCode(200).when().get(url)
        .as(CurrentUser.class);

    // then
    assertNotNull(currentUser);
    assertNotNull(currentUser.getSystemUser());
    assertNotNull(currentUser.getSystemUser().getGoogleId());
    assertTrue(currentUser.getIsLoggedIn());
    assertFalse(currentUser.getIsAdmin());
    assertNotNull(currentUser.getLoginUrl());
    assertNotNull(currentUser.getLogoutUrl());
    assertNotNull(currentUser.getNickname());
  }

  @Test
  public void testGetCurrentUserLoggedOut() {
    // given when current user is not logged in
    CurrentUser currentUser = RestAssured.given().contentType(ContentType.JSON).when()
        .get(url).as(CurrentUser.class);

    // then
    assertNotNull(currentUser);
    assertNull(currentUser.getSystemUser());
    assertFalse(currentUser.getIsLoggedIn());
    assertFalse(currentUser.getIsAdmin());
  }

}

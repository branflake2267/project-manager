package org.gonevertical.testing;

import junit.framework.TestCase;

import com.jayway.restassured.RestAssured;

public class TestUnit extends TestCase  {
  
  private String url = "http://localhost:8888/_ah/api/archetypeendpoint/v1/archetype";

  public void testList() {
    RestAssured.given().param("limit", "2").expect().statusCode(200).when().get(url).prettyPrint();
  }

}

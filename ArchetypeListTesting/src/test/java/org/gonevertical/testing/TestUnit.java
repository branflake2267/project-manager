package org.gonevertical.testing;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.gonevertical.archetypes.server.domain.Archetype;
import org.gonevertical.archetypes.server.domain.Category;

import com.jayway.restassured.RestAssured;

public class TestUnit extends TestCase  {
  
  private String url = "http://localhost:8888/_ah/api/archetypeendpoint/v1/archetype";

  public void testList() {
    RestAssured.given().param("limit", "2").expect().statusCode(200).when().get(url);
  }
  
  public void testInsert() {
    // given
    Category cat1 = new Category();
    cat1.setName("cat1");
    Category cat2 = new Category();
    cat2.setName("cat2");
    
    List<Category> tags = new ArrayList<Category>();
    tags.add(cat1);
    tags.add(cat2);
    
    List<String> categories = new ArrayList<String>();
    categories.add("key1");
    categories.add("key2");
    
    Archetype archtype = new Archetype();
    archtype.setArtifactId("artifactId");
    archtype.setCategories(categories);
    archtype.setDescription("description");
    archtype.setGroupId("groupId");
    archtype.setName("name");
    archtype.setRepository("repository");
    archtype.setTags(tags);
    archtype.setVersion("version");
    
    
  }

}

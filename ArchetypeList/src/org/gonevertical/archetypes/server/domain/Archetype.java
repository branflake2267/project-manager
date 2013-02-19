package org.gonevertical.archetypes.server.domain;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.persistence.Id;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Archetype {

  @Id
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  private BlobKey blobKey;
  private String name;
  private Text description;
  private List<Key> categories;
  private List<Category> tags;

  private String repository;
  private String groupId;
  private String artifactId;
  private String version;

  public String getKey() {
    return KeyFactory.keyToString(key);
  }

  public void setKey(String key) {
    this.key = KeyFactory.stringToKey(key);
  }

  // TODO
  public String getBlobUrl() {
    return null;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Text getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = new Text(description);
  }

  public List<String> getCategories() {
    if (categories == null) {
      return null;
    }
    
    ArrayList<String> list = new ArrayList<String>();
    for (Key key : categories) {
      list.add(KeyFactory.keyToString(key));
    }
      
    return list;
  }

  public void setCategories(List<Key> categories) {
    this.categories = categories;
  }

  public List<Category> getTags() {
    return tags;
  }

  public void setTags(List<Category> tags) {
    this.tags = tags;
  }

  public String getRepository() {
    return repository;
  }

  public void setRepository(String repository) {
    this.repository = repository;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getArtifactId() {
    return artifactId;
  }

  public void setArtifactId(String artifactId) {
    this.artifactId = artifactId;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

}

package org.gonevertical.pm.directory.server.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;

@PersistenceCapable(detachable = "true")
public class Archetype {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  private BlobKey blobKey;
  private String name;
  private Text description;
  private LinkedHashSet<Key> categories;
  private HashSet<Category> tags;

  private String repository;
  private String groupId;
  private String artifactId;
  private String version;
  
  private Date dateCreated;
  private Key systemUserKey;
  
  public Archetype() {
    dateCreated = new Date();
  }

  public String getKey() {
    if (key == null) {
      return "";
    }
    return KeyFactory.keyToString(key);
  }

  public void setKey(String key) {
    if (key == null || key.trim().length() == 0) {
      this.key = null;
    } else {
      this.key = KeyFactory.stringToKey(key);
    }
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

  public String getDescription() {
    if (description == null) {
      return null;
    }
    return description.getValue();
  }

  public void setDescription(String description) {
    if (description == null) {
      this.description = null;
    } else {
      this.description = new Text(description);
    }
  }

  public List<String> getCategories() {
    if (categories == null) {
      return new ArrayList<String>();
    }
    ArrayList<String> list = new ArrayList<String>();
    for (Key key : categories) {
      list.add(KeyFactory.keyToString(key));
    }
    return list;
  }

  public void setCategories(List<String> categories) {
    if (categories == null) {
      return;
    }
    categories.clear();
    for (String skey : categories) {
      this.categories.add(KeyFactory.stringToKey(skey));
    }
  }

  public List<Category> getTags() {
    if (tags == null) {
      return new ArrayList<Category>();
    }
    return new ArrayList<Category>(tags);
  }

  public void setTags(List<Category> tags) {
    if (tags == null) {
      this.tags = new HashSet<Category>();
    } else {
      this.tags = new HashSet<Category>(tags);
    }
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

  public void setSystemUserKey(String key) {
    if (key == null) {
      systemUserKey = null;
    } else {
      systemUserKey = KeyFactory.stringToKey(key);
    }
  }
  
  public String getSystemUserKey() {
    if (systemUserKey == null) {
      return null;
    }
    return KeyFactory.keyToString(systemUserKey);
  }
  
  public Long getDateCreated() {
    if (dateCreated == null) {
      return null;
    }
    return dateCreated.getTime();
  }
  
}

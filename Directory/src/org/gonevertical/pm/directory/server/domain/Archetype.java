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
import com.google.appengine.datanucleus.annotations.Unowned;

@PersistenceCapable(detachable = "true")
public class Archetype {

  @PrimaryKey
  @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
  private Key key;

  @Persistent
  private BlobKey blobKey;
  
  @Persistent
  private String name;
  
  @Persistent
  private Text description;
  
  @Persistent(defaultFetchGroup = "true")
  @Unowned
  private HashSet<Category> categories;
  
  @Persistent(defaultFetchGroup = "true")
  @Unowned
  private HashSet<Tag> tags;

  @Persistent
  private String repository;
  
  @Persistent
  private String groupId;
  
  @Persistent
  private String artifactId;
  
  @Persistent
  private String version;
  
  @Persistent
  private Date dateCreated;
  
  @Persistent 
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

  public List<Category> getCategories() {
    if (categories == null) {
      return new ArrayList<Category>();
    }
    return new ArrayList<Category>(categories);
  }

  public void setCategories(List<Category> categoriesList) {
    if (categoriesList == null) {
      this.categories = null;
      return;
    }
    categories = new LinkedHashSet<Category>(categoriesList);
  }

  public List<Tag> getTags() {
    if (tags == null) {
      return new ArrayList<Tag>();
    }
    return new ArrayList<Tag>(tags);
  }

  public void setTags(List<Tag> tagsList) {
    if (tagsList == null) {
      this.tags = new HashSet<Tag>();
    } else {
      this.tags = new HashSet<Tag>(tagsList);
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

  public String getCategoriesSearch() {
    if (categories == null) {
      return null;
    }
    String search = "";
    for (Category category : categories) {
      if (category.getName() != null) {
        search += category.getName();
      }
    } 
    return search;
  }

  public String getTagsSearch() {
    if (tags == null) {
      return null;
    }
    String search = "";
    for (Tag tag : tags) {
      if (tag.getName() != null) {
        search += tag.getName();
      }
    }
    return search;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((key == null) ? 0 : key.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Archetype other = (Archetype) obj;
    if (key == null) {
      if (other.key != null)
        return false;
    } else if (!key.equals(other.key))
      return false;
    return true;
  }
  
}

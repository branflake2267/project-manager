package org.gonevertical.pm.directory.server.domain.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Archetype implements Serializable {
  private String key;
  private String blobKey;
  private String name;
  private String description;
  private List<Category> categories;
  private List<Tag> tags;
  private String repository;
  private String groupId;
  private String artifactId;
  private String version;
  private Date dateCreated;
  private String systemUserKey;
  private String categoriesSearch;
  private String kind;

  public String getCategoriesSearch() {
    return categoriesSearch;
  }

  public void setCategoriesSearch(String categoriesSearch) {
    this.categoriesSearch = categoriesSearch;
  }

  public String getKind() {
    return kind;
  }

  public void setKind(String kind) {
    this.kind = kind;
  }

  public Archetype() {
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getBlobKey() {
    return blobKey;
  }

  public void setBlobKey(String blobKey) {
    this.blobKey = blobKey;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Category> getCategories() {
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public List<Tag> getTags() {
    return tags;
  }

  public void setTags(List<Tag> tags) {
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

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public String getSystemUserKey() {
    return systemUserKey;
  }

  public void setSystemUserKey(String systemUserKey) {
    this.systemUserKey = systemUserKey;
  }
}

package org.gonevertical.pm.directory.server.domain.dto;

import java.util.List;

public class Archetype {

  private String key;
  private String blobKey;
  private String name;
  private String description;
  private List<String> categories;
  private List<Category> tags;

  private String repository;
  private String groupId;
  private String artifactId;
  private String version;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public String getBlobUrl() {
    return blobKey;
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

  public List<String> getCategories() {
    return categories;
  }

  public void setCategories(List<String> categories) {
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

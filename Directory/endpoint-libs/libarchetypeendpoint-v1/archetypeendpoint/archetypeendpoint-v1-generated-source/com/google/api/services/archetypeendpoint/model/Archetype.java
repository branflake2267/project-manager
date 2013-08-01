/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2013-04-29 14:08:19 UTC)
 * on 2013-04-30 at 06:09:24 UTC 
 * Modify at your own risk.
 */

package com.google.api.services.archetypeendpoint.model;

/**
 * Model definition for Archetype.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the . For a detailed explanation see:
 * <a href="http://code.google.com/p/google-api-java-client/wiki/Json">http://code.google.com/p/google-api-java-client/wiki/Json</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Archetype extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String artifactId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String blobUrl;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Category> categories;

  static {
    // hack to force ProGuard to consider Category used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(Category.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String categoriesSearch;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key @com.google.api.client.json.JsonString
  private java.lang.Long dateCreated;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String description;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String groupId;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String key;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String name;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String repository;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String systemUserKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Tag> tags;

  static {
    // hack to force ProGuard to consider Tag used, since otherwise it would be stripped out
    // see http://code.google.com/p/google-api-java-client/issues/detail?id=528
    com.google.api.client.util.Data.nullOf(Tag.class);
  }

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String tagsSearch;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String version;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getArtifactId() {
    return artifactId;
  }

  /**
   * @param artifactId artifactId or {@code null} for none
   */
  public Archetype setArtifactId(java.lang.String artifactId) {
    this.artifactId = artifactId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getBlobUrl() {
    return blobUrl;
  }

  /**
   * @param blobUrl blobUrl or {@code null} for none
   */
  public Archetype setBlobUrl(java.lang.String blobUrl) {
    this.blobUrl = blobUrl;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Category> getCategories() {
    return categories;
  }

  /**
   * @param categories categories or {@code null} for none
   */
  public Archetype setCategories(java.util.List<Category> categories) {
    this.categories = categories;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getCategoriesSearch() {
    return categoriesSearch;
  }

  /**
   * @param categoriesSearch categoriesSearch or {@code null} for none
   */
  public Archetype setCategoriesSearch(java.lang.String categoriesSearch) {
    this.categoriesSearch = categoriesSearch;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Long getDateCreated() {
    return dateCreated;
  }

  /**
   * @param dateCreated dateCreated or {@code null} for none
   */
  public Archetype setDateCreated(java.lang.Long dateCreated) {
    this.dateCreated = dateCreated;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getDescription() {
    return description;
  }

  /**
   * @param description description or {@code null} for none
   */
  public Archetype setDescription(java.lang.String description) {
    this.description = description;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getGroupId() {
    return groupId;
  }

  /**
   * @param groupId groupId or {@code null} for none
   */
  public Archetype setGroupId(java.lang.String groupId) {
    this.groupId = groupId;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getKey() {
    return key;
  }

  /**
   * @param key key or {@code null} for none
   */
  public Archetype setKey(java.lang.String key) {
    this.key = key;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getName() {
    return name;
  }

  /**
   * @param name name or {@code null} for none
   */
  public Archetype setName(java.lang.String name) {
    this.name = name;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getRepository() {
    return repository;
  }

  /**
   * @param repository repository or {@code null} for none
   */
  public Archetype setRepository(java.lang.String repository) {
    this.repository = repository;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSystemUserKey() {
    return systemUserKey;
  }

  /**
   * @param systemUserKey systemUserKey or {@code null} for none
   */
  public Archetype setSystemUserKey(java.lang.String systemUserKey) {
    this.systemUserKey = systemUserKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Tag> getTags() {
    return tags;
  }

  /**
   * @param tags tags or {@code null} for none
   */
  public Archetype setTags(java.util.List<Tag> tags) {
    this.tags = tags;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTagsSearch() {
    return tagsSearch;
  }

  /**
   * @param tagsSearch tagsSearch or {@code null} for none
   */
  public Archetype setTagsSearch(java.lang.String tagsSearch) {
    this.tagsSearch = tagsSearch;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getVersion() {
    return version;
  }

  /**
   * @param version version or {@code null} for none
   */
  public Archetype setVersion(java.lang.String version) {
    this.version = version;
    return this;
  }

  @Override
  public Archetype set(String fieldName, Object value) {
    return (Archetype) super.set(fieldName, value);
  }

  @Override
  public Archetype clone() {
    return (Archetype) super.clone();
  }

}

package com.noodb.blog.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (ArticleImages)实体类
 *
 * @author noodzhan
 * @since 2021-10-19 21:37:50
 */
public class ArticleImages implements Serializable {
  private static final long serialVersionUID = -54618593062264931L;

  private Long id;

  private Long articleId;

  private String imageName;

  private String imageType;

  private byte[] base64Url;

  private String systemUrl;
  /** 创建人 */
  private Long createBy;
  /** 创建时间 */
  private Date createTime;
  /** 修改人 */
  private Long updateBy;
  /** 修改时间 */
  private Date updateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getArticleId() {
    return articleId;
  }

  public void setArticleId(Long articleId) {
    this.articleId = articleId;
  }

  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }

  public String getImageType() {
    return imageType;
  }

  public void setImageType(String imageType) {
    this.imageType = imageType;
  }

  public byte[] getBase64Url() {
    return base64Url;
  }

  public void setBase64Url(byte[] base64Url) {
    this.base64Url = base64Url;
  }

  public String getSystemUrl() {
    return systemUrl;
  }

  public void setSystemUrl(String systemUrl) {
    this.systemUrl = systemUrl;
  }

  public Long getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Long createBy) {
    this.createBy = createBy;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Long getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(Long updateBy) {
    this.updateBy = updateBy;
  }

  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
}

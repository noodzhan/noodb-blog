package com.noodb.blog.vo;

import com.noodb.blog.entity.ArticleImages;

/**
 * TODO
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/10/22 9:37 下午
 */
public class UploadImageVO extends ArticleImages {
  private String frontUrl;

  public String getFrontUrl() {
    return frontUrl;
  }

  public void setFrontUrl(String frontUrl) {
    this.frontUrl = frontUrl;
  }
}

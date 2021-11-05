package com.noodb.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.noodb.blog.entity.Article;

/**
 * 文章表(Article)表服务接口
 *
 * @author noodzhan
 * @since 2021-08-14 20:16:49
 */
public interface ArticleService extends IService<Article> {

  /**
   * 提取Summary
   *
   * @param content
   * @return
   */
  String extractSummaryHandle(String content);
}

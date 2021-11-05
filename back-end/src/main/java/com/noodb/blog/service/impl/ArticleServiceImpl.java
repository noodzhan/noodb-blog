package com.noodb.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noodb.blog.entity.Article;
import com.noodb.blog.mapper.ArticleMapper;
import com.noodb.blog.service.ArticleService;
import org.springframework.stereotype.Service;

/**
 * 文章表(Article)表服务实现类
 *
 * @author noodzhan
 * @since 2021-08-14 20:16:49
 */
@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {
  @Override
  public String extractSummaryHandle(String content) {
    return content.substring(0, Math.min(content.length(), 250)).replace('#', ' ').replace('*', ' ')
        + "...";
  }
}

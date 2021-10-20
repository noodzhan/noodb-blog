package com.noodb.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.noodb.blog.entity.Article;
import com.noodb.blog.entity.ArticleImages;
import com.noodb.blog.service.ArticleImagesService;
import com.noodb.blog.service.ArticleService;
import com.noodb.blog.util.FileUtils;
import com.noodb.blog.util.R;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 博客的增删改查接口
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/8/18 11:00 下午
 */
@RestController
@RequestMapping("/article")
public class BlogController {
  @Autowired private ArticleService articleService;
  @Autowired private ArticleImagesService articleImagesService;

  @Value("${article.imagePath}")
  private String filePath;

  @GetMapping("/all")
  public R<Page> findAllArticleByPage(
      @RequestParam("pageNum") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize) {
    Page<Article> articlePage = new Page<>(pageNum, pageSize);
    articleService
        .lambdaQuery()
        .select(Article::getId, Article::getTitle, Article::getSummary)
        .page(articlePage);
    return R.success(articlePage);
  }

  @GetMapping("/one")
  public R<Article> findArticleById(@RequestParam("id") Long id) {
    return R.success(articleService.lambdaQuery().eq(Article::getId, id).one());
  }

  @PostMapping("/edit")
  public R<Boolean> editOrInsertArticle(@RequestBody Article article) {
    article.setSummary(
        article
                .getContent()
                .substring(0, Math.min(article.getContent().length(), 250))
                .replace('#', ' ')
                .replace('*', ' ')
            + "...");
    boolean save = articleService.saveOrUpdate(article);
    return R.success(save);
  }

  @PostMapping("/img")
  public R<Boolean> uploadImage(
      @RequestPart("file") MultipartFile file,
      @RequestPart("articleImages") ArticleImages articleImages) {

    ArticleImages result = new ArticleImages();
    BeanUtils.copyProperties(articleImages, result);
    result.setImageName(file.getName());
    result.setImageType(file.getContentType());
    String absoluteFileName = FileUtils.generateFileByMultipartFile(file, filePath);
    result.setSystemUrl(absoluteFileName);
    return null;
  }
}

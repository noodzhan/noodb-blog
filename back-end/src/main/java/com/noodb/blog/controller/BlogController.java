package com.noodb.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.noodb.blog.constant.NoodbConstant;
import com.noodb.blog.entity.Article;
import com.noodb.blog.entity.ArticleImages;
import com.noodb.blog.entity.LuQueryParam;
import com.noodb.blog.lucene.LuceneManager;
import com.noodb.blog.service.ArticleImagesService;
import com.noodb.blog.service.ArticleService;
import com.noodb.blog.util.FileUtils;
import com.noodb.blog.util.R;
import com.noodb.blog.vo.UploadImageVO;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
  @Autowired
  private LuceneManager luceneManager;

  @Value("${article.imagePath}")
  private String filePath;

  @GetMapping("/all")
  public R<Page> findAllArticleByPage(
      @RequestParam("pageNum") Integer pageNum,
      @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize,
      @RequestParam(value = "searchValue", required = false) String searchValue) {
    Page<Article> articlePage = new Page<>(pageNum, pageSize);
    articleService
        .lambdaQuery()
        .like(
            Strings.isNotBlank(searchValue),
            Article::getTitle,
            NoodbConstant.percentChar + searchValue + NoodbConstant.percentChar)
        .select(Article::getId, Article::getTitle, Article::getSummary)
        .page(articlePage);
    return R.success(articlePage);
  }

  @GetMapping("/one")
  public R<Article> findArticleById(@RequestParam("id") Long id) {
    return R.success(articleService.lambdaQuery().eq(Article::getId, id).one());
  }

  @PostMapping("/edit")
  public R<String> editOrInsertArticle(@RequestBody @Validated Article article) {
    article.setSummary(articleService.extractSummaryHandle(article.getContent()));
    articleService.saveOrUpdate(article);
    luceneManager.save(article);
    return R.success(article.getId().toString());
  }

  @PostMapping("/delete")
  public R<Boolean> deleteArticleByIds(@RequestBody List<String> ids) {
    boolean b = articleService.removeByIds(ids);
    luceneManager.deleteByIds(ids);
    return R.success(b);
  }

    @PostMapping("/search")
    public R<List<Article>> search(@RequestBody LuQueryParam luQueryParam) {
        return R.success(luceneManager.search(luQueryParam));
    }

  @PostMapping("/createIndex")
  public R<Boolean> createIndex() {
    luceneManager.save(articleService.list());
    return R.success(true);
  }


  @PostMapping("/img")
  public R<UploadImageVO> uploadImage(
      @RequestPart("file") MultipartFile file,
      @RequestPart("articleImages") ArticleImages articleImages) {
    UploadImageVO result = new UploadImageVO();
    BeanUtils.copyProperties(articleImages, result);
    String absoluteFileName = FileUtils.generateFileByMultipartFile(file, filePath);
    result.setImageName(FileUtils.getFileNameFromAbsolutePath(absoluteFileName));
    result.setImageType(file.getContentType());
    result.setSystemUrl(absoluteFileName);
    result.setFrontUrl(
        NoodbConstant.resourceUrlPrefix + FileUtils.getFileNameFromAbsolutePath(absoluteFileName));
    articleImagesService.save(result);
    return R.success(result);
  }
}

package com.noodb.blog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.noodb.blog.entity.Article;
import com.noodb.blog.service.ArticleService;
import com.noodb.blog.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 博客的增删改查接口
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/8/18 11:00 下午
 */
@RestController
@RequestMapping("/article")
public class BlogController {
    @Autowired
    ArticleService articleService;

    @GetMapping("/all")
    public R<Page> findAllArticleByPage(@RequestParam("pageNum") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "15") Integer pageSize) {
        Page<Article> articlePage = new Page<>(pageNum, pageSize);
        articleService.lambdaQuery().select(Article::getId,Article::getTitle,Article::getSummary).page(articlePage);
        return R.success(articlePage);
    }

    @GetMapping("/one")
    public R<Article> findArticleById(@RequestParam("id") Long id){
        return R.success(articleService.lambdaQuery().eq(Article::getId, id).one());
    }


}

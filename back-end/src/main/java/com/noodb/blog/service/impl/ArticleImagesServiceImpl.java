package com.noodb.blog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.noodb.blog.entity.ArticleImages;
import com.noodb.blog.mapper.ArticleImagesMapper;
import com.noodb.blog.service.ArticleImagesService;
import org.springframework.stereotype.Service;

/**
 * (ArticleImages)表服务实现类
 *
 * @author noodzhan
 * @since 2021-10-19 21:37:53
 */
@Service("articleImagesService")
public class ArticleImagesServiceImpl extends ServiceImpl<ArticleImagesMapper, ArticleImages>
    implements ArticleImagesService {}

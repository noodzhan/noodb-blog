package com.noodb.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 文章表(Article)实体类
 *
 * @author noodzhan
 * @since 2021-08-14 20:11:10
 */
@Data
public class Article extends CommonField implements Serializable {
    private static final long serialVersionUID = 907731445964072540L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 分类id
    */
    private Long categoryId;
    /**
    * 标题
    */
    private String title;
    /**
    * 概要
    */
    private String summary;
    /**
    * 内容
    */
    private String content;
}

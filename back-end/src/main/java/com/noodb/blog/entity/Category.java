package com.noodb.blog.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 类别表(Category)实体类
 *
 * @author noodzhan
 * @since 2021-08-14 20:11:22
 */
@Data
public class Category extends CommonField implements Serializable {
    private static final long serialVersionUID = -99741556124636417L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 类别名字
    */
    private String name;
    /**
    * 父类别id
    */
    private Long pid;
    /**
    * 描述
    */
    private String description;


}

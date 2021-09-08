package com.noodb.blog.entity;

import lombok.Data;

import java.util.Date;

/**
 * 数据库公共字段
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/8/14 8:47 下午
 */
@Data
public class CommonField {
    /**
     * 创建人
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改人
     */
    private Long updateBy;
    /**
     * 修改时间
     */
    private Date updateTime;
}

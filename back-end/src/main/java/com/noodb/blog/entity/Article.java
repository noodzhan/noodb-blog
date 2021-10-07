package com.noodb.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * 文章表(Article)实体类
 *
 * @author noodzhan
 * @since 2021-08-14 20:11:10
 */
public class Article extends CommonField implements Serializable {
    private static final long serialVersionUID = 907731445964072540L;
    /**
    * 主键
    */
    @TableId
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

    public Article() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Article)) return false;
        final Article other = (Article) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$categoryId = this.getCategoryId();
        final Object other$categoryId = other.getCategoryId();
        if (this$categoryId == null ? other$categoryId != null : !this$categoryId.equals(other$categoryId))
            return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$summary = this.getSummary();
        final Object other$summary = other.getSummary();
        if (this$summary == null ? other$summary != null : !this$summary.equals(other$summary)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Article;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $categoryId = this.getCategoryId();
        result = result * PRIME + ($categoryId == null ? 43 : $categoryId.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $summary = this.getSummary();
        result = result * PRIME + ($summary == null ? 43 : $summary.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        return result;
    }

    public String toString() {
        return "Article(id=" + this.getId() + ", categoryId=" + this.getCategoryId() + ", title=" + this.getTitle() + ", summary=" + this.getSummary() + ", content=" + this.getContent() + ")";
    }
}

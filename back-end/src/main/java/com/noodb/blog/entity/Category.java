package com.noodb.blog.entity;

import java.io.Serializable;

/**
 * 类别表(Category)实体类
 *
 * @author noodzhan
 * @since 2021-08-14 20:11:22
 */
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

    public Category() {
    }


    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Long getPid() {
        return this.pid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Category)) return false;
        final Category other = (Category) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$pid = this.getPid();
        final Object other$pid = other.getPid();
        if (this$pid == null ? other$pid != null : !this$pid.equals(other$pid)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Category;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $pid = this.getPid();
        result = result * PRIME + ($pid == null ? 43 : $pid.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        return result;
    }

    public String toString() {
        return "Category(id=" + this.getId() + ", name=" + this.getName() + ", pid=" + this.getPid() + ", description=" + this.getDescription() + ")";
    }
}

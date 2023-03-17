package com.noodb.blog.entity;

public class LuQueryParam {
    //title:中国^2 OR content:中国
    private String keyword;

    public LuQueryParam() {
    }

    public LuQueryParam(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}

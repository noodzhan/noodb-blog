package com.noodb.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 博客系统后台
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/8/8 5:24 下午
 */
@SpringBootApplication
@MapperScan("com.noodb.blog.mapper")
public class BlogApplicationStart {
    public static void main(String[] args) {
        SpringApplication.run(BlogApplicationStart.class,args);

    }
}

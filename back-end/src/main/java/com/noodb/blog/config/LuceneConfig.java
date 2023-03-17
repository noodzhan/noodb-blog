package com.noodb.blog.config;

import com.noodb.blog.lucene.LuceneManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LuceneConfig {

    @Bean
    public LuceneManager luceneManager(){
        return new LuceneManager();
    }
}

package com.noodb.blog.lucene;

import com.noodb.blog.entity.Article;
import com.noodb.blog.entity.LuQueryParam;
import org.apache.lucene.document.Document;

import java.util.List;


public interface LuceneService {

    void save(Article article);

    void save(List<Article> articles);

    Document queryByDocId(int docId);

    List<Article> search(LuQueryParam query);

}

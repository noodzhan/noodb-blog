package com.noodb.blog.lucene;

import com.noodb.blog.entity.Article;
import org.apache.lucene.document.Document;


public interface LuceneService {

    void save(Article article);

    Document queryByDocId(int docId);

}

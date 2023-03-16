package com.noodb.blog.lucene;

import com.noodb.blog.entity.Article;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.TopDocs;
import org.junit.jupiter.api.Test;


public class LuceneManagerTest {

    @Test
    public void saveTest() {
        LuceneManager luceneManager = new LuceneManager();
        Article article = new Article();
        article.setTitle("ceshi nihao woshi nibab");
        article.setContent("中国你好ceshiceshi");
        luceneManager.save(article);

        Article article1 = new Article();
        article1.setTitle("ceshi nihao2222");
        article1.setContent("中国你好ceshiceshi");
        luceneManager.save(article1);


    }

    @Test
    public void queryTest() throws ParseException {
        LuceneManager luceneManager = new LuceneManager();
        QueryParser queryParser = new QueryParser("title", new StandardAnalyzer());
        TopDocs topDocs = luceneManager.query(queryParser.parse("ceshi"), 100);
        System.out.println(topDocs.totalHits.value);
    }


}

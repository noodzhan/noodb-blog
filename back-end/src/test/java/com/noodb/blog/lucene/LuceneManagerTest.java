package com.noodb.blog.lucene;

import com.noodb.blog.entity.Article;
import java.io.IOException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.junit.jupiter.api.Test;

public class LuceneManagerTest {

    @Test
    public void saveTest() {
        LuceneManager luceneManager = new LuceneManager();
        Article article = new Article();
        article.setTitle("title");
        article.setContent("中国");
        luceneManager.save(article);


    }

    @Test
    public void queryTest() throws ParseException, IOException {

        LuceneManager luceneManager = new LuceneManager();

        //standardAnalyzer
        String[] searchField = {"title", "content"};
        QueryParser queryParser = new MultiFieldQueryParser(searchField, new StandardAnalyzer());
//        QueryParser queryParser = new QueryParser("title", new StandardAnalyzer());
        TopDocs topDocs = luceneManager.query(queryParser.parse("title:中国^2 OR content:中国"), 100);
        System.out.println(topDocs.totalHits.value);
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
        IndexSearcher indexSearch = luceneManager.getIndexSearch();
        this.show(topDocs, standardAnalyzer, indexSearch);
        System.out.println("----------------------------");
        //cjkAnalyzer
        CJKAnalyzer cjkAnalyzer = new CJKAnalyzer();
        QueryParser queryParser1 = new QueryParser("title", cjkAnalyzer);
        Query query1 = queryParser1.parse("中国");
        TopDocs search = indexSearch.search(query1, 100);
        this.show(search, cjkAnalyzer, indexSearch);

    }

    @Test
    public void highlightQuery() throws ParseException, IOException, InvalidTokenOffsetsException {
        String[] searchField = {"title", "content"};
        QueryParser queryParser = new MultiFieldQueryParser(searchField, new StandardAnalyzer());
        Query query = queryParser.parse("title:中国^2 OR content:中国");

        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter();
        DefaultEncoder encoder = new DefaultEncoder();
        QueryScorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, encoder, scorer);

        LuceneManager luceneManager = new LuceneManager();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexSearcher indexSearch = luceneManager.getIndexSearch();

        TopDocs topDocs = luceneManager.query(query, 100);

        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            Document doc = indexSearch.doc(scoreDoc.doc);
            TokenStream ts = analyzer.tokenStream("title", doc.getField("title").stringValue());
//            ts.reset();
//            CharTermAttribute attribute = ts.getAttribute(CharTermAttribute.class);
//            while (ts.incrementToken()) {
//                System.out.print(attribute + "|");
//            }
//            System.out.println();
            System.out.println("title: "+highlighter.getBestFragment(ts,doc.getField("title").stringValue()));
            System.out.println("content:"+doc.getField("content").stringValue());
            ts.close();
        }
    }


    private void show(TopDocs topDocs, Analyzer analyzer, IndexSearcher indexSearcher) throws IOException {
        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            TokenStream ts = analyzer.tokenStream("", indexSearcher.doc(scoreDoc.doc).getField("title").stringValue());
            ts.reset();
            CharTermAttribute attribute = ts.getAttribute(CharTermAttribute.class);
            while (ts.incrementToken()) {
                System.out.print(attribute + "|");
            }
            System.out.println();
            ts.close();
        }
    }


}

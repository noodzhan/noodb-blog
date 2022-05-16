package com.noodb.blog.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Path;

/**
 * luceneIndexCreator
 *
 * @author <a href="noodzhan@163.com">noodzhan</a>
 * @since 2021/12/11 9:00 下午
 */
public class LuceneIndexCreator {
  public static void main(String[] args) throws IOException, ParseException {
    //
    Directory directory = null;
    try {
      directory = FSDirectory.open(Path.of("/Users/noodzhan/IdeaProjects/noodb/temp"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    Analyzer standardAnalyzer = new StandardAnalyzer();
    IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);

    IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

    for (int i = 0; i < 10; i++) {
      Document document = new Document();
      document.add(new TextField("content", "mybatis是持久层框架" + i, Field.Store.YES));
      document.add(
          new StringField(
              "title", "mybatis is simple framework about database" + i, Field.Store.YES));
      indexWriter.addDocument(document);
    }
    indexWriter.commit();
    indexWriter.close();
    directory.close();

    directory = FSDirectory.open(Path.of("/Users/noodzhan/IdeaProjects/noodb/temp"));
    IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(directory));

    System.out.println("----------------");
    System.out.println(indexSearcher.doc(1).get("title"));
    System.out.println(indexSearcher.doc(1).get("content"));
    System.out.println("----------------");

    QueryParser queryParser = new QueryParser("title", standardAnalyzer);
    Query query = queryParser.parse("mybatis1");

    int n = 10;
    TopDocs search = indexSearcher.search(query, 10);

    if (search.totalHits.equals(new TotalHits(0l, TotalHits.Relation.EQUAL_TO))) {
      System.out.println("没有命中任何数据");
    }

    for (ScoreDoc scoreDoc : search.scoreDocs) {
      //
      System.out.println(scoreDoc.doc);

      Document doc = indexSearcher.doc(scoreDoc.doc);

      System.out.println(doc.get("title"));
      System.out.println(doc.get("content"));
    }
  }
}

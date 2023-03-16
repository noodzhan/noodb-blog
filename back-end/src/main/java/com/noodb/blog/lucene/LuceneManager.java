package com.noodb.blog.lucene;

import com.noodb.blog.entity.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class LuceneManager implements LuceneService {

    private String indexFileDir = System.getProperty("user.dir") + File.separator +"lucene_index";

    @Override
    public void save(Article article) {
        IndexWriter indexWriter = getIndexWriter();
        Document document = new Document();
        document.add(new TextField("content", article.getContent(), Field.Store.YES));
        document.add(new StringField("title", article.getTitle(), Field.Store.YES));
        try {
            indexWriter.addDocument(document);
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Document queryByDocId(int docId) {

        try {
            IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Path.of(indexFileDir))));
            return indexSearcher.doc(docId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TopDocs query(Query query, int topN) {

        try {
            IndexSearcher indexSearcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(Path.of(indexFileDir))));
            return indexSearcher.search(query, topN);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public IndexWriter getIndexWriter() {
        //
        try {
            Directory directory = null;
            directory = FSDirectory.open(Path.of(indexFileDir));
            Analyzer standardAnalyzer = new StandardAnalyzer();
            IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
            return new IndexWriter(directory, indexWriterConfig);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.noodb.blog.lucene;

import com.noodb.blog.entity.Article;
import com.noodb.blog.entity.LuQueryParam;
import org.apache.logging.log4j.util.Strings;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class LuceneManager implements LuceneService {

    public String indexFileDir = System.getProperty("user.dir") + File.separator + "lucene_index";

    public DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MMMM-dd HH:mm:ss");

    private static Logger logger = LoggerFactory.getLogger(LuceneManager.class);

    @Override
    public void save(Article article) {
        this.save(Arrays.asList(article));
    }

    @Override
    public void save(List<Article> articles) {
        IndexWriter indexWriter = getIndexWriter();
        for (Article article : articles) {
            Document document = new Document();
            document.add(new TextField("content", article.getContent(), Field.Store.YES));
            document.add(new TextField("summary", article.getSummary(), Field.Store.YES));
            document.add(new TextField("title", article.getTitle(), Field.Store.YES));
            document.add(new StringField("id", String.valueOf(article.getId()), Field.Store.YES));

            if (Objects.nonNull(article.getCreateBy())) {
                document.add(new StringField("createBy", String.valueOf(article.getCreateBy()), Field.Store.YES));
            }
            if (Objects.nonNull(article.getUpdateBy())) {
                document.add(new StringField("updateBy", String.valueOf(article.getUpdateBy()), Field.Store.YES));
            }
            if (Objects.nonNull(article.getCreateTime())) {
                document.add(new TextField("createTime", dateTimeFormatter.format(article.getCreateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()), Field.Store.YES));
            }
            if (Objects.nonNull(article.getCreateTime())) {
                document.add(new TextField("updateTime", dateTimeFormatter.format(article.getUpdateTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()), Field.Store.YES));
            }
            try {
                indexWriter.addDocument(document);
                indexWriter.commit();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            indexWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> search(LuQueryParam queryParam) {
        List<Article> result = new LinkedList<>();
        String[] searchField = {"title", "content"};
        QueryParser queryParser = new MultiFieldQueryParser(searchField, new StandardAnalyzer());
        Query query = null;
        try {
            query = queryParser.parse(queryParam.getKeyword());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SimpleHTMLFormatter formatter = new SimpleHTMLFormatter();
        DefaultEncoder encoder = new DefaultEncoder();
        QueryScorer scorer = new QueryScorer(query);
        Highlighter highlighter = new Highlighter(formatter, encoder, scorer);
        highlighter.setTextFragmenter(new SimpleFragmenter(3650000));

        LuceneManager luceneManager = new LuceneManager();
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexSearcher indexSearch = null;
        try {
            indexSearch = luceneManager.getIndexSearch();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TopDocs topDocs = luceneManager.query(query, 1000);

        for (int i = 0; i < topDocs.scoreDocs.length; i++) {
            ScoreDoc scoreDoc = topDocs.scoreDocs[i];
            Document doc = null;
            Article article;
            try {
                doc = indexSearch.doc(scoreDoc.doc);
                article = new Article();
                String hlTitle = highlighter.getBestFragment(analyzer, "title", doc.getField("title").stringValue());
                if (Strings.isBlank(hlTitle)) {
                    article.setTitle(doc.get("title"));
                } else {
                    article.setTitle(hlTitle);
                }
                String hlSummary = highlighter.getBestFragment(analyzer, "summary", doc.getField("summary").stringValue());
                if (Strings.isBlank(hlSummary)) {
                    article.setSummary(doc.get("summary"));
                } else {
                    article.setSummary(hlSummary);
                }
                if (Objects.nonNull(doc.getField("id"))) {
                    article.setId(Long.valueOf(doc.getField("id").stringValue()));
                }
                if (Objects.nonNull(doc.getField("createBy"))) {
                    article.setCreateBy(Long.valueOf(doc.getField("createBy").stringValue()));
                }
                if (Objects.nonNull(doc.getField("UpdateBy"))) {
                    article.setUpdateBy(Long.valueOf(doc.getField("UpdateBy").stringValue()));
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InvalidTokenOffsetsException e) {
                throw new RuntimeException(e);
            }
            result.add(article);
        }

        return result;
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


    public IndexSearcher getIndexSearch() throws IOException {
        return new IndexSearcher(DirectoryReader.open(FSDirectory.open(Path.of(indexFileDir))));
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

package com.endava.wiki.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long articleId;

    @Column(name = "article_name")
    private String articleName;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<ArticleTopWords> wordsContorList;

    public Article(String articleName) {
        super();
        this.articleName = articleName;
    }

    public Article() {
        super();
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                '}';
    }
}

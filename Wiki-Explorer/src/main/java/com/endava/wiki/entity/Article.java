package com.endava.wiki.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long articleId;

    @Column(name = "article_name")
    private String articleName;

    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<ArticleTopWords> wordsContorList;

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

    public Set<ArticleTopWords> getWordsContorList() {
        return wordsContorList;
    }

    public void setWordsContorList(Set<ArticleTopWords> wordsContorList) {
        this.wordsContorList = wordsContorList;
    }

    public void addItemToWordsContorList(ArticleTopWords articleTopWords) {
        this.wordsContorList.add(articleTopWords);
    }

    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", articleName='" + articleName + '\'' +
                '}';
    }
}

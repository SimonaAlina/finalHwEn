package com.endava.wiki.entity;

import javax.persistence.*;

/**
 * Created by sroboiu on 12-Aug-16.
 */
@Entity
@Table(name = "article_top_words")
public class ArticleTopWords {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "word")
    private String word;
    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    public ArticleTopWords(String word, Integer count, Article article) {
        super();
        this.word = word;
        this.count = count;
        this.article = article;
    }

    public ArticleTopWords() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ArticleTopWords{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", count=" + count +
                ", articleOwner=" + article.getArticleName() +
                '}';
    }
}

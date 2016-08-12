package com.endava.wiki.entity;

import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "article_name")
    private String articleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", articleName='" + articleName + '\'' +
                '}';
    }
}

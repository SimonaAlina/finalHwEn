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
    @Column(name = "art_id")
    private long artId;

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

    public long getArtId() {
        return artId;
    }

    public void setArtId(long artId) {
        this.artId = artId;
    }

    @Override
    public String toString() {
        return "ArticleTopWords{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", count=" + count +
                ", artId=" + artId +
                '}';
    }
}

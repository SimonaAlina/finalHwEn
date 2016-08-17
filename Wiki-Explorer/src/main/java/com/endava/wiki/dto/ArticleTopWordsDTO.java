package com.endava.wiki.dto;

/**
 * Created by sroboiu on 16-Aug-16.
 */
public class ArticleTopWordsDTO {

    private Long id;
    private String word;
    private Integer count;
    private Long artId;

    public ArticleTopWordsDTO() {
    }

    public ArticleTopWordsDTO(String word, Integer count, Long artId) {
        this.word = word;
        this.count = count;
        this.artId = artId;
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

    public Long getArtId() {
        return artId;
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    @Override
    public String toString() {
        return "ArticleTopWordsDTO{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", count=" + count +
                ", artId=" + artId +
                '}';
    }
}

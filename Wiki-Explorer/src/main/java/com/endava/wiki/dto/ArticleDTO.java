package com.endava.wiki.dto;

import com.endava.wiki.entity.Article;
import com.endava.wiki.entity.ArticleTopWords;

import javax.validation.constraints.NotNull;
import java.util.Hashtable;
import java.util.List;

/**
 * Created on 11-Aug-16.
 */
public class ArticleDTO {

    @NotNull
    private Long articleId;
    @NotNull
    private String articleName;

    private Hashtable<String, Integer> wordCount;
    
    public Hashtable<String, Integer> getWordCount() {
        return wordCount;
    }

    public void setWordCount(Hashtable<String, Integer> wordCount) {
        this.wordCount = wordCount;
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
}

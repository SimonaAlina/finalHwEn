package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.WikiArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by sroboiu on 19-Aug-16.
 */
public class MultiThreadsWikiService extends Thread {

    @Autowired
    ArticleService articleService;
    @Autowired
    WikiArticleService wikiArticleService;

    List<String> titles;
    Hashtable<String, Integer> result;

    public MultiThreadsWikiService(List<String> titles) {
        this.titles = titles;
        result = new Hashtable<>();
    }

    @Override
    public void run() {

        for (String title : this.titles) {
            title = title.replaceAll("\\s+", "+");
            ArticleDTO articleDTO = articleService.getArticleByName(title);

            if (articleDTO == null) {
                System.out.println("Get result from Wikipedia for title name: " + title);

                articleDTO = new ArticleDTO();
                articleDTO.setArticleName(title);

                Hashtable<String, Integer> wordsCount = wikiArticleService.parseContentResultFromWiki(title);

                if (wordsCount != null) {
                    articleDTO.setWordCount(wordsCount);
                    articleDTO = articleService.saveArticle(articleDTO);
                }

            } else {
                System.out.println("Get result from Databasefor title name: " + title);
            }
            result.putAll(articleDTO.getWordCount());
        }
    }
}

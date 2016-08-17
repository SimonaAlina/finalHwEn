package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.dto.ArticleTopWordsDTO;
import com.endava.wiki.parser.FileParser;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.ArticleTopWordsService;
import com.endava.wiki.service.WikiArticleService;
import com.endava.wiki.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sroboiu on 16-Aug-16.
 */
@Service
public class WikiServiceImpl implements WikiService {

    @Autowired
    ArticleService articleService;
    @Autowired
    WikiArticleService wikiArticleService;
    @Autowired
    ArticleTopWordsService articleTopWordsService;
    @Autowired
    FileParser fileParser;

    @Override
    @Transactional
    public Hashtable<String, Integer> getSimpleResult(String title) {

        System.out.println("WikiService: begining");

        ArticleDTO articleDTO = articleService.getArticleByName(title);

        if (articleDTO == null) {
            articleDTO = new ArticleDTO();
            articleDTO.setArticleName(title);
            articleDTO.setWordCount(new Hashtable<String, Integer>());
            articleDTO = articleService.saveArticle(articleDTO);

            System.out.println("WikiService: articleDto saved:" + articleDTO);

            ArticleTopWordsDTO articleTopWordsDTO = new ArticleTopWordsDTO();
            articleTopWordsDTO.setArtId(articleDTO.getArticleId());

            Hashtable<String, Integer> wordsCount = wikiArticleService.parseContentResultFromWiki(title);

            for (Map.Entry<String, Integer> entry : wordsCount.entrySet()) {
                articleTopWordsDTO.setWord(entry.getKey());
                articleTopWordsDTO.setCount(entry.getValue());
                articleTopWordsService.saveArticleTopWords(articleTopWordsDTO);
            }
            articleDTO.setWordCount(wordsCount);
            System.out.println("WikiService result: " + articleDTO.getWordCount());

        }
        return articleDTO.getWordCount();
    }

    @Override
    public Hashtable<String, Integer> getMultipleResult(String content) {

        Set<String> titles = fileParser.parseInputFile(content);
        if (titles == null || titles.isEmpty())
            return null;

        Hashtable<String, Integer> result = new Hashtable<String, Integer>();
        for (String title : titles) {

            for (final Map.Entry<String, Integer> entrySet : getSimpleResult(title).entrySet()) {
                result.computeIfPresent(entrySet.getKey(), (k, v) -> v + entrySet.getValue());
            }
        }
        return result;
    }
}

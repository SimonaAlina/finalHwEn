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

        ArticleDTO articleDTO = articleService.getArticleByName(title);

        if (articleDTO == null) {
            articleDTO = new ArticleDTO();
            articleDTO.setArticleName(title);

            Hashtable<String, Integer> wordsCount = wikiArticleService.parseContentResultFromWiki(title);

            if(wordsCount == null) {
                return null;
            } else {
                articleDTO.setWordCount(wordsCount);
            }
            articleDTO = articleService.saveArticle(articleDTO);
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

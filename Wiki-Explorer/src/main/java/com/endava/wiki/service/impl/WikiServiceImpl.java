package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.parser.FileParser;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.WikiArticleService;
import com.endava.wiki.service.WikiService;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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
    FileParser fileParser;

    @Override
    @Transactional
    public ArticleDTO getSimpleResult(String title) {

        title = WordUtils.capitalizeFully(title);
        title = title.replaceAll("\\s+", "_");
        ArticleDTO articleDTO = articleService.getArticleByName(title);

        if (articleDTO == null) {
            System.out.println("Get result from Wikipedia");
            articleDTO = new ArticleDTO();
            articleDTO.setArticleName(title);
            articleDTO.setSource(1);

            Hashtable<String, Integer> wordsCount = wikiArticleService.parseContentResultFromWiki(title);
            articleDTO.setWordCount(wordsCount);

            if (wordsCount == null || wordsCount.isEmpty()) {
                return articleDTO;
            }

            try {
                articleDTO = articleService.saveArticle(articleDTO);
                articleDTO.setSource(1);
            } catch (RuntimeException e) {
                System.out.println(e.getCause() + "for title:" + articleDTO.getArticleName());
            }
        } else {
            System.out.println("Get result from Database");
            articleDTO.setSource(0);
        }
        return articleDTO;
    }

    @Override
    public Hashtable<String, Integer> getMultipleResultWithoutThreads(String content) {

        Set<String> titles = fileParser.parseInputFile(content);
        if (titles == null || titles.isEmpty())
            return null;

        Hashtable<String, Integer> result = new Hashtable<String, Integer>();
        for (String title : titles) {
            Hashtable<String, Integer> hashResult = getSimpleResult(title).getWordCount();
            if (hashResult == null || hashResult.isEmpty())
                continue;
            for (Map.Entry<String, Integer> entrySet : hashResult.entrySet()) {
                if (result.containsKey(entrySet.getKey()))
                    result.computeIfPresent(entrySet.getKey(), (k, v) -> v + entrySet.getValue());
                else
                    result.put(entrySet.getKey(), entrySet.getValue());
            }
        }
        return result;
    }

}

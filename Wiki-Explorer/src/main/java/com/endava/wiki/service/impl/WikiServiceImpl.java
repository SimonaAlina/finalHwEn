package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.parser.FileParser;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.WikiArticleService;
import com.endava.wiki.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Hashtable;
import java.util.List;
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
    FileParser fileParser;

    @Override
    public Hashtable<String, Integer> getSimpleResult(String title) {

        ArticleDTO articleDTO = articleService.getArticleByName(title);
        if(articleDTO == null)
            articleDTO = wikiArticleService.getResultFromWiki(title);
        return articleDTO.getWordCount();
    }

    @Override
    public Hashtable<String , Integer> getMultipleResult(String content) {

        Set<String> titles = fileParser.parseInputFile(content);
        if(titles == null || titles.isEmpty())
            return null;
        Hashtable<String, Integer> result = new Hashtable<String, Integer>();
        for(String title : titles) {
            //todo: if exist? ++
            result.putAll(getSimpleResult(title));
        }
        return result;
    }
}

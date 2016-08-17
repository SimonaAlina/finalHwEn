package com.endava.wiki.service;

import com.endava.wiki.dto.ArticleDTO;

import java.util.Hashtable;

/**
 * Created by sroboiu on 16-Aug-16.
 */
public interface WikiArticleService {

    Hashtable<String, Integer> parseContentResultFromWiki(String title);

    String getContentFromWiki(String articleName);

}

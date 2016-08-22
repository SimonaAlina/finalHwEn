package com.endava.wiki.service;

import com.endava.wiki.dto.ArticleDTO;

import java.util.Hashtable;

/**
 * Created by sroboiu on 16-Aug-16.
 */
public interface WikiService {

    Hashtable<String, Integer> getSimpleResult(String title);

    Hashtable<String, Integer> getMultipleResultWithoutThreads(String content);
}

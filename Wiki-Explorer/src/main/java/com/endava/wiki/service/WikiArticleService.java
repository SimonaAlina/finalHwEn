package com.endava.wiki.service;

import java.util.Hashtable;

/**
 * Created by sroboiu on 16-Aug-16.
 */
public interface WikiArticleService {

    void getContentFromWeb(String articleName);
    Hashtable<String, Integer> parseContent(String content);
}

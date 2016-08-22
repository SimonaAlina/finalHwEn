package com.endava.wiki.service;

import java.util.Hashtable;

/**
 * Created by sroboiu on 19-Aug-16.
 */
public interface MultiThreadsWikiService {
    public void searchTitle(final String title);

    public Hashtable<String, Integer> getMultipleResults(String content);
}

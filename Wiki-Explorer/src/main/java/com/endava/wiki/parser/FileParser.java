package com.endava.wiki.parser;

import org.springframework.stereotype.Component;


import java.util.*;


@Component
public class FileParser {

    private String delimiters = "\r\n";

    public Set<String> parseInputFile(String content) {

        if(content == null || content.isEmpty())
            return null;

        Set<String> result = new HashSet<String>();
        StringTokenizer token = new StringTokenizer(content, delimiters);
        while (token.hasMoreTokens()) {
            String titleSearch = token.nextToken();
            titleSearch = titleSearch.replaceAll("\\s+", "_");
            result.add(titleSearch);
        }
        return result;
    }
}

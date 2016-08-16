package com.endava.wiki.parser;

import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.StringTokenizer;

@Component
public class ContentParser {

    private String delimiters = " ;:/?~\\\\.,><_~`[{()}]!@#$%^&-+'=*\\\"|\t\n";

    public Hashtable<String, Integer> parseContent(String content) {

        int freq = 0;
        Hashtable<String, Integer> wordsFreq = new Hashtable<String, Integer>();
        StringTokenizer token = new StringTokenizer(content, delimiters);
        while (token.hasMoreTokens()) {
            String word = token.nextToken().toLowerCase();
            if(word.matches("[0-9]+"))
                continue;
            if (wordsFreq.get(word) == null) {
                wordsFreq.put(word, 1);
            } else {
                freq = wordsFreq.get(word);
                wordsFreq.put(word, ++freq);
            }
        }
        return wordsFreq;
    }
}

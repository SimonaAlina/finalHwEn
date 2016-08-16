package com.endava.wiki.parser;

import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

@Component
public class ContentParser {


    private String delimiters = " ;:/?~\\\\.,><_~`[{()}]!@#$%^&-+'=*\\\"|\t\n";
    private static final Set<String> COMMON_WORDS = new LinkedHashSet<>();

    static {
        COMMON_WORDS.add("a");
        COMMON_WORDS.add("and");
        COMMON_WORDS.add("be");
        COMMON_WORDS.add("for");
        COMMON_WORDS.add("from");
        COMMON_WORDS.add("has");
        COMMON_WORDS.add("i");
        COMMON_WORDS.add("in");
        COMMON_WORDS.add("is");
        COMMON_WORDS.add("it");
        COMMON_WORDS.add("of");
        COMMON_WORDS.add("on");
        COMMON_WORDS.add("to");
        COMMON_WORDS.add("the");
    }

    public Hashtable<String, Integer> parseContent(String content) {

        int freq = 0;
        Hashtable<String, Integer> wordsFreq = new Hashtable<String, Integer>();
        StringTokenizer token = new StringTokenizer(content, delimiters);
        while (token.hasMoreTokens()) {
            String word = token.nextToken().toLowerCase();
            if (word.matches("[0-9]+")) {
                continue;
            }
            if (COMMON_WORDS.contains(word)) {
                continue;
            }
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

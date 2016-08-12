package com.endava.wiki.dto;

/**
 * Created by ienescu on 8/11/2016.
 */
public class WordCountDto {

    private String theWord;
    private int occurences;

    public WordCountDto(String theWord, int occurences) {
        this.theWord = theWord;
        this.occurences = occurences;
    }

    public String getTheWord() {
        return theWord;
    }

    public void setTheWord(String theWord) {
        this.theWord = theWord;
    }

    public int getOccurences() {
        return occurences;
    }

    public void setOccurences(int occurences) {
        this.occurences = occurences;
    }
}

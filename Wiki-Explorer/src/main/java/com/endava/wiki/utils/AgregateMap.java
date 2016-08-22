package com.endava.wiki.utils;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by sroboiu on 19-Aug-16.
 */
public class AgregateMap {

    private static Hashtable<String, Integer> result;

    public AgregateMap() {
        result = new Hashtable<>();
    }

    public Hashtable<String, Integer> getResult() {
        return result;
    }

    public synchronized Hashtable<String, Integer> addPartialResult(Hashtable<String, Integer> partialResult) {

        if (partialResult == null || partialResult.isEmpty())
            return result;
        for (Map.Entry<String, Integer> entrySet : partialResult.entrySet()) {
            if (result.containsKey(entrySet.getKey()))
                result.computeIfPresent(entrySet.getKey(), (k, v) -> v + entrySet.getValue());
            else
                result.put(entrySet.getKey(), entrySet.getValue());
        }
        return result;
    }
}

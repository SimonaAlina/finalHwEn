package com.endava.wiki.service.impl;

import com.endava.wiki.parser.ContentParser;
import com.endava.wiki.service.WikiArticleService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by sroboiu on 16-Aug-16.
 */
@Service
public class WikiArticleServiceImpl implements WikiArticleService {

    @Autowired
    ContentParser contentParser;

    @Override
    public Hashtable<String, Integer> parseContentResultFromWiki(String title) {

        String content = getContentFromWiki(title);
        if (content == null)
            return null;
        Hashtable<String, Integer> result = contentParser.parseContent(content);
        return result;
    }

    @Override
    public String getContentFromWiki(String articleName) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&titles=" + articleName.toLowerCase());
        HttpResponse response = null;

        String content = null;

        try {
            response = httpClient.execute(request);
            String jsonStr = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject pages = jsonObject.getJSONObject("query").getJSONObject("pages");
            JSONObject var = pages.getJSONObject(pages.keys().next());
            if (var.has("extract") == false)
                return "";
            content = (String) var.get("extract");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}

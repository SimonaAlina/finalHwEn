package com.endava.wiki.service.impl;

import com.endava.wiki.service.WebArticleService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by sroboiu on 16-Aug-16.
 */
@Service
public class WikiArticleServiceImpl {

    //@Override
    public void getContentFromWeb(String articleName) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&titles=" + articleName);
        HttpResponse response = null;

        try {
            response = httpClient.execute(request);
            String jsonStr = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject pages = jsonObject.getJSONObject("query").getJSONObject("pages");
            String content = (String) pages.getJSONObject(pages.keys().next()).get("extract");

            System.out.println("Response:\n" + content);
            // todo: class for parsing => hashtable => articleDto (articleName, hasttable)

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //@Override
    public Hashtable<String, Integer> parseContent(String content) {
        return null;
    }
}

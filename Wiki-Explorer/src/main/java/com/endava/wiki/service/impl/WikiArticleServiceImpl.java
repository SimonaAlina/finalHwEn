package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
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
    public ArticleDTO getResultFromWiki(String title) {

        ArticleDTO articleDTO = new ArticleDTO();
        String content = getContentFromWiki(title);
        Hashtable<String, Integer> result = contentParser.parseContent(content);
        articleDTO.setArticleName(title);
        articleDTO.setWordCount(result);
        return articleDTO;
    }

    @Override
    public String getContentFromWiki(String articleName) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost("https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&explaintext=&titles=" + articleName);
        HttpResponse response = null;

        String content = null;

        try {
            response = httpClient.execute(request);
            String jsonStr = EntityUtils.toString(response.getEntity());

            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject pages = jsonObject.getJSONObject("query").getJSONObject("pages");
            content = (String) pages.getJSONObject(pages.keys().next()).get("extract");
            //System.out.println("Response:\n" + content);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

}

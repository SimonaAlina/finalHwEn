package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.entity.Article;
import com.endava.wiki.mapping.MapEntityDTO;
import com.endava.wiki.repository.ArticleRepository;
import com.endava.wiki.service.ArticleService;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sroboiu on 11-Aug-16.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private MapEntityDTO mappingArticle;


    @Override
    @Transactional(readOnly = true)
    public ArticleDTO getArticleById(Long articleId) {
        Article article = articleRepository.findOne(articleId);
        // map entity to DTO
        return mappingArticle.mapEntityToDto(article);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDTO getArticleByName(String articleName) {
        Article article = articleRepository.findByArticleName(articleName);
        // map entity to DTO
        return mappingArticle.mapEntityToDto(article);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleDTO> getAllArticles() {

        List<ArticleDTO> articles = new ArrayList<ArticleDTO>();
        List<Article> articlesEntities = articleRepository.findAll();
        if (articlesEntities != null && !articlesEntities.isEmpty()) {
            for (Article article : articlesEntities) {
                /// map entity to DTO
                articles.add(mappingArticle.mapEntityToDto(article));
            }
        }
        return articles;
    }

    @Override
    @Transactional
    public ArticleDTO saveArticle(ArticleDTO articleDTO) {
        Article article = new MapEntityDTO().mapDtoToEntity(articleDTO);
        return new MapEntityDTO().mapEntityToDto(articleRepository.save(article));
    }

    public void getArticleFromWeb(String articleName) {
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
}

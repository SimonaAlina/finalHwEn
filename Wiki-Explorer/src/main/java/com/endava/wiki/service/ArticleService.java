package com.endava.wiki.service;

import com.endava.wiki.dto.ArticleDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by sroboiu on 11-Aug-16.
 */
public interface ArticleService {


    ArticleDTO getArticleById(Long articleId);
    ArticleDTO getArticleByName(String articleName);
    List<ArticleDTO> getAllArticles();
    ArticleDTO saveArticle(ArticleDTO articleDTO);
    void getArticleFromWeb(String articleName);

}

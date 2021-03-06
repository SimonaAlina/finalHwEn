package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.entity.Article;
import com.endava.wiki.mapping.MapEntityDTO;
import com.endava.wiki.repository.ArticleRepository;
import com.endava.wiki.service.ArticleService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


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
        if(article == null)
            return null;
        return mappingArticle.mapEntityToDto(article);
    }

    @Override
    @Transactional(readOnly = true)
    public ArticleDTO getArticleByName(String articleName) {

        Article article = articleRepository.findFirstByArticleName(articleName);
        // map entity to DTO
        if(article == null)
            return null;
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
        Article article = mappingArticle.mapDtoToEntity(articleDTO);
        return mappingArticle.mapEntityToDto(articleRepository.save(article));
    }
}

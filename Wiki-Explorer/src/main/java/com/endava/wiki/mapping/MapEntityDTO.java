package com.endava.wiki.mapping;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.entity.Article;
import com.endava.wiki.entity.ArticleTopWords;
import com.endava.wiki.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by sroboiu on 12-Aug-16.
 */
@Component
public class MapEntityDTO {

    @Autowired
    private ArticleRepository articleRepository;

    public Article mapDtoToEntity(ArticleDTO articleDTO) {

        Article article = articleRepository.findOne(articleDTO.getArticleId());
        if (article == null) {
            article = new Article();
            article.setArticleName(articleDTO.getArticleName());
            List<ArticleTopWords> words = new ArrayList<ArticleTopWords>();
            for(Map.Entry<String, Integer> entry : articleDTO.getWordCount().entrySet()) {
                words.add(new ArticleTopWords(entry.getKey(), entry.getValue(), article));
            }
            article.setWordsContorList(words);
        }
        return article;
    }

    public ArticleDTO mapEntityToDto(Article articleEntyty) {

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticleId(articleEntyty.getArticleId());
        articleDTO.setArticleName(articleEntyty.getArticleName());

        Hashtable<String, Integer> wordCount = new Hashtable<String, Integer>();
        for (ArticleTopWords article : articleEntyty.getWordsContorList()) {
            wordCount.put(article.getWord(), article.getCount());
        }
        articleDTO.setWordCount(wordCount);

        return articleDTO;
    }
}

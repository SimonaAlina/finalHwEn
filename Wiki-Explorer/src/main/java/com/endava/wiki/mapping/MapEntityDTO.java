package com.endava.wiki.mapping;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.entity.Article;
import com.endava.wiki.entity.ArticleTopWords;
import com.endava.wiki.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by sroboiu on 12-Aug-16.
 */
@Component
public class MapEntityDTO {

    @Autowired
    private ArticleRepository articleRepository;

    public Article mapDtoToEntity(ArticleDTO articleDTO) {

        Article article = articleRepository.findFirstByArticleName(articleDTO.getArticleName());
        if (article == null) {
            article = new Article();
            //article.setArticleId(articleDTO.getArticleId());
            article.setArticleName(articleDTO.getArticleName());
            if(articleDTO.getWordCount() == null || articleDTO.getWordCount().isEmpty())
                return article;
            Set<ArticleTopWords> words = new HashSet<>();
            for(Map.Entry<String, Integer> entry : articleDTO.getWordCount().entrySet()) {
                words.add(new ArticleTopWords(entry.getKey(), entry.getValue(), article));
            }
            article.setWordsContorList(words);
        }
        return article;
    }

    public ArticleDTO mapEntityToDto(Article articleEntity) {

        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setArticleId(articleEntity.getArticleId());
        articleDTO.setArticleName(articleEntity.getArticleName());

        Hashtable<String, Integer> wordCount = new Hashtable<String, Integer>();
        if(articleEntity.getWordsContorList() == null || articleEntity.getWordsContorList().isEmpty())
            return articleDTO;
        for (ArticleTopWords article : articleEntity.getWordsContorList()) {
            wordCount.put(article.getWord(), article.getCount());
        }
        articleDTO.setWordCount(wordCount);

        return articleDTO;
    }
}

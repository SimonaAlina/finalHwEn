package com.endava.wiki.service;

import com.endava.wiki.dto.ArticleTopWordsDTO;

/**
 * Created by sroboiu on 16-Aug-16.
 */
public interface ArticleTopWordsService {

    public ArticleTopWordsDTO getArticleTopWordsById(Long articleId);
    public ArticleTopWordsDTO getArticleTopWordsByArtId(Long artId);
    public ArticleTopWordsDTO saveArticleTopWords(ArticleTopWordsDTO articleTopWordsDTO);
}

package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleTopWordsDTO;
import com.endava.wiki.repository.ArticleTopWordsRepository;
import com.endava.wiki.service.ArticleTopWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sroboiu on 16-Aug-16.
 */
@Service
public class ArticleTopWordsServiceImpl implements ArticleTopWordsService {

    @Autowired
    private ArticleTopWordsRepository articleTopWordsRepository;

    @Override
    @Transactional(readOnly = true)
    public ArticleTopWordsDTO getArticleTopWordsById(Long articleId) {
        return null;
    }

    @Override
    public ArticleTopWordsDTO getArticleTopWordsByArtId(Long artId) {
        return null;
    }

    @Override
    public ArticleTopWordsDTO saveArticleTopWords(ArticleTopWordsDTO articleTopWordsDTO) {
        return null;
    }

}

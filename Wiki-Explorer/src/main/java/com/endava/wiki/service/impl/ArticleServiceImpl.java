package com.endava.wiki.service.impl;

import com.endava.wiki.repository.ArticleRepository;
import com.endava.wiki.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by sroboiu on 11-Aug-16.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

}

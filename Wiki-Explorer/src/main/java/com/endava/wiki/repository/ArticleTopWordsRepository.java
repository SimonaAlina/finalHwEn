package com.endava.wiki.repository;

import com.endava.wiki.entity.Article;
import com.endava.wiki.entity.ArticleTopWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by sroboiu on 16-Aug-16.
 */
@Repository
public interface ArticleTopWordsRepository extends JpaRepository<ArticleTopWords, Long> {

    List<ArticleTopWords> findByArticle(Article article);
}

package com.endava.wiki.repository;

import com.endava.wiki.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    Article findFirstByArticleName(String articleName);

}

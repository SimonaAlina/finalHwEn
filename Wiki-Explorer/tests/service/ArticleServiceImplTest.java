package service;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.entity.Article;
import com.endava.wiki.mapping.MapEntityDTO;
import com.endava.wiki.repository.ArticleRepository;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.impl.ArticleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created on 18-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private MapEntityDTO mappingArticle;

    @Mock
    ArticleService articleService;


    @Test
    public void getArticleById() throws Exception {

        when(articleService.getArticleById(any(Long.class)))
                .thenReturn(new ArticleDTO());
        when(articleRepository.findOne(any(Long.class)))
                .thenReturn(new Article());
    }

    @Test
    public void getArticleByName() throws Exception {
        when(articleService.getArticleByName(any(String.class)))
                .thenReturn(new ArticleDTO());
        when(articleRepository.findFirstByArticleName(any(String.class)))
                .thenReturn(new Article());
    }

    @Test
    public void getAllArticles() throws Exception {

        when(articleService.getAllArticles())
                .thenReturn(new ArrayList<ArticleDTO>());
        when(articleRepository.findAll())
                .thenReturn(new ArrayList<Article>());

    }

    @Test
    public void saveArticle() throws Exception {
        when(articleService.saveArticle(any(ArticleDTO.class)))
                .thenReturn(new ArticleDTO());
        when(mappingArticle.mapDtoToEntity(any(ArticleDTO.class)))
                .thenReturn(new Article());
    }

}
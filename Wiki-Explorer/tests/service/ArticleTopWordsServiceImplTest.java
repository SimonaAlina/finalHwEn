package service;

import com.endava.wiki.repository.ArticleTopWordsRepository;
import com.endava.wiki.service.impl.ArticleTopWordsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created on 18-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)

public class ArticleTopWordsServiceImplTest {

    @Mock
    ArticleTopWordsRepository articleTopWordsRepository;
    @Mock
    ArticleTopWordsServiceImpl articleTopWordsServiceImpl;

    @Test
    public void getArticleTopWordsById() throws Exception {

    }

    @Test
    public void getArticleTopWordsByArtId() throws Exception {

    }

    @Test
    public void saveArticleTopWords() throws Exception {

    }

}
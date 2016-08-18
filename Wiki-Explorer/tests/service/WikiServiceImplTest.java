package service;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.parser.FileParser;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.ArticleTopWordsService;
import com.endava.wiki.service.WikiArticleService;
import com.endava.wiki.service.WikiService;
import com.endava.wiki.service.impl.WikiServiceImpl;
import javafx.beans.binding.When;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.AdditionalMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created on 18-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class WikiServiceImplTest {

    @Mock
    WikiService wikiService;
    @Mock
    ArticleService articleService;
    @Mock
    WikiArticleService wikiArticleService;
    @Mock
    FileParser fileParser;

    @Before
    public void setUp() throws Exception {
//        wikiServiceImpl = new WikiServiceImpl();
    }

    @Test
    public void verify_class_methods() throws Exception {
        {
            when(wikiService.getSimpleResult(any(String.class)))
                    .thenReturn(new Hashtable<String, Integer>());
            when(articleService.getArticleByName(any(String.class)))
                    .thenReturn(new ArticleDTO());
            when(wikiArticleService.parseContentResultFromWiki(any(String.class)))
                    .thenReturn(new Hashtable<String, Integer>());
            when(articleService.saveArticle(any(ArticleDTO.class)))
                    .thenReturn(new ArticleDTO());
        }
        {
            when(wikiService.getMultipleResult(any(String.class)))
                    .thenReturn(new Hashtable<String, Integer>());
            when(fileParser.parseInputFile(any(String.class)))
                    .thenReturn(new HashSet<String>());
        }
    }
}
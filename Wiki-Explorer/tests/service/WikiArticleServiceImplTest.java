package service;

import com.endava.wiki.parser.ContentParser;
import com.endava.wiki.service.WikiArticleService;
import com.endava.wiki.service.impl.WikiArticleServiceImpl;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Hashtable;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.when;

/**
 * Created on 18-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class WikiArticleServiceImplTest {

    @Mock
    ContentParser contentParser;
    @Mock
    WikiArticleServiceImpl wikiArticleServiceImpl;

    @Mock
    HttpPost request;

    @Test
    public void parseContentResultFromWikiTest() throws Exception {
        when(wikiArticleServiceImpl.parseContentResultFromWiki(any(String.class)))
                .thenReturn(new Hashtable<String, Integer>());
        when(wikiArticleServiceImpl.getContentFromWiki(any(String.class)))
                .thenReturn(new String());
        when(contentParser.parseContent(any(String.class)))
                .thenReturn(new Hashtable<String, Integer>());
    }


    @Test
    public void getContentFromWikiTest() throws Exception {

        when(wikiArticleServiceImpl.getContentFromWiki(any(String.class)))
                .thenReturn(new String());

    }

}
package controller;

import com.endava.wiki.controller.ArticleController;
import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.service.MultiThreadsWikiService;
import com.endava.wiki.service.WikiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Hashtable;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created on 19-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArticleControllerTest {

    @Mock
    private WikiService wikiService;
    @Mock
    private MultiThreadsWikiService multiThreadsWikiService;
    @Mock
    ArticleController articleController;

    @Test
    public void getTopWordsTest() throws Exception {
        when(articleController.getTopWords(any(String.class)))
                .thenReturn(new LinkedHashMap<String, Integer>());

        when(wikiService.getSimpleResult(any(String.class)))
                .thenReturn(new ArticleDTO());
    }

    @Test
    public void getTopWordsFromFilesTest() throws Exception {
        when(articleController.getTopWordsFromFiles(any(MultipartFile.class)))
                .thenReturn(new LinkedHashMap<String, Integer>());

        when(multiThreadsWikiService.getMultipleResults(any(String.class)))
                .thenReturn(new Hashtable<String, Integer>());
    }

    @Test
    public void getTopWordsFromLocalFileTest() throws Exception {
        when(articleController.getTopWordsFromLocalFile())
                .thenReturn(new LinkedHashMap<String, Integer>());

        when(multiThreadsWikiService.getMultipleResults(any(String.class)))
                .thenReturn(new Hashtable<String, Integer>());
    }

}
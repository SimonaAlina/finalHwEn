package com.endava.wiki.service.impl;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.parser.ContentParser;
import com.endava.wiki.parser.FileParser;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.MultiThreadsWikiService;
import com.endava.wiki.service.WikiArticleService;
import com.endava.wiki.utils.AgregateMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.*;


/**
 * Created by sroboiu on 19-Aug-16.
 *
 * https://docs.oracle.com/javase/tutorial/essential/concurrency/executors.html
 */
@Service
public class MultiThreadsWikiServiceImpl implements MultiThreadsWikiService {

    @Autowired
    ArticleService articleService;
    @Autowired
    WikiArticleService wikiArticleService;
    @Autowired
    FileParser fileParser;

    AgregateMap agregateMap;

    ExecutorService executor;
    List<Future<ArticleDTO>> listFutures;
    private static int NR_THREADS = Runtime.getRuntime().availableProcessors();

    public MultiThreadsWikiServiceImpl() {
        executor = Executors.newFixedThreadPool(NR_THREADS);
        listFutures = new ArrayList<>();
        agregateMap = new AgregateMap();
    }

    public void searchTitle(final String title) {

        Future<ArticleDTO> future = executor.submit(
                () -> {
                    ArticleDTO articleDTO = new ArticleDTO();
                    articleDTO.setArticleName(title);

                    WikiArticleService wikiArticleService1 = new WikiArticleServiceImpl();
                    String content = wikiArticleService1.getContentFromWiki(title);
                    if(content == null)
                        return null;
                    Hashtable<String, Integer> wordsCount = new ContentParser().parseContent(content);
                    //agregare
                    agregateMap.addPartialResult(wordsCount);
                    articleDTO.setWordCount(wordsCount);

                    return articleDTO;
                });
        listFutures.add(future);
    }

    public Hashtable<String, Integer> getMultipleResults(String content) {

        List<String> titles = new ArrayList<>(fileParser.parseInputFile(content));

        if (titles == null || titles.isEmpty())
            return null;

        ArticleDTO articleDTO = null;

        for (String title : titles) {
            articleDTO = articleService.getArticleByName(title);
            if (articleDTO == null) {
                System.out.println("Get result from Wikipedia for: " + title);
                searchTitle(title);
            } else {
                //agrega articleDTO.getWordCount
                agregateMap.addPartialResult(articleDTO.getWordCount());
                System.out.println("Get result from Database for: " + title);
            }
        }
        for (Future<ArticleDTO> future : listFutures) {
            while (!future.isDone()) {}
            try {
                ArticleDTO articleDTOresult = future.get();
                articleService.saveArticle(articleDTOresult);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                System.out.println(e.getCause() + "for title:" + articleDTO.getArticleName());
            }
        }

        return agregateMap.getResult();
    }

}

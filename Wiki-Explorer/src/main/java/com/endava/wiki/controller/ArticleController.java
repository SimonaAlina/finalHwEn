package com.endava.wiki.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import com.endava.wiki.dto.WordCountDto;
import com.endava.wiki.service.ArticleService;
import com.endava.wiki.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/wikiapp")
@Scope(value = "session")
public class ArticleController {

    @Autowired
    private WikiService wikiService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getParamTest(@PathVariable Integer id) {
        List<WordCountDto> wc = Arrays.asList(new WordCountDto("gigel",2), new WordCountDto("ionel",3));

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("aBinding"," :) ");
        mv.addObject("wordCount",wc);

        return mv;
    }

    @RequestMapping(value = "/getTitle/{title}", method = RequestMethod.GET)
    public ModelAndView getTopWords(@PathVariable String title) {

        System.out.println("Controller: begining");

        ModelAndView mv = new ModelAndView("index");
        Hashtable<String, Integer> result = wikiService.getSimpleResult(title);
        if(result == null) {
            System.out.println("There is not a wikipedia file result");
        }
        //todo: sort hashtable and extract top ten
        mv.addObject("topWords", result);

        System.out.println("Result:\n" + result);

        return mv;
    }

    @RequestMapping(value = "/getTitles", method = RequestMethod.POST)
    public ModelAndView getTopWordsFromFiles(@RequestParam("file") MultipartFile file) {

        ModelAndView mv = new ModelAndView("index");

        try {
            String content = new String(file.getBytes());
            Hashtable<String, Integer> result = wikiService.getMultipleResult(content);
            //todo: sort hashtable and extract top ten
            mv.addObject("topWords", result);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return mv;

    }

    //todo : properly handle exception
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String handleFormUpload (@RequestParam("theFile") MultipartFile file) throws Exception {

        String theContent = new String(file.getBytes());

        return "";
    }

    //todo : metoda pt. upload fisiere
    //todo ; parsare fisier de upload (cu titluri)

    //todo : service responsabil cu download.
    //todo : service responsabil cu parsarea continutului unui wiki intoarce (Map<String, Int>)
    //todo : service + repo + entitati : responsabile cu DB.
    //todo : multithreading.

}

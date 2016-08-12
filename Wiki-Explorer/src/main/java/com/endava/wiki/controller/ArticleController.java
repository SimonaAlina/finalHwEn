package com.endava.wiki.controller;

import java.util.Arrays;
import java.util.List;

import com.endava.wiki.dto.WordCountDto;
import com.endava.wiki.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/wikiapp")
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getBook(@PathVariable Integer id) {
        List<WordCountDto> wc = Arrays.asList(new WordCountDto("gigel",2), new WordCountDto("ionel",3));

        ModelAndView mv = new ModelAndView("index");
        mv.addObject("aBinding","cel tare-n tenis");
        mv.addObject("wordCount",wc);

        return mv;
    }

    //todo : metoda pt. upload fisiere
    //todo ; parsare fisier de upload (cu titluri)

    //todo : service responsabil cu download.
    //todo : service responsabil cu parsarea continutului unui wiki intoarce (Map<String, Int>)
    //todo : service + repo + entitati : responsabile cu DB.
    //todo : multithreading.

}

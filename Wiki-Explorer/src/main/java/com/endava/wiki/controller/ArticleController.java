package com.endava.wiki.controller;

import java.util.List;

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
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("theTime","oValoare");

        return mv;
    }

}

package com.endava.wiki.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import com.endava.wiki.service.WikiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@Controller
@RequestMapping("/wikiapp")
@Scope(value = "session")
public class ArticleController {

    @Autowired
    private WikiService wikiService;

    @RequestMapping(value = "/getTitle/{title}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Integer> getTopWords(@PathVariable String title) {

        Hashtable<String, Integer> result = wikiService.getSimpleResult(title);

        if (result == null || result.isEmpty()) {
            System.out.println("There is not a wikipedia file result");
            return null;
        }

        Map<String, Integer> sortedMap =
                result.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .limit(10)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println("Search: " + title);
        System.out.println("Result:\n" + sortedMap);

        return sortedMap;
    }

    @RequestMapping(value = "/getTitles", method = RequestMethod.POST, consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Integer> getTopWordsFromFiles(@RequestParam("file") MultipartFile file) {

        String content = null;

        try {
            //Path path = Paths.get("D:\\homework\\Wiki-Explorer\\file.txt");
            //content = new String(Files.readAllBytes(path));

            content = new String(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Hashtable<String, Integer> result = wikiService.getMultipleResult(content);

        if (result == null) {
            System.out.println("Error: smth was wrong");
            return null;
        }

        Map<String, Integer> sortedMap =
                result.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                        .limit(10)
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                (e1, e2) -> e1, LinkedHashMap::new));
        sortedMap = sortedMap.entrySet().stream().limit(10).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("Result agregated from input file:\n" + sortedMap);

        return sortedMap;
    }

}

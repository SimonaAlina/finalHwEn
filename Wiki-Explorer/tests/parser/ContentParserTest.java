package parser;

import com.endava.wiki.parser.ContentParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContentParserTest {

    private ContentParser contentParser;

    @Before
    public void before() {
        contentParser = new ContentParser();
        System.out.println("Starting tests... ");
    }

    @Test
    public void testParseContent() throws Exception {

        String str = "Placing \\[]#$% deter snails from @ {{{coming to {th\\e} plants.The#$ \n \t decollate ''snail (Rumina decollata) {{{will}}' capture";
        assertEquals(13, contentParser.parseContent(str).size());

    }


}
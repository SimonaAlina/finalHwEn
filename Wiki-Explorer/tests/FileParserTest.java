import com.endava.wiki.parser.FileParser;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created on 16-Aug-16.
 */
public class FileParserTest {

    private FileParser fileParser;

    @Before
    public void before() {
        fileParser = new FileParser();
        System.out.println("Starting tests... ");
    }

    @Test
    public void parseInputFile() throws Exception {

        String str = "Andrei\n,Mihai\\Cristi\n,Andrei&@#,{Andrei}";

        assertEquals(3, fileParser.parseInputFile(str).size());

    }

}
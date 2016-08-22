package service;

import com.endava.wiki.service.MultiThreadsWikiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Hashtable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created on 22-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MultiThreadsWikiServiceImplTest {

    @Mock
    MultiThreadsWikiService multiThreadsWikiService;

    @Test
    public void searchTitle() throws Exception {

    }

    @Test
    public void getMultipleResults() throws Exception {
        when(multiThreadsWikiService.getMultipleResults(any(String.class)))
                .thenReturn(new Hashtable<String, Integer>());
    }

}
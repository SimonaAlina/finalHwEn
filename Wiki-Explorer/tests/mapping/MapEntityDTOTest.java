package mapping;

import com.endava.wiki.dto.ArticleDTO;
import com.endava.wiki.entity.Article;
import com.endava.wiki.mapping.MapEntityDTO;
import com.endava.wiki.repository.ArticleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created on 18-Aug-16.
 */
@RunWith(MockitoJUnitRunner.class)

public class MapEntityDTOTest {

    @Mock
    MapEntityDTO mapEntityDTO;

    @Mock
    ArticleRepository articleRepository;

    @Test
    public void mapDtoToEntity() throws Exception {

        when(mapEntityDTO.mapDtoToEntity(any(ArticleDTO.class)))
                .thenReturn(new Article());

        when(articleRepository.findFirstByArticleName(any(String.class)))
                .thenReturn(new Article());
    }


    @Test
    public void mapEntityToDto() throws Exception {

        when(mapEntityDTO.mapEntityToDto(any(Article.class)))
                .thenReturn(new ArticleDTO());

    }

}
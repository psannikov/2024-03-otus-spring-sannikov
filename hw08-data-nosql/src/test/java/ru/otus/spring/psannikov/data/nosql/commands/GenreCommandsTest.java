package ru.otus.spring.psannikov.data.nosql.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.converters.GenreConverter;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.services.GenreService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Команды для Genre должны")
@DataMongoTest
@Import(GenreCommands.class)
public class GenreCommandsTest {

    @Autowired
    private GenreCommands genreCommands;

    @MockBean
    GenreService genreService;

    @MockBean
    GenreConverter genreConverter;

    private final static String ID = "66732661ad08f050f3a38b90";
    private final static String GENRE = "Genre_1";
    private final static String RETURN_STRING = "Id: 66732661ad08f050f3a38b90, Name: Genre_1";

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("выводить информацию о всех жанрах")
    @Test
    public void findAllGenresTest() {
        var mockGenre = new Genre(ID, GENRE);
        var mockGenres = List.of(mockGenre);
        when(genreService.findAll()).thenReturn(mockGenres);
        when(genreConverter.genreToString(mockGenre)).thenReturn(RETURN_STRING);
        String actualGenres = genreCommands.findAllGenres();
        assertThat(actualGenres).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualGenres);
    }

    @DisplayName("должен удалять существующий жанр")
    @Test
    public void deleteGenreTest() {
        genreCommands.deleteGenre(ID);
        verify(genreService, times(1)).deleteById(ID);
    }
}

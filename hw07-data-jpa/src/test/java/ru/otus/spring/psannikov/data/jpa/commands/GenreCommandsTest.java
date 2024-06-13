package ru.otus.spring.psannikov.data.jpa.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.jpa.converters.GenreConverter;
import ru.otus.spring.psannikov.data.jpa.models.Genre;
import ru.otus.spring.psannikov.data.jpa.services.GenreService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Команды для Genre должны")
@DataJpaTest
@Import(GenreCommands.class)
public class GenreCommandsTest {

    @Autowired
    private GenreCommands genreCommands;

    @MockBean
    GenreService genreService;

    @MockBean
    GenreConverter genreConverter;

    private final static long ID = 1L;
    private final static String GENRE = "Genre_1";
    private final static String RETURN_STRING = "Id: 1, Name: Author_1";

    @DisplayName("выводить информацию о всех жанрах")
    @Test
    public void findAllAuthorsTest() {
        var mockGenre = new Genre(ID, GENRE);
        var mockGenres = List.of(mockGenre);
        when(genreService.findAll()).thenReturn(mockGenres);
        when(genreConverter.genreToString(mockGenre)).thenReturn(RETURN_STRING);
        String actualGenres = genreCommands.findAllGenres();
        assertEquals(RETURN_STRING, actualGenres);
    }
}

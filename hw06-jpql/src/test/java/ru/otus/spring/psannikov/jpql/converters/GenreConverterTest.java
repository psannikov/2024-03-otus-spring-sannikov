package ru.otus.spring.psannikov.jpql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreConverter.class)
@DisplayName("Конвертер жанров должен")
public class GenreConverterTest {

    @Autowired
    private GenreConverter genreConverter;

    private static final long ID = 1l;
    private static final String GENRE = "Genre_1";
    private static final String EXPECTED_GENRE_STRING = "Id: 1, Name: Genre_1";


    @DisplayName("должен вывести информацию о жанре")
    @Test
    void genreToStringTest() {
        var actualGenre = genreConverter.genreToString(new Genre(ID, GENRE));
        assertThat(actualGenre).isEqualTo(EXPECTED_GENRE_STRING);
    }
}

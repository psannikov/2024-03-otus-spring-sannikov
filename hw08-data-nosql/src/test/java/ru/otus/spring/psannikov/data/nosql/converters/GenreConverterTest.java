package ru.otus.spring.psannikov.data.nosql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import(GenreConverter.class)
@DisplayName("Конвертер жанров должен")
public class GenreConverterTest {

    @Autowired
    private GenreConverter genreConverter;

    private static final String ID = "667328bb2266e3cd9fd099ac";
    private static final String GENRE = "Genre_1";
    private static final String EXPECTED_GENRE_STRING = "Id: 667328bb2266e3cd9fd099ac, Name: Genre_1";

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вывести информацию о жанре")
    @Test
    void genreToStringTest() {
        var actualGenre = genreConverter.genreToString(new Genre(ID, GENRE));
        assertThat(actualGenre).isEqualTo(EXPECTED_GENRE_STRING);
    }
}

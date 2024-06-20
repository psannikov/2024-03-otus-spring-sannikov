package ru.otus.spring.psannikov.data.jpa.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.jpa.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorConverter.class)
@DisplayName("Конвертер авторов должен")
public class AuthorConverterTest {

    @Autowired
    private AuthorConverter authorConverter;

    private static final long ID = 1L;
    private static final String AUTHOR = "Author_1";
    private static final String EXPECTED_AUTHOR_STRING = "Id: 1, FullName: Author_1";


    @DisplayName("вывести информацию о авторе")
    @Test
    void shouldReturnCorrectAuthorInfo() {
        var actualAuthor = authorConverter.authorToString(new Author(ID, AUTHOR));
        assertThat(actualAuthor).isEqualTo(EXPECTED_AUTHOR_STRING);
    }
}

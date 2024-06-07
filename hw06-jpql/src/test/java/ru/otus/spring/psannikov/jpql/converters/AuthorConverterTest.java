package ru.otus.spring.psannikov.jpql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorConverter.class)
@DisplayName("Конвертер авторов должен")
public class AuthorConverterTest {

    @Autowired
    private AuthorConverter authorConverter;

    private static final String EXPECTED_AUTHOR_STRING = "Id: 1, FullName: Author1";
    private static final long ID = 1l;
    private static final String AUTHOR_FIO = "Author1";

    @DisplayName("должен вывести информацию о авторе")
    @Test
    void shouldReturnCorrectAuthorInfo() {
        var actualAuthor = authorConverter.authorToString(new Author(ID, AUTHOR_FIO));
        assertThat(actualAuthor).isEqualTo(EXPECTED_AUTHOR_STRING);
    }
}

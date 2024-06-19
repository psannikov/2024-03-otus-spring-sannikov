package ru.otus.spring.psannikov.data.nosql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import(AuthorConverter.class)
@DisplayName("Конвертер авторов должен")
public class AuthorConverterTest {

    @Autowired
    private AuthorConverter authorConverter;

    private static final String ID = "6673271d724260ce3f2d763e";
    private static final String AUTHOR = "Author_1";
    private static final String EXPECTED_AUTHOR_STRING = "Id: 6673271d724260ce3f2d763e, FullName: Author_1";

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вывести информацию о авторе")
    @Test
    void shouldReturnCorrectAuthorInfo() {
        var actualAuthor = authorConverter.authorToString(new Author(ID, AUTHOR));
        assertThat(actualAuthor).isEqualTo(EXPECTED_AUTHOR_STRING);
    }
}

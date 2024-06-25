package ru.otus.spring.psannikov.data.nosql.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.converters.AuthorConverter;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.services.AuthorService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Команды для Author должны")
@DataMongoTest
@Import(AuthorCommands.class)
public class AuthorCommandsTest {

    @Autowired
    private AuthorCommands authorCommands;

    @MockBean
    AuthorService authorService;

    @MockBean
    AuthorConverter authorConverter;

    private final static String ID = "56e6835a90ffdc6014c728c0";
    private final static String AUTHOR = "Author_1";
    private final static String RETURN_STRING = "Id: 56e6835a90ffdc6014c728c0, FullName: Author_1";

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("выводить информацию о всех авторах")
    @Test
    public void findAllAuthorsTest() {
        var mockAuthor = new Author(ID, AUTHOR);
        var mockAuthors = List.of(mockAuthor);
        when(authorService.findAll()).thenReturn(mockAuthors);
        when(authorConverter.authorToString(mockAuthor)).thenReturn(RETURN_STRING);
        String actualAuthors = authorCommands.findAllAuthors();
        assertThat(actualAuthors).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualAuthors);
    }

    @DisplayName("должен удалять существующего автора")
    @Test
    public void deleteAuthorTest() {
        authorCommands.deleteAuthor(ID);
        verify(authorService, times(1)).deleteById(ID);
    }
}

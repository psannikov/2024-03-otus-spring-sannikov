package ru.otus.spring.psannikov.data.jpa.commands;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.jpa.converters.AuthorConverter;
import ru.otus.spring.psannikov.data.jpa.models.Author;
import ru.otus.spring.psannikov.data.jpa.services.AuthorService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Команды для Author должны")
@DataJpaTest
@Import(AuthorCommands.class)
public class AuthorCommandsTest {

    @Autowired
    private AuthorCommands authorCommands;

    @MockBean
    AuthorService authorService;

    @MockBean
    AuthorConverter authorConverter;

    private final static long ID = 1L;
    private final static String AUTHOR = "Author_1";
    private final static String RETURN_STRING = "Id: 1, FullName: Author_1";

    @DisplayName("выводить информацию о всех авторах")
    @Test
    public void findAllAuthorsTest() {
        var mockAuthor = new Author(ID, AUTHOR);
        var mockAuthors = List.of(mockAuthor);
        when(authorService.findAll()).thenReturn(mockAuthors);
        when(authorConverter.authorToString(mockAuthor)).thenReturn(RETURN_STRING);
        String actualAuthors = authorCommands.findAllAuthors();
        assertEquals(RETURN_STRING, actualAuthors);
    }
}

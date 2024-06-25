package ru.otus.spring.psannikov.data.nosql.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.models.Book;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для Author должен")
@DataMongoTest
@Import(AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @MockBean
    AuthorRepository authorRepository;

    private final static String ID_AUTHOR = "6673218685210a129228297e";
    private final static String AUTHOR = "Author_1";
    private Author mockAuthor;

    @BeforeEach
    public void setUp() {
        mockAuthor = new Author(ID_AUTHOR, AUTHOR);
    }

    @DisplayName("должен загружать автора по ID")
    @Test
    public void findByIdTest() {
        when(authorRepository.findById(ID_AUTHOR)).thenReturn(Optional.of(mockAuthor));
        var actualAuthor = authorService.findById(ID_AUTHOR);
        assertThat(actualAuthor).isNotEmpty();
        assertThat(actualAuthor.get()).isEqualTo(mockAuthor);
    }

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("должен загружать всех авторов")
    @Test
    public void findAllTest() {
        var mockAuthors = List.of(mockAuthor);
        when(authorRepository.findAll()).thenReturn(mockAuthors);
        var authors = authorService.findAll();
        assertThat(authors).isEqualTo(mockAuthors);
    }

    @DisplayName("должен удалять существующего автора")
    @Test
    public void deleteByIdTest() {
        authorService.deleteById(ID_AUTHOR);
        verify(authorRepository, times(1)).deleteById(ID_AUTHOR);
    }
}

package ru.otus.spring.psannikov.spring.security.auth.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.spring.security.auth.models.Author;
import ru.otus.spring.psannikov.spring.security.auth.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DisplayName("Сервис для Author должен")
@DataJpaTest
@Import(AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    private Author mockAuthor;
    private final static long ID = 1L;
    private final static String AUTHOR = "Author_1";

    @BeforeEach
    public void setUp() {
        mockAuthor = new Author(ID, AUTHOR);
    }

    @Autowired
    private AuthorServiceImpl authorService;

    @MockBean
    AuthorRepository authorRepository;

    @DisplayName("должен загружать автора по ID")
    @Test
    public void findByIdTest() {
        when(authorRepository.findById(ID)).thenReturn(Optional.of(mockAuthor));
        var actualAuthor = authorService.findById(ID);
        assertThat(actualAuthor).isNotEmpty();
        assertThat(actualAuthor.get()).isEqualTo(mockAuthor);
    }

    @DisplayName("должен загружать всех авторов")
    @Test
    public void findAllTest() {
        var authorsMock = List.of(new Author(1L, "Author_1"));
        when(authorRepository.findAll()).thenReturn(authorsMock);
        var authors = authorService.findAll();
        assertThat(authors).isEqualTo(authorsMock);
    }
}

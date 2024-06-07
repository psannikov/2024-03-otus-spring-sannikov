package ru.otus.spring.psannikov.jpql.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.repositories.AuthorRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Сервис для Author должен")
@DataJpaTest
@Import(AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    @MockBean
    AuthorRepository authorRepository;

    @DisplayName("должен загружать всех авторов")
    @Test
    public void getAllAuthorTest() {
        List<Author> authorsMock = List.of(new Author(1l, "Author_1"));
        when(authorRepository.findAll()).thenReturn(authorsMock);

        List<Author> authors = authorService.findAll();

        assertThat(authors).isEqualTo(authorsMock);
    }
}

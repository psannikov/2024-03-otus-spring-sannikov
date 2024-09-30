package ru.otus.spring.psannikov.hystrix.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.hystrix.models.Author;
import ru.otus.spring.psannikov.hystrix.repositories.AuthorRepository;

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
    public void findAllTest() {
        var authorsMock = List.of(new Author(1L, "Author_1"));
        when(authorRepository.findAll()).thenReturn(authorsMock);
        var authors = authorService.findAll();
        assertThat(authors).isEqualTo(authorsMock);
    }
}

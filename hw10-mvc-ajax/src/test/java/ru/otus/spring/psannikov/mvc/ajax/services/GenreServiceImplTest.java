package ru.otus.spring.psannikov.mvc.ajax.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.mvc.ajax.models.Genre;
import ru.otus.spring.psannikov.mvc.ajax.repositories.GenreRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Сервис для Genre должен")
@DataJpaTest
@Import(GenreServiceImpl.class)
public class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreService;

    @MockBean
    GenreRepository genreRepository;

    @DisplayName("должен загружать все жанры")
    @Test
    public void findAllTest() {
        var genresMock = List.of(new Genre(1L, "Genre_1"));
        when(genreRepository.findAll()).thenReturn(genresMock);
        var actualGenres = genreService.findAll();
        assertThat(actualGenres).isEqualTo(genresMock);
    }
}

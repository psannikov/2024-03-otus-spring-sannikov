package ru.otus.spring.psannikov.spring.security.acl.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.spring.security.acl.models.Genre;
import ru.otus.spring.psannikov.spring.security.acl.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Сервис для Genre должен")
@DataJpaTest
@Import(GenreServiceImpl.class)
public class GenreServiceImplTest {

    private final static long ID = 1L;
    private final static String GENRE = "Genre_1";

    @Autowired
    private GenreServiceImpl genreService;

    @MockBean
    GenreRepository genreRepository;

    private Genre mockGenre;

    @BeforeEach
    public void setUp() {
        mockGenre = Mockito.mock(Genre.class);
        mockGenre = new Genre(ID, GENRE);
    }

    @DisplayName("должен загружать жанр по ID")
    @Test
    public void findByIdTest() {
        when(genreRepository.findById(ID)).thenReturn(Optional.of(mockGenre));
        var actualGenre = genreService.findById(ID);
        assertThat(actualGenre).isNotEmpty();
        assertThat(actualGenre.get()).isEqualTo(mockGenre);
    }

    @DisplayName("должен загружать все жанры")
    @Test
    public void findAllTest() {
        var genresMock = List.of(new Genre(1L, "Genre_1"));
        when(genreRepository.findAll()).thenReturn(genresMock);
        var actualGenres = genreService.findAll();
        assertThat(actualGenres).isEqualTo(genresMock);
    }
}

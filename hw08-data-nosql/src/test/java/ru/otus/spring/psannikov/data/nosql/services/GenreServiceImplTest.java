package ru.otus.spring.psannikov.data.nosql.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.models.Genre;
import ru.otus.spring.psannikov.data.nosql.repositories.GenreRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для Genre должен")
@DataMongoTest
@Import(GenreServiceImpl.class)
public class GenreServiceImplTest {

    @Autowired
    private GenreServiceImpl genreService;

    @MockBean
    GenreRepository genreRepository;

    public static final String ID_GENRE = "66732d7e9d6152cb02bf1e38";
    public static final String GENRE = "Genre_1";
    private Genre mockGenre;

    @BeforeEach
    public void setUp() {
        mockGenre = new Genre(ID_GENRE, GENRE);
    }

    @DisplayName("должен загружать все жанры")
    @Test
    public void findAllTest() {
        var genresMock = List.of(mockGenre);
        when(genreRepository.findAll()).thenReturn(genresMock);
        var actualGenres = genreService.findAll();
        assertThat(actualGenres).isEqualTo(genresMock);
    }

    @DisplayName("должен удалять существующий жанр")
    @Test
    public void deleteByIdTest() {
        genreService.deleteById(ID_GENRE);
        verify(genreRepository, times(1)).deleteById(ID_GENRE);
    }

    @DisplayName("должен загружать автора по ID")
    @Test
    public void findByIdTest() {
        when(genreRepository.findById(ID_GENRE)).thenReturn(Optional.of(mockGenre));
        var actualGenre = genreService.findById(ID_GENRE);
        assertThat(actualGenre).isNotEmpty();
        assertThat(actualGenre.get()).isEqualTo(mockGenre);
    }
}

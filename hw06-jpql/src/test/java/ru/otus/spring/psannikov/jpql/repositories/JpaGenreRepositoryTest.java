package ru.otus.spring.psannikov.jpql.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.jpql.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для Genre должен")
@DataJpaTest
@Import(JpaGenreRepository.class)
@Transactional(propagation = Propagation.NEVER)
public class JpaGenreRepositoryTest {
    @Autowired
    private JpaGenreRepository genreRepository;
    @Autowired
    private TestEntityManager entityManager;

    private static final long FIRST_GENRE_ID = 1L;

    @DisplayName("должен загружать жанр по id")
    @Test
    @Transactional(readOnly = true)
    void shouldReturnCorrectGenreById() {
        var actualGenre = genreRepository.findById(FIRST_GENRE_ID);
        var expectedGenre = entityManager.find(Genre.class, FIRST_GENRE_ID);
        assertThat(actualGenre).isPresent()
                .get()
                .isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать список всех жанров")
    @Test
    @Transactional(readOnly = true)
    void shouldFindAllGenres() {
        var actualGenres = genreRepository.findAll();
        var expectedGenre = entityManager
                .getEntityManager()
                .createQuery("select g from Genre g")
                .getResultList();
        assertThat(actualGenres).containsExactlyElementsOf(expectedGenre);
    }
}

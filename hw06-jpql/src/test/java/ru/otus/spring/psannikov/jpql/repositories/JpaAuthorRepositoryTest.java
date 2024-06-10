package ru.otus.spring.psannikov.jpql.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Репозиторий для Author должен")
@DataJpaTest
@Import(JpaAuthorRepository.class)
public class JpaAuthorRepositoryTest {
    @Autowired
    private JpaAuthorRepository authorRepository;
    @Autowired
    private TestEntityManager entityManager;

    private static final long FIRST_AUTHOR_ID = 1L;

    @DisplayName("загружать автора по id")
    @Test
    void shouldReturnCorrectAuthorById() {
        var actualAuthor = authorRepository.findById(FIRST_AUTHOR_ID);
        var expectedAuthor = entityManager.find(Author.class, FIRST_AUTHOR_ID);
        assertThat(actualAuthor).isPresent()
                .get()
                .isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать список всех авторов")
    @Test
    void shouldFindAllAuthors() {
        var actualAuthors = authorRepository.findAll();
        var expectedAuthors = entityManager
                .getEntityManager()
                .createQuery("select a from Author a")
                .getResultList();
        assertThat(actualAuthors).containsExactlyElementsOf(expectedAuthors);
    }
}

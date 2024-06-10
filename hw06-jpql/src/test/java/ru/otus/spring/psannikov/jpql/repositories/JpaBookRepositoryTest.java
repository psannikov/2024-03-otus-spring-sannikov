package ru.otus.spring.psannikov.jpql.repositories;

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Genre;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.nullValue;

@DisplayName("Репозиторий для Book должен")
@DataJpaTest
@Import(JpaBookRepository.class)
public class JpaBookRepositoryTest {
    @Autowired
    private JpaBookRepository bookRepository;

    @Autowired
    private TestEntityManager entityManager;

    private static final long FIRST_BOOK_ID = 1L;

    private static final long FIRST_AUTHOR_ID = 1L;
    private static final long SECOND_AUTHOR_ID = 2L;
    private static final long FIRST_GENRE_ID = 1L;
    private static final long SECOND_GENRE_ID = 2L;

    @DisplayName("загружать книгу по id")
    @Test
    void shouldReturnCorrectBookById() {
        var actualBook = bookRepository.findById(FIRST_BOOK_ID);
        var expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBook).isPresent()
                .get()
                .isEqualTo(expectedBook);
    }

    @DisplayName("загружать список всех книг")
    @Test
    void shouldReturnCorrectBooksList() {
        var actualBooks = bookRepository.findAll();
        var expectedBooks = entityManager
                .getEntityManager()
                .createQuery("select b from Book b", Book.class)
                .getResultList();
        assertThat(actualBooks).containsExactlyElementsOf(expectedBooks);
    }

    @DisplayName("сохранять новую книгу")
    @Test
    void shouldSaveNewBook() {
        var actualBook = bookRepository.save(new Book(0L,
                "BookTitle_10500",
                entityManager.find(Author.class, FIRST_AUTHOR_ID),
                entityManager.find(Genre.class, FIRST_GENRE_ID)));
        var expectedBook = entityManager.find(Book.class, actualBook.getId());
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DisplayName("сохранять измененную книгу")
    @Test
    void shouldSaveUpdatedBook() {
        var actualBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        actualBook.setAuthor(entityManager.find(Author.class, SECOND_AUTHOR_ID));
        actualBook.setGenre(entityManager.find(Genre.class, SECOND_GENRE_ID));
        bookRepository.save(actualBook);
        var expectedBook = entityManager.find(Book.class, FIRST_BOOK_ID);
        assertThat(actualBook).isEqualTo(expectedBook);
    }

    @DisplayName("удалять книгу по id ")
    @Test
    void shouldDeleteBook() {
        bookRepository.deleteById(FIRST_BOOK_ID);
        MatcherAssert.assertThat(entityManager.find(Book.class, FIRST_BOOK_ID), nullValue());
    }
}

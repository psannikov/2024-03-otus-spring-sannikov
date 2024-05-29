package ru.otus.spring.psannikov.jpql.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.nullValue;

@DisplayName("Репозиторий для Comment должен")
@DataJpaTest
@Import(JpaCommentRepository.class)
public class JpaCommentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private JpaCommentRepository commentRepository;

    private static final long FIRST_COMMENT_ID = 1L;
    private static final long FIRST_BOOK_ID = 1L;
    private static final long SECOND_BOOK_ID = 2L;

    @DisplayName("должен загружать комментарий по id")
    @Test
    @Transactional(readOnly = true)
    void shouldReturnCorrectCommentById() {
        var actualComment = commentRepository.findById(FIRST_COMMENT_ID);
        var expectedComment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
        assertThat(actualComment).isPresent()
                .get()
                .isEqualTo(expectedComment);
    }

    @DisplayName("должен сохранять новый комментарий")
    @Test
    @Transactional
    void shouldSaveNewComment() {
        var actualComment = commentRepository.save(new Comment(0L,
                entityManager.find(Book.class, FIRST_BOOK_ID),
                "Comment_100500"));
        entityManager.clear();
        var expectedComment = entityManager.find(Comment.class, actualComment.getId());
        assertThat(expectedComment).isEqualTo(expectedComment);
    }

    @DisplayName("должен сохранять измененный комментарий")
    @Test
    @Transactional
    void shouldSaveUpdatedComment() {
        var actualComment = entityManager.find(Comment.class, FIRST_COMMENT_ID);
        actualComment.setFullComment("Comment_+100500+");
        actualComment.setBook(entityManager.find(Book.class, SECOND_BOOK_ID));
        commentRepository.save(actualComment);
        var expectedBook = entityManager.find(Comment.class, FIRST_BOOK_ID);
        assertThat(actualComment).isEqualTo(expectedBook);
    }

    @DisplayName("должен удалять комментарий по id ")
    @Test
    @Transactional
    void shouldDeleteComment() {
        commentRepository.deleteById(FIRST_BOOK_ID);
        entityManager.clear();
        assertThat(entityManager.find(Comment.class, FIRST_BOOK_ID), nullValue());
    }

    @DisplayName("должен находить все комментарии по id книги")
    @Test
    @Transactional
    void shouldFindAllCommentByBookId() {
        var actualCommentsByBookID = commentRepository.findAllByBookId(FIRST_BOOK_ID);
        var expectedCommentsByBookID = entityManager
                .getEntityManager()
                .createQuery("select c from Comment c " +
                        "left join c.book b " +
                        "where b.id = :id")
                .setParameter("id", FIRST_BOOK_ID)
                .getResultList();
        assertThat(actualCommentsByBookID).containsExactlyElementsOf(expectedCommentsByBookID);
    }
}
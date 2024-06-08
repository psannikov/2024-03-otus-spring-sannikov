package ru.otus.spring.psannikov.jpql.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Comment;
import ru.otus.spring.psannikov.jpql.models.Genre;
import ru.otus.spring.psannikov.jpql.repositories.AuthorRepository;
import ru.otus.spring.psannikov.jpql.repositories.BookRepository;
import ru.otus.spring.psannikov.jpql.repositories.CommentRepository;
import ru.otus.spring.psannikov.jpql.repositories.GenreRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DisplayName("Сервис для Comment должен")
@DataJpaTest
@Import(CommentServiceImpl.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    private final static long ID = 1l;
    private final static String FULL_COMMENT = "Comment_1";
    private Comment mockComment;


    @BeforeEach
    public void setUp() {
        mockComment = new Comment(ID, new Book(), FULL_COMMENT);
    }


    @DisplayName("должен загружать комментарий по ID")
    @Test
    public void findByIdTest() {
        when(commentRepository.findById(ID)).thenReturn(Optional.of(mockComment));
        var actualComment = commentRepository.findById(ID);
        assertThat(actualComment).isNotEmpty();
        assertThat(actualComment.get()).isEqualTo(mockComment);
    }
}

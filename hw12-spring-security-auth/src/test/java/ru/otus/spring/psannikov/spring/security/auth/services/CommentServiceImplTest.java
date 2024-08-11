package ru.otus.spring.psannikov.spring.security.auth.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.spring.security.auth.models.Book;
import ru.otus.spring.psannikov.spring.security.auth.models.Comment;
import ru.otus.spring.psannikov.spring.security.auth.repositories.BookRepository;
import ru.otus.spring.psannikov.spring.security.auth.repositories.CommentRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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

    private final static long ID = 1L;
    private final static String FULL_COMMENT = "Comment_1";
    private Comment mockComment;
    private Book mockBook;


    @BeforeEach
    public void setUp() {
        mockBook = Mockito.mock(Book.class);
        mockComment = new Comment(ID, FULL_COMMENT);
    }


    @DisplayName("должен загружать комментарий по ID")
    @Test
    public void findByIdTest() {
        when(commentRepository.findById(ID)).thenReturn(Optional.of(mockComment));
        var actualComment = commentRepository.findById(ID);
        assertThat(actualComment).isNotEmpty();
        assertThat(actualComment.get()).isEqualTo(mockComment);
    }

    @DisplayName("должен добавлять новый комментарий")
    @Test
    public void insertTest() {
        when(bookRepository.findById(ID)).thenReturn(Optional.of(mockBook));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(mockComment);
        var comment = commentService.insert(ID, FULL_COMMENT);
        assertEquals(comment, mockComment);
        verify(bookRepository, times(1)).findById(ID);
        verify(commentRepository, times(1)).save(Mockito.any(Comment.class));
    }

    @DisplayName("должен изменять существующую книгу")
    @Test
    public void updateTest() {
        when(bookRepository.findById(ID)).thenReturn(Optional.of(mockBook));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(mockComment);
        var comment = commentService.update(ID, ID, FULL_COMMENT);
        assertEquals(comment, mockComment);
        verify(bookRepository, times(1)).findById(ID);
        verify(commentRepository, times(1)).save(Mockito.any(Comment.class));
    }

    @DisplayName("должен удалять существующий комментарий")
    @Test
    public void deleteByIdTest() {
        commentService.deleteById(ID);
        verify(commentRepository, times(1)).deleteById(ID);
    }
}

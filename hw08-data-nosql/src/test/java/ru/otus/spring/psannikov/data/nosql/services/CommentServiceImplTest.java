package ru.otus.spring.psannikov.data.nosql.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.nosql.models.Book;
import ru.otus.spring.psannikov.data.nosql.models.Comment;
import ru.otus.spring.psannikov.data.nosql.repositories.BookRepository;
import ru.otus.spring.psannikov.data.nosql.repositories.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Сервис для Comment должен")
@DataMongoTest
@Import(CommentServiceImpl.class)
public class CommentServiceImplTest {

    @Autowired
    private CommentServiceImpl commentService;

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    private final static String ID_COMMENT = "66732bb379d387cb383df266";
    private final static String ID_BOOK = "66732c4740c5d08cfa319c93";
    private final static String FULL_COMMENT = "Comment_1";
    private Comment mockComment;
    private Book mockBook;


    @BeforeEach
    public void setUp() {
        mockBook = Mockito.mock(Book.class);
        mockComment = new Comment(ID_COMMENT, FULL_COMMENT);
    }


    @DisplayName("должен загружать комментарий по ID")
    @Test
    public void findByIdTest() {
        when(commentRepository.findById(ID_COMMENT)).thenReturn(Optional.of(mockComment));
        var actualComment = commentService.findById(ID_COMMENT);
        assertThat(actualComment).isNotEmpty();
        assertThat(actualComment.get()).isEqualTo(mockComment);
    }

    @DisplayName("должен добавлять новый комментарий")
    @Test
    public void insertTest() {
        when(bookRepository.findById(ID_BOOK)).thenReturn(Optional.of(mockBook));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(mockComment);
        var actualComment = commentService.insert(ID_BOOK, FULL_COMMENT);
        assertThat(actualComment).isEqualTo(mockComment);
//        assertEquals(actualComment, mockComment);
        verify(bookRepository, times(1)).findById(ID_BOOK);
        verify(commentRepository, times(1)).save(Mockito.any(Comment.class));
    }

    @DisplayName("должен изменять существующую книгу")
    @Test
    public void updateTest() {
        when(bookRepository.findById(ID_BOOK)).thenReturn(Optional.of(mockBook));
        when(commentRepository.save(Mockito.any(Comment.class))).thenReturn(mockComment);
        var actualComment = commentService.update(ID_COMMENT, ID_BOOK, FULL_COMMENT);
        assertThat(actualComment).isEqualTo(mockComment);
//        assertEquals(comment, mockComment);
        verify(bookRepository, times(1)).findById(ID_BOOK);
        verify(commentRepository, times(1)).save(Mockito.any(Comment.class));
    }

    @DisplayName("должен удалять существующий комментарий")
    @Test
    public void deleteByIdTest() {
        commentService.deleteById(ID_COMMENT);
        verify(commentRepository, times(1)).deleteById(ID_COMMENT);
    }

    @DisplayName("должен находить все комментарии по ID книги")
    @Test
    public void findAllByBookIdTest() {
        var mockComments = List.of(mockComment);
        when(bookRepository.findById(ID_BOOK)).thenReturn(Optional.of(mockBook));
        when(mockBook.getComments()).thenReturn(mockComments);
        var actualComments = commentService.findAllByBookId(ID_BOOK);
        assertThat(actualComments).isEqualTo(mockComments);
    }
}

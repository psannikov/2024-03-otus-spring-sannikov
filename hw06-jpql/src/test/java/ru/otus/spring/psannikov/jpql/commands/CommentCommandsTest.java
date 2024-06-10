package ru.otus.spring.psannikov.jpql.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.jpql.converters.CommentConverter;
import ru.otus.spring.psannikov.jpql.models.Author;
import ru.otus.spring.psannikov.jpql.models.Book;
import ru.otus.spring.psannikov.jpql.models.Comment;
import ru.otus.spring.psannikov.jpql.models.Genre;
import ru.otus.spring.psannikov.jpql.services.CommentService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@DisplayName("Команды для Comment должны")
@DataJpaTest
@Import(CommentCommands.class)
public class CommentCommandsTest {
    @Autowired
    private CommentCommands commentCommands;

    @MockBean
    CommentService commentService;

    @MockBean
    CommentConverter commentConverter;

    private final static long ID = 1L;
    private final static String TITLE = "Title_1";
    private final static String AUTHOR = "Author_1";
    private final static String GENRE = "Genre_1";
    private final static String RETURN_STRING = "Тестовый комментарий";

    private Comment mockComment;

    @BeforeEach
    public void setUp() {
        mockComment = new Comment(ID,
                new Book(ID, TITLE, new Author(ID, AUTHOR), new Genre(ID, GENRE)),
                RETURN_STRING);
    }

    @DisplayName("выводить информацию о коментарии по Id")
    @Test
    public void findCommentByIdTest() {
        when(commentService.findById(ID)).thenReturn(Optional.of(mockComment));
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.findCommentById(ID);
        assertThat(actualComment).isEqualTo(RETURN_STRING);
    }

    @DisplayName("добавлять новый комментарий")
    @Test
    public void insertCommentTest() {
        when(commentService.insert(ID, RETURN_STRING)).thenReturn(mockComment);
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.insertComment(ID, RETURN_STRING);
        assertEquals(RETURN_STRING, actualComment);
    }

    @DisplayName("изменять существующий комментарий")
    @Test
    public void updateCommentTest() {
        when(commentService.update(ID, ID, RETURN_STRING)).thenReturn(mockComment);
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.updateComment(ID, ID, RETURN_STRING);
        assertEquals(RETURN_STRING, actualComment);
    }

    @DisplayName("выводить все комментарии по ID книги")
    @Test
    public void findAllByBookIdTest() {
        when(commentService.findAllByBookId(ID)).thenReturn(List.of(mockComment));
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.findAllByBookId(ID);
        assertEquals(RETURN_STRING, actualComment);
    }
}

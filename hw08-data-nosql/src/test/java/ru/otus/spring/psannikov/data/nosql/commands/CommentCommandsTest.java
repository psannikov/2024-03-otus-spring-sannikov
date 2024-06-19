package ru.otus.spring.psannikov.data.nosql.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.converters.CommentConverter;
import ru.otus.spring.psannikov.data.nosql.models.Comment;
import ru.otus.spring.psannikov.data.nosql.services.CommentService;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Команды для Comment должны")
@DataMongoTest
@Import(CommentCommands.class)
public class CommentCommandsTest {
    @Autowired
    private CommentCommands commentCommands;

    @MockBean
    CommentService commentService;

    @MockBean
    CommentConverter commentConverter;

    private final static String ID_COMMENT = "66732220c1b319daf1733d48";
    private final static String ID_BOOK = "667322ab5dfd1ea6e6b4c820";
    private final static String FULL_COMMENT = "My Comment";
    private final static String RETURN_STRING = "Тестовый комментарий";

    private Comment mockComment;

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @BeforeEach
    public void setUp() {
        mockComment = new Comment(ID_COMMENT, FULL_COMMENT);
    }

    @DisplayName("выводить информацию о коментарии по Id")
    @Test
    public void findCommentByIdTest() {
        when(commentService.findById(ID_COMMENT)).thenReturn(Optional.of(mockComment));
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.findCommentById(ID_COMMENT);
        assertThat(actualComment).isEqualTo(RETURN_STRING);
    }

    @DisplayName("добавлять новый комментарий")
    @Test
    public void insertCommentTest() {
        when(commentService.insert(ID_COMMENT, FULL_COMMENT)).thenReturn(mockComment);
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.insertComment(ID_COMMENT, FULL_COMMENT);
        assertThat(actualComment).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualComment);
    }

    @DisplayName("изменять существующий комментарий")
    @Test
    public void updateCommentTest() {
        when(commentService.update(ID_COMMENT, ID_BOOK, RETURN_STRING)).thenReturn(mockComment);
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.updateComment(ID_COMMENT, ID_BOOK, RETURN_STRING);
        assertThat(actualComment).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualComment);
    }

    @DisplayName("выводить все комментарии по ID книги")
    @Test
    public void findAllByBookIdTest() {
        when(commentService.findAllByBookId(ID_BOOK)).thenReturn(List.of(mockComment));
        when(commentConverter.commentToString(mockComment)).thenReturn(RETURN_STRING);
        var actualComment = commentCommands.findAllByBookId(ID_BOOK);
        assertThat(actualComment).isEqualTo(RETURN_STRING);
//        assertEquals(RETURN_STRING, actualComment);
    }

    @DisplayName("должен удалять существующий комментарий")
    @Test
    public void deleteCommentTest() {
        commentCommands.deleteComment(ID_COMMENT);
        verify(commentService, times(1)).deleteById(ID_COMMENT);
    }
}

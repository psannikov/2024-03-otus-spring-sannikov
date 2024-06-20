package ru.otus.spring.psannikov.data.jpa.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.psannikov.data.jpa.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(CommentConverter.class)
@DisplayName("Конвертер комментариев должен")
public class CommentConverterTest {

    @Autowired
    private CommentConverter commentConverter;

    private static final long ID = 1L;
    private static final String FULL_COMMENT = "Comment 1";
    private static final Comment COMMENT = new Comment(ID, FULL_COMMENT);
    private static final String COMMENT_STRING = "Id: 1, FullComment: Comment 1";


    @DisplayName("вывести информацию о комментарии")
    @Test
    void commentToStringTest() {
        var actualComment = commentConverter.commentToString(COMMENT);
        assertThat(actualComment).isEqualTo(COMMENT_STRING);
    }
}

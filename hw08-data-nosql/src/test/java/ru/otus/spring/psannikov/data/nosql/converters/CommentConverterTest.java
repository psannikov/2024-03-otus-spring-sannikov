package ru.otus.spring.psannikov.data.nosql.converters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.spring.psannikov.data.nosql.models.Comment;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@Import(CommentConverter.class)
@DisplayName("Конвертер комментариев должен")
public class CommentConverterTest {

    @Autowired
    private CommentConverter commentConverter;

    private static final String ID = "6673288a3aecbc9631b1b29f";
    private static final String FULL_COMMENT = "Comment 1";
    private static final Comment COMMENT = new Comment(ID, FULL_COMMENT);
    private static final String COMMENT_STRING = "Id: 6673288a3aecbc9631b1b29f, FullComment: Comment 1";

//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вывести информацию о комментарии")
    @Test
    void commentToStringTest() {
        var actualComment = commentConverter.commentToString(COMMENT);
        assertThat(actualComment).isEqualTo(COMMENT_STRING);
    }
}

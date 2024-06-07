package ru.otus.spring.psannikov.jpql.converters;

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

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@Import(CommentConverter.class)
@DisplayName("Конвертер комментариев должен")
public class CommentConverterTest {

    @Autowired
    private CommentConverter commentConverter;

    @MockBean
    private BookConverter bookConverter;

    private static final long ID = 1l;
    private static final String TITLE = "Title_1";
    private static final String AUTHOR = "Author_1";
    private static final String GENRE = "Genre_1";
    private static final Book BOOK = new Book(ID, TITLE, new Author(ID, AUTHOR), new Genre(ID, GENRE));
    private static final String FULL_COMMENT = "Comment 1";
    private static final String BOOK_STRING = "This is Book";
    private static final Comment COMMENT = new Comment(ID,BOOK,FULL_COMMENT);
    private static final String COMMENT_STRING = "Id: 1, FullComment: Comment 1, Book: {This is Book}";


    @DisplayName("должен вывести информацию о комментарие")
    @Test
    void commentToStringTest() {
        when(bookConverter.bookToString(any())).thenReturn(BOOK_STRING);
        var actualComment = commentConverter.commentToString(COMMENT);
        assertThat(actualComment).isEqualTo(COMMENT_STRING);
    }
}

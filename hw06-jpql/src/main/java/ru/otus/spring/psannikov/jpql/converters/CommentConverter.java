package ru.otus.spring.psannikov.jpql.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.jpql.models.Comment;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    private final BookConverter bookConverter;

    public String commentToString(Comment comment) {
        return "Id: %d, FullComment: %s, Book: {%s}"
                .formatted(comment.getId(),
                        comment.getFullComment(),
                        bookConverter.bookToString(comment.getBook()));
    }
}

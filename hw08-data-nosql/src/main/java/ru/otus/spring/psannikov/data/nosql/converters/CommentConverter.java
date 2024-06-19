package ru.otus.spring.psannikov.data.nosql.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.nosql.models.Comment;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    public String commentToString(Comment comment) {
        return "Id: %s, FullComment: %s"
                .formatted(comment.getId(),
                        comment.getFullComment());
    }
}

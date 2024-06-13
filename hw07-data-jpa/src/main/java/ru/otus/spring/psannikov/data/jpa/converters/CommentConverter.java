package ru.otus.spring.psannikov.data.jpa.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.spring.psannikov.data.jpa.models.Comment;

@RequiredArgsConstructor
@Component
public class CommentConverter {

    public String commentToString(Comment comment) {
        return "Id: %d, FullComment: %s"
                .formatted(comment.getId(),
                        comment.getFullComment());
    }
}

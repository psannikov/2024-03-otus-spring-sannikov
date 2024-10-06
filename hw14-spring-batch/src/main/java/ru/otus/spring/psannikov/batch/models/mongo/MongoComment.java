package ru.otus.spring.psannikov.batch.models.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.psannikov.batch.models.jpa.Comment;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
@EqualsAndHashCode(of = {"fullComment"})
public class MongoComment {
    private String fullComment;

    public static MongoComment convert(Comment comment) {
        return new MongoComment(comment.getFullComment());
    }
}

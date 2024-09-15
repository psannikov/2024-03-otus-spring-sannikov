package ru.otus.spring.psannikov.batch.models.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.psannikov.batch.models.postgres.Comment;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
@EqualsAndHashCode(of = {"fullComment"})
public class MongoComment {

    @Id
    private String id;

    private String fullComment;

    public MongoComment(String fullComment) {
        this.fullComment = fullComment;
    }

    public static MongoComment convert(Comment comment) {
        return new MongoComment(comment.getFullComment());
    }
}

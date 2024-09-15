package ru.otus.spring.psannikov.batch.models.mongo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.otus.spring.psannikov.batch.models.postgres.Genre;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document
@EqualsAndHashCode(of = {"name"})
public class MongoGenre {

    @Id
    private String id;

    private String name;

    public MongoGenre(String name) {
        this.name = name;
    }

    public static MongoGenre convert(Genre genre) {
        return new MongoGenre(genre.getName());
    }
}

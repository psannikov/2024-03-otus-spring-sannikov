package ru.otus.spring.psannikov.mvc.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BookDto {

    private long id;

    private String title;

    private long authorId;

    private long genreId;

}

package ru.otus.spring.psannikov.mvc.view.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BookInsertDto {

    private String title;

    private long authorId;

    private long genreId;

}

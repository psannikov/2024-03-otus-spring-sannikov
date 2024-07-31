package ru.otus.spring.psannikov.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class Memo {

    private String title;

    private String fullText;

    private User author;

    private LocalDateTime date;
}

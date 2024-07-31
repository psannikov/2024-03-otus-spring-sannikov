package ru.otus.spring.psannikov.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Builder
public class Task {

    private String title;

    private String description;

    private Memo memo;

    private User performer;

    @Setter
    private Status status;
}

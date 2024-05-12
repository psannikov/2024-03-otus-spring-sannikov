package ru.otus.spring.psannikov.spring.boot.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}

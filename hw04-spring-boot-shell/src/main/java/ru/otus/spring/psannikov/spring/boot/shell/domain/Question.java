package ru.otus.spring.psannikov.spring.boot.shell.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}

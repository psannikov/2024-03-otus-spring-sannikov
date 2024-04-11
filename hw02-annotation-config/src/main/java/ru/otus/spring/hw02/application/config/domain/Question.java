package ru.otus.spring.hw02.application.config.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}

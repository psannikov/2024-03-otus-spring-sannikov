package ru.otus.spring.psannikov.hw01.xml.config.domain;

import java.util.List;

public record Question(String text, List<Answer> answers) {
}

package ru.otus.spring.psannikov.hw01.xml.config.config;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AppProperties implements TestFileNameProvider {
    private final String testFileName;
}

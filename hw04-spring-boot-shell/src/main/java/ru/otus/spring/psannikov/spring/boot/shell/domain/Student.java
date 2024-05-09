package ru.otus.spring.psannikov.spring.boot.shell.domain;

public record Student(String firstName, String lastName) {
    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}

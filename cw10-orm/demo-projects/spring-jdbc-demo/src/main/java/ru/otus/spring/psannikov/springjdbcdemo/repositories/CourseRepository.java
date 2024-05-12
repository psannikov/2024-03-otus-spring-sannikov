package ru.otus.spring.psannikov.springjdbcdemo.repositories;

import ru.otus.spring.psannikov.springjdbcdemo.models.Course;

import java.util.List;

public interface CourseRepository {
    List<Course> findAllUsed();
}

package ru.otus.spring.psannikov.springjdbcdemo.repositories;

import ru.otus.spring.psannikov.springjdbcdemo.models.OtusStudent;

import java.util.List;

public interface OtusStudentRepository {
    List<OtusStudent> findAllWithAllInfo();
}

package ru.otus.spring.psannikov.ormdemo.repositories;


import ru.otus.spring.psannikov.ormdemo.models.OtusStudent;

import java.util.List;
import java.util.Optional;

public interface OtusStudentRepository {
    OtusStudent save(OtusStudent student);
    Optional<OtusStudent> findById(long id);

    List<OtusStudent> findAll();
    List<OtusStudent> findByName(String name);

    void updateNameById(long id, String name);
    void deleteById(long id);
}

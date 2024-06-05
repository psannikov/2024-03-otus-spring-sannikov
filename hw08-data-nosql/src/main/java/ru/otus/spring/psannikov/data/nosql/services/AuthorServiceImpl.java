package ru.otus.spring.psannikov.data.nosql.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.psannikov.data.nosql.models.Author;
import ru.otus.spring.psannikov.data.nosql.repositories.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Repository
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(String id) {
        return authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(String id) {
        authorRepository.deleteById(id);
    }
}

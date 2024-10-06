package ru.otus.spring.psannikov.service;

import java.util.List;

import ru.otus.spring.psannikov.dto.Country;

public interface CountryService {

	Country findByCode(String id);

	List<Country> getAll();
}

package ru.otus.spring.psannikov.service;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

public class PersonServiceImpl implements PersonService {

	private final PersonDao dao;

	public PersonServiceImpl(PersonDao dao) {
		this.dao = dao;
	}

	@Override
	public Person getByName(String name) {
		return dao.findByName(name);
	}
}

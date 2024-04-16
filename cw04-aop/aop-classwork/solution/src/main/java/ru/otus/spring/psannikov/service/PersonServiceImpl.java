package ru.otus.spring.psannikov.service;

import org.springframework.stereotype.Service;

import ru.otus.spring.psannikov.dao.PersonDao;
import ru.otus.spring.psannikov.domain.Person;

@Service
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

package ru.otus.spring.psannikov.dao;

import org.springframework.stereotype.Repository;

import ru.otus.spring.psannikov.domain.Person;
import ru.otus.spring.psannikov.logging.LogMe;

@Repository
public class PersonDaoSimple implements PersonDao {

	@Override
	@LogMe
	public Person findByName(String name) {
		return new Person(name, 18);
	}
}

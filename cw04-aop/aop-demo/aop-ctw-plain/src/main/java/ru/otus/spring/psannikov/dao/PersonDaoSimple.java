package ru.otus.spring.psannikov.dao;

import ru.otus.spring.domain.Person;

public class PersonDaoSimple implements PersonDao {

	@Override
	public Person findByName(String name) {
		return new Person(name, 18);
	}
}

package ru.otus.spring.psannikov.dao;

import ru.otus.demo.domain.Person;


public class PersonDaoSimple implements PersonDao {

	@Override
	public Person findByName(String name) {
		return new Person(name, 18);
	}
}

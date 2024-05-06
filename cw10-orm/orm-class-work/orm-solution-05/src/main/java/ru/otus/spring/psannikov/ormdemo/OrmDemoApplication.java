package ru.otus.spring.psannikov.ormdemo;

import org.h2.tools.Console;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrmDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrmDemoApplication.class, args);
		//Console.main(args);
	}
}

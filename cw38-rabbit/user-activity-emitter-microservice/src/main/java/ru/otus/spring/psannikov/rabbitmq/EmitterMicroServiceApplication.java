package ru.otus.spring.psannikov.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan("ru.otus.spring.psannikov.useractivitymodels")
@SpringBootApplication
public class EmitterMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmitterMicroServiceApplication.class, args);
	}

}

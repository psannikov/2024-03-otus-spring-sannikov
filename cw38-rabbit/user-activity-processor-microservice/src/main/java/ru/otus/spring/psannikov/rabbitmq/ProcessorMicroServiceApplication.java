package ru.otus.spring.psannikov.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EntityScan("ru.otus.example.useractivitymodels")
@SpringBootApplication
public class ProcessorMicroServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcessorMicroServiceApplication.class, args);
	}

}

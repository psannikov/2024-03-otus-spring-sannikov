package ru.otus.spring.psannikov.testconfigurationdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.otus.spring.psannikov.testconfigurationdemo.family")
@SpringBootApplication
public class TestConfigurationDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestConfigurationDemoApplication.class, args);
	}

}

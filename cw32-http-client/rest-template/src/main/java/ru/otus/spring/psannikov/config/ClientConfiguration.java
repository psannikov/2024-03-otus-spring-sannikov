package ru.otus.spring.psannikov.config;

import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;

public class ClientConfiguration {

	@Bean
	public RequestInterceptor requestInterceptor(ClientProperties properties) {
		return requestTemplate -> requestTemplate.query("access_key", properties.getKey());
	}
}

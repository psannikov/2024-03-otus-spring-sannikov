package ru.otus.spring.psannikov.service;

import java.net.URI;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.spring.psannikov.config.ClientProperties;
import ru.otus.spring.psannikov.dto.Country;

@Slf4j
@RequiredArgsConstructor
@Service
@Cacheable(cacheNames = "countries")
public class TemplateCountryService implements CountryService {
	final ClientProperties properties;

	private final RestOperations rest = new RestTemplate();

	@Override
	@Retryable(retryFor = Exception.class, maxAttempts = 5, backoff = @Backoff(delay = 5000))
	public Country findByCode(String id) {
		log.info("RestTemplate Request findByCode");
		return rest.getForObject("http://api.countrylayer.com/v2/alpha/" + id + "?access_key=" + properties.getKey(),
				Country.class);
	}

	@Override
	public List<Country> getAll() {
		log.info("RestTemplate Request getAll");
		URI uri = UriComponentsBuilder.fromHttpUrl(properties.getUrl())
				.path("/all")
				.queryParam("access_key", properties.getKey())
				.build().toUri();
		RequestEntity<List<Country>> requestEntity = new RequestEntity<>(HttpMethod.GET,
				uri);
//		List<Country> countries = rest.getForObject(uri, List.class);
		ResponseEntity<List<Country>> exchange = rest.exchange(requestEntity, new ParameterizedTypeReference<>() {
		});
		return exchange.getBody();
	}
}

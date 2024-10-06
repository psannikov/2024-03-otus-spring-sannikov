package ru.otus.spring.psannikov.rabbitmq.actuator;

import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.psannikov.rabbitmq.repositories.ActivityStatRepository;
import ru.otus.spring.psannikov.useractivitymodels.ActivityStatElem;

@RequiredArgsConstructor
@Component
@Endpoint(id = "activity-stat")
public class ActivityStatEndpoint {

	private final ActivityStatRepository activityStatRepository;

	@ReadOperation
	public List<ActivityStatElem> getAppUsersActivityStat() {
		return activityStatRepository.findAll();
	}
}

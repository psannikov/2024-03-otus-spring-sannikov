package ru.otus.spring.psannikov.rabbitmq.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ActivityStatCalculationEmitterSerivce {
	private static final String USER_ACTIVITY_STAT_ROUTING_KEY = "user.activity.stat";
	private static final String CALC_STAT_COMMAND = "{\"command\": \"calc stat now\"}";

	private final RabbitTemplate rabbitTemplate;

	@Scheduled(initialDelay = 3000, fixedRate = 10000)
	public void emitAppUserActivityStatCalculation() {
//        log.warn("Stat send!!!");
		rabbitTemplate.convertAndSend(USER_ACTIVITY_STAT_ROUTING_KEY, CALC_STAT_COMMAND);
		log.warn("Stat calculated!!!");
	}
}

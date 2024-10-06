package ru.otus.spring.psannikov.rabbitmq.services;

import org.springframework.mail.SimpleMailMessage;

import ru.otus.spring.psannikov.useractivitymodels.UserActivity;

public interface UserActivityToEmailTransformer {
	SimpleMailMessage transform(UserActivity activity);
}

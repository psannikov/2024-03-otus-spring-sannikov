package ru.otus.spring.psannikov.rabbitmq.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.otus.spring.psannikov.rabbitmq.config.AppProps;
import ru.otus.spring.psannikov.useractivitymodels.UserActivity;

@RequiredArgsConstructor
@Service
public class UserActivityToEmailTransformerImpl implements UserActivityToEmailTransformer {

	private final AppProps appProps;

	@Override
	public SimpleMailMessage transform(UserActivity activity) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(appProps.getAdminEmail());
		mailMessage.setFrom(appProps.getServerEmail());
		mailMessage.setSubject("Обнаружена вредная активность");
		mailMessage.setText(String.format("Внимание!!! Обнаружена вредная активность! Время: %s, пользователь: %s, тип активности: %s",
				activity.getActivityTime(), activity.getAppUser().getName(), activity.getType().getName()));
		return mailMessage;
	}
}

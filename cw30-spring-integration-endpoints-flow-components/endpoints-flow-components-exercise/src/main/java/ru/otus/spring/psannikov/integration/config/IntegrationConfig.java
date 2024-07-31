package ru.otus.spring.psannikov.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannelSpec;
import org.springframework.integration.dsl.MessageChannels;
import ru.otus.spring.psannikov.integration.domain.Food;
import ru.otus.spring.psannikov.integration.domain.OrderItem;
import ru.otus.spring.psannikov.integration.services.KitchenService;

@SuppressWarnings("unused")
@Configuration
public class IntegrationConfig {
	@Bean
	public MessageChannelSpec<?, ?> itemsChannel() {
		return MessageChannels.queue(10);
	}

	@Bean
	public MessageChannelSpec<?, ?> foodChannel() {
		return MessageChannels.publishSubscribe();
	}

	@Bean
	public IntegrationFlow cafeFlow(KitchenService kitchenService) {
		return IntegrationFlow.from(itemsChannel())
				.split()
//				.handle((item) -> kitchenService.cook((OrderItem) item.getPayload()))
				.handle(kitchenService,"cook")
				.transform(Food.class, f -> f.getName().equals("water") ? new Food("wine") : f)
				.aggregate()
				.channel(foodChannel())
				.get();
	}
}

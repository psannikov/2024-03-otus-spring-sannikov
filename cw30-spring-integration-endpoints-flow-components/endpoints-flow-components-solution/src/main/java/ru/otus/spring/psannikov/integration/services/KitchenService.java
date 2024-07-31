package ru.otus.spring.psannikov.integration.services;

import ru.otus.spring.psannikov.integration.domain.Food;
import ru.otus.spring.psannikov.integration.domain.OrderItem;

public interface KitchenService {

	Food cook(OrderItem orderItem);
}

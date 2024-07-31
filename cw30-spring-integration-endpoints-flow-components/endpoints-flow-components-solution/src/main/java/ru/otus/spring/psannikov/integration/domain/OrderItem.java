package ru.otus.spring.psannikov.integration.domain;

public class OrderItem {

	private final String itemName;

	public OrderItem(String itemName) {
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}
}

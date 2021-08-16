package com.ibm.order.service;

import java.util.List;

import com.ibm.order.model.Order;
import com.ibm.order.model.OrderInput;

public interface OrderService {
	
	public Order getOrder(String orderNumber);
	public List<Order> getOrders();
	public Order addOrder(OrderInput orderInput);
}

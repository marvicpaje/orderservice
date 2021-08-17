package com.ibm.order.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.order.endpoint.MenuEndpoint;
import com.ibm.order.message.producer.OrderMessageProducer;
import com.ibm.order.model.MenuItem;
import com.ibm.order.model.Order;
import com.ibm.order.model.OrderInput;
import com.ibm.order.model.OrderInputMenuItem;
import com.ibm.order.model.OrderMenuItem;
import com.ibm.order.repo.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService {
	
	private final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private MenuEndpoint menuEndpoint;
	
	@Autowired
	OrderMessageProducer orderMessageProducer;
	
	@Override
	public Order getOrder(String orderNumber) {
		
		logger.info("Entered OrderServiceImpl.getOrder().  orderNumber={}", orderNumber);
		
		Order order = null;
		
		order = this.orderRepo.findByOrderNumber(orderNumber);
		
		logger.info("Leaving OrderServiceImpl.getOrder().  order={}", order);
		
		return order;
	}
	
	@Override
	public List<Order> getOrders() {
		logger.info("Entered OrderServiceImpl.getOrders().");
		List<Order> orders = null;
		
		orders = this.orderRepo.findAll();
		logger.info("Leaving OrderServiceImpl.getOrders().");
		return orders;
	}
	
	@Override
	public Order addOrder(OrderInput orderInput) {
		logger.info("Entered OrderServiceImpl.addOrder(). orderInput={}", orderInput);
		Order order = new Order();
		List<OrderMenuItem> orderMenuItems = new ArrayList<OrderMenuItem>();
		order.setOrderMenuItems(orderMenuItems);
		
		order.setCustomerNumber(orderInput.getCustomerNumber());
		OrderInputMenuItem orderInputMenuItem = null;
		OrderMenuItem orderMenuItem = null;

		MenuItem menuItem = null;

		double orderPrice = 0.0;
		DecimalFormat df2 = new DecimalFormat("#.##");
		Iterator<OrderInputMenuItem> iter = orderInput.getOrderInputMenuItems().iterator();
		while (iter.hasNext()) {
			
			orderInputMenuItem = iter.next();
			logger.debug("OrderServiceImpl.addOrder(). orderInputMenuItem={}", orderInputMenuItem);
			menuItem = this.menuEndpoint.getMenuItem(orderInputMenuItem.getMenuItemNumber());
	
			orderMenuItem = new OrderMenuItem();
			orderMenuItem.setMenuItemNumber(orderInputMenuItem.getMenuItemNumber());
			orderMenuItem.setMenuName(menuItem.getName());
			orderMenuItem.setQuantityOfMenuItem(orderInputMenuItem.getQuantityOfMenuItem());
			orderMenuItem.setPrice(menuItem.getPrice());
			
			orderMenuItems.add(orderMenuItem);
			
			orderPrice = orderPrice + menuItem.getPrice() * (orderInputMenuItem.getQuantityOfMenuItem());
		}

		orderPrice = Double.valueOf(df2.format(orderPrice));
		logger.debug("OrderServiceImpl.addOrder(). orderPrice={}", orderPrice);
		order.setOrderPrice(orderPrice);
				
		order.setOrderNumber(orderInput.getOrderNumber());
		
		// Insert order in mongodb
		this.orderRepo.insert(order);
		
		// Send event that order was added.
		this.sendOrderAddedEvent(order);
		
		logger.info("Leaving OrderServiceImpl.addOrder(). order={}", order);
		return order;
	}
	
	private void sendOrderAddedEvent(Order order) {

		try {
			this.orderMessageProducer.sendMessage(new ObjectMapper().writeValueAsString(order));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
}

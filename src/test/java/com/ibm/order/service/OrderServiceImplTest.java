package com.ibm.order.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.ibm.order.endpoint.MenuEndpoint;
import com.ibm.order.message.producer.OrderMessageProducer;
import com.ibm.order.model.MenuItem;
import com.ibm.order.model.Order;
import com.ibm.order.model.OrderInput;
import com.ibm.order.model.OrderInputMenuItem;
import com.ibm.order.repo.OrderRepo;

public class OrderServiceImplTest {

	@Mock
	MenuEndpoint menuEndpoint;

	@Mock
	OrderRepo orderRepo;

	@Mock
	OrderMessageProducer orderMessageProducer;

	@InjectMocks
	OrderServiceImpl orderService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@DisplayName("Test #1 - Validate Order Price during AddOrder")
	@Test
	void testOrderPriceAtAddOrder() {
		// 1st menu item
		String menuItemNumber1 = "123";
		MenuItem menuItem1 = new MenuItem(menuItemNumber1, "Seafood", "Salmon", "Baked Salmon", 10, 5.0);
		when(menuEndpoint.getMenuItem(menuItemNumber1)).thenReturn(menuItem1);

		// 2nd menu item
		String menuItemNumber2 = "345";
		MenuItem menuItem2 = new MenuItem(menuItemNumber2, "Veggies", "Salad", "Caesar Salad", 100, 2.0);
		when(menuEndpoint.getMenuItem(menuItemNumber2)).thenReturn(menuItem2);

		// mock for Order Repo
		// This when statement will mock orderRepo's insert method to
		// return the object passed to it
		when(orderRepo.insert(Mockito.any(Order.class))).thenAnswer(new Answer<Order>() {
			@Override
			public Order answer(InvocationOnMock invocation) {
				return invocation.getArgument(0, Order.class);
			}
		});

		// Prepare the OrderInput
		List<OrderInputMenuItem> orderInputMenuItem = new ArrayList<OrderInputMenuItem>();
		OrderInputMenuItem item1 = new OrderInputMenuItem("123", 3);
		OrderInputMenuItem item2 = new OrderInputMenuItem("345", 5);
		orderInputMenuItem.add(item1);
		orderInputMenuItem.add(item2);
		OrderInput orderInput = new OrderInput("CUST123", orderInputMenuItem);

		// Call the addOrder from the Order Service.
		Order order = orderService.addOrder(orderInput);

		assertEquals(order.getOrderPrice(), 25.0, "Order Price is incorrect");
		verify(menuEndpoint).getMenuItem(menuItemNumber1);
	}

}

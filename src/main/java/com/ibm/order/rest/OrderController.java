package com.ibm.order.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.order.model.Order;
import com.ibm.order.model.OrderInput;
import com.ibm.order.service.OrderService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);

	// URL: http://ip-address:port/order/order (POST request)
	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public ResponseEntity<?> addOrder(@RequestBody OrderInput orderInput) {

		Order order = this.orderService.addOrder(orderInput);
		ResponseEntity<Order> responseEntity = new ResponseEntity<Order>(order, HttpStatus.OK);
		return responseEntity;
	}

	// URL: http://ip-address:port/order/order/O123 (GET request)
	@RequestMapping(value = "/order/{orderNumber}")
	public ResponseEntity<?> getOrder(@PathVariable(value="orderNumber") String orderNumber) {

		logger.info("Entered OrderController.getOrder().  orderNumber={}", orderNumber);
		Order order = this.orderService.getOrder(orderNumber);
		logger.debug("OrderController.getOrder().  order={}", order);
		ResponseEntity<Order> responseEntity = new ResponseEntity<Order>(order, HttpStatus.OK);
		return responseEntity;
   }
   
   // URL: http://ip-address:port/order/orders (GET request)
   @RequestMapping(value = "/orders")
   public ResponseEntity<?> getOrders() {
	   
	   List<Order> orders = this.orderService.getOrders();
	   ResponseEntity<List<Order>> responseEntity = new ResponseEntity<List<Order>>(orders, HttpStatus.OK);
	   return responseEntity;
   }
}
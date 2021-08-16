package com.ibm.order.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ibm.order.model.MenuItem;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Component
public class MenuEndpointImpl implements MenuEndpoint {

	private final Logger logger = LoggerFactory.getLogger(MenuEndpointImpl.class);

	private RestTemplate restTemplate = new RestTemplate();

	@Value("${menu.endpoint}")
	private String menuServiceEndpoint;

	public MenuEndpointImpl() {
		this.restTemplate = new RestTemplate();
	}

	@Override
	@HystrixCommand(fallbackMethod = "getMenuItem_fallBack", commandKey = "endpointGetMenuItem", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "40000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "75") })
	public MenuItem getMenuItem(String menuItemNumber) {

		MenuItem menuItem = null;

		String menuServiceURL = "http://" + menuServiceEndpoint + "/menu/menu/" + menuItemNumber;
		menuItem = this.restTemplate.getForObject(menuServiceURL, MenuItem.class);

		return menuItem;
	}

	public MenuItem getMenuItem_fallBack(String menuItemNumber) {
		logger.warn("!!!!!! IN FALLBACK: getMenuItem_fallBack menuItemNumber:{} !!!!!!", menuItemNumber);
		MenuItem menuItem = new MenuItem("M123", "Entree Fallback", "FallBack Salmon Cuisine",
				"Salmon with fallback vegetables and rice", 8, 10.00);
		return menuItem;
	}
}

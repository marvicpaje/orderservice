package com.ibm.library.cucumber;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.springframework.web.client.RestTemplate;

import com.ibm.order.model.Order;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {

	private Order order;
	private RestTemplate restTemplate;

	public CucumberStepDefinitions() {
		this.restTemplate = new RestTemplate();
	}
	
	// Scenario #1
	@Given("^Order and Menu services are running and Order's db has orderNumber, (.*?), in it$")
	public void orderRunningOrderNumberInOrderDb(String orderNumber) throws Exception {
	}

	@When("^The Order microservice receives a request for orderNumber, (.*?)$")
	public void orderReceivesGetOrderRequest(String orderNumber) throws IOException {
		String orderServiceRESTRequest = "http://localhost:9743/order/order/" + orderNumber;
		this.order = this.restTemplate.getForObject(orderServiceRESTRequest, Order.class);
	}

	@Then("^The Order microservice should return order information for orderNumber, (.*?)$")
	public void orderGetOrderReturnsOrder(String orderNumber) {
		assertNotNull("order should not be null", this.order);
	}

}

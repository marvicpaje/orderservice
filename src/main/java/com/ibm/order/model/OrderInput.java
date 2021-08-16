package com.ibm.order.model;

import java.util.List;

public class OrderInput {

	private String orderNumber;
	private String customerNumber;
	private List<OrderInputMenuItem> orderInputMenuItems;
	
	public OrderInput() {
		super();
	}
	
	public OrderInput(String customerNumber, List<OrderInputMenuItem> orderInputMenuItems) {
		super();
		this.customerNumber = customerNumber;
		this.orderInputMenuItems = orderInputMenuItems;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public List<OrderInputMenuItem> getOrderInputMenuItems() {
		return orderInputMenuItems;
	}

	public void setOrderInputMenuItems(List<OrderInputMenuItem> orderInputMenuItems) {
		this.orderInputMenuItems = orderInputMenuItems;
	}

	@Override
	public String toString() {
		return "OrderInput [orderNumber=" + orderNumber + ", customerNumber=" + customerNumber
				+ ", orderInputMenuItems=" + orderInputMenuItems + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderNumber == null) ? 0 : orderNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderInput other = (OrderInput) obj;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

}

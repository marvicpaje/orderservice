package com.ibm.order.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "Order")
public class Order {
	@JsonIgnore
	@Id
	private ObjectId _id;

	private String customerNumber;
	private String orderNumber;
	private List<OrderMenuItem> orderMenuItems;
	
	private double orderPrice;
	
	public Order() {
		super();
	}
	
	public Order(String customerNumber, List<OrderMenuItem> orderMenuItems, double orderPrice) {
		super();
		this.customerNumber = customerNumber;
		this.orderMenuItems = orderMenuItems;
		this.orderPrice = orderPrice;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public List<OrderMenuItem> getOrderMenuItems() {
		return orderMenuItems;
	}

	public void setOrderMenuItems(List<OrderMenuItem> orderMenuItems) {
		this.orderMenuItems = orderMenuItems;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
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
		Order other = (Order) obj;
		if (orderNumber == null) {
			if (other.orderNumber != null)
				return false;
		} else if (!orderNumber.equals(other.orderNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [_id=" + _id + ", customerNumber=" + customerNumber + ", orderNumber=" + orderNumber
				+ ", orderMenuItems=" + orderMenuItems + ", orderPrice=" + orderPrice + "]";
	}
}

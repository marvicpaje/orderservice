package com.ibm.order.repo;

import com.ibm.order.model.Order;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends MongoRepository<Order, Long> 
{
	public Order findByOrderNumber(String orderNumber);
}
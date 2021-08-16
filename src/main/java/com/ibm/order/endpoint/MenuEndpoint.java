package com.ibm.order.endpoint;

import com.ibm.order.model.MenuItem;

public interface MenuEndpoint {

	public MenuItem getMenuItem(String menuItemNumber);
}

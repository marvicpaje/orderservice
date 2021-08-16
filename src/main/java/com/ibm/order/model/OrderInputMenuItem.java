package com.ibm.order.model;

public class OrderInputMenuItem {
	
	private String menuItemNumber;
	private int quantityOfMenuItem;
	
	public OrderInputMenuItem() {
		super();
	}
	
	public OrderInputMenuItem(String menuItemNumber, int quantityOfMenuItem) {
		super();
		this.menuItemNumber = menuItemNumber;
		this.quantityOfMenuItem = quantityOfMenuItem;
	}
	
	public String getMenuItemNumber() {
		return menuItemNumber;
	}
	public void setMenuItemNumber(String menuItemNumber) {
		this.menuItemNumber = menuItemNumber;
	}
	public int getQuantityOfMenuItem() {
		return quantityOfMenuItem;
	}
	public void setQuantityOfMenuItem(int quantityOfMenuItem) {
		this.quantityOfMenuItem = quantityOfMenuItem;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuItemNumber == null) ? 0 : menuItemNumber.hashCode());
		result = prime * result + quantityOfMenuItem;
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
		OrderInputMenuItem other = (OrderInputMenuItem) obj;
		if (menuItemNumber == null) {
			if (other.menuItemNumber != null)
				return false;
		} else if (!menuItemNumber.equals(other.menuItemNumber))
			return false;
		if (quantityOfMenuItem != other.quantityOfMenuItem)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderInputMenuItem [menuItemNumber=" + menuItemNumber + ", quantityOfMenuItem=" + quantityOfMenuItem
				+ "]";
	}
}

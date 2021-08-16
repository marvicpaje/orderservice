package com.ibm.order.model;

public class OrderMenuItem {

	private String menuItemNumber;
	private int quantityOfMenuItem;
	private String menuName;
	private double price;
	
	public OrderMenuItem() {
		super();
	}
	
	public OrderMenuItem(String menuItemNumber, int quantityOfMenuItem, String menuName, double price) {
		super();
		this.menuItemNumber = menuItemNumber;
		this.quantityOfMenuItem = quantityOfMenuItem;
		this.menuName = menuName;
		this.price = price;
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
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((menuItemNumber == null) ? 0 : menuItemNumber.hashCode());
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
		OrderMenuItem other = (OrderMenuItem) obj;
		if (menuItemNumber == null) {
			if (other.menuItemNumber != null)
				return false;
		} else if (!menuItemNumber.equals(other.menuItemNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderMenuItem [menuItemNumber=" + menuItemNumber + ", quantityOfMenuItem=" + quantityOfMenuItem
				+ ", menuName=" + menuName + ", price=" + price + "]";
	}
}

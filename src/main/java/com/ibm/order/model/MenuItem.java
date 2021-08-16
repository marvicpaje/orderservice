package com.ibm.order.model;

public class MenuItem {

	private String menuItemNumber;
	private String category;
	private String name;
	private String description;
	private int numAvailable;
	private double price;
	
	public MenuItem() {
		super();
	}
	
	public MenuItem(String menuItemNumber, String category, String name, String description, int numAvailable,
			double price) {
		super();
		this.menuItemNumber = menuItemNumber;
		this.category = category;
		this.name = name;
		this.description = description;
		this.numAvailable = numAvailable;
		this.price = price;
	}
	
	public String getMenuItemNumber() {
		return menuItemNumber;
	}
	public void setMenuItemNumber(String menuItemNumber) {
		this.menuItemNumber = menuItemNumber;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getNumAvailable() {
		return numAvailable;
	}
	public void setNumAvailable(int numAvailable) {
		this.numAvailable = numAvailable;
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
		MenuItem other = (MenuItem) obj;
		if (menuItemNumber == null) {
			if (other.menuItemNumber != null)
				return false;
		} else if (!menuItemNumber.equals(other.menuItemNumber))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MenuItem [menuItemNumber=" + menuItemNumber + ", category=" + category + ", name=" + name
				+ ", description=" + description + ", numAvailable=" + numAvailable + ", price=" + price + "]";
	}
	
}
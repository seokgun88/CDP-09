package com.test.second.object;

public class MenuObj {
	String Menu_str;
	String Price;
	
	public String getMenu_str() {
		return Menu_str;
	}
	public String getPrice() {
		return Price;
	}
	public void setMenu_str(String menu_str) {
		Menu_str = menu_str;
	}
	public void setPrice(String price) {
		Price = price;
	}
	@Override
	public String toString() {
		return getMenu_str() + " " + getPrice() + "\n";
	}
	
}

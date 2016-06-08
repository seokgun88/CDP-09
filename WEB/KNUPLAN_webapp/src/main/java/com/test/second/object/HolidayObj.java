package com.test.second.object;

public class HolidayObj {
	private String date;
	private String name;
	
	public void setDate(String date) {
		this.date = date;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {

		return "Date : " + getDate() + " Name : "+ getName();
	}
}

package com.test.second.object;

public class CalendarObj {
	private String title;
	private String start;
	
	public CalendarObj() {
		// TODO Auto-generated constructor stub
		title = "";
		start = "";
	}
	
	public void setStart(String start) {
		this.start = start;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTitle() +","+ getStart();
	}

}

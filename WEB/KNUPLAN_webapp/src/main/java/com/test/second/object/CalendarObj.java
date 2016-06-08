package com.test.second.object;

public class CalendarObj {
	private String title;
	private String start;
	private String end;

	public CalendarObj() {
		// TODO Auto-generated constructor stub
		title = "";
		start = "";
		end = "";
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

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTitle() + "," + getStart() + "," + getEnd();
	}

}

package com.test.second.object;

import java.util.*;

//강의실 시간표를 db에 저장할 형식 즉 dbo
public class ClassroomScheduleObj {
	String building;
	String room;
	int day;
	int start;
	int end;
	String schedule;
	
	public ClassroomScheduleObj() {
		// TODO Auto-generated constructor stub
		building = "";
		room = "";
		day = 0;
		start = 0;
		end = 0;
		schedule = "";
	}
	
	public ClassroomScheduleObj(String building , String room ,int day ,int start, int end, String schedule){
		this.building = building;
		this.room = room;
		this.day = day;
		this.start = start;
		this.end = end;
		this.schedule = schedule;
		System.out.println(toString());
	}

	@Override
	public String toString() {
		return "ClassroomScheduleObj [building=" + building + ", room=" + room + ", day=" + day + ", start=" + start+ ", end=" + end
				+ ", schedule=" + schedule +"]";
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}


	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
}

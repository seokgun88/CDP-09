package com.test.second.object;

public class ScheduleAttr {
	//String []daylist = { "시간", "월", "화","수","목","금","토","일" };
	String time;
	String []day = new String[7];
	String monday;
	String tuesday;
	String wednesday;
	String thirsday;
	String friday;
	String saturday;
	String sunday;
	
	public void setDay(String[] day) {
		this.day = day;
	}
	public String[] getDay() {
		return day;
	}
	
	public ScheduleAttr(String time,String monday,String tuesday,String wednesday,
			String thirsday,String friday,String saturday,String sunday) {
		this.time = time;
		day[0] = sunday;
		day[1] = monday;
		day[2] = tuesday;
		day[3] = wednesday;
		day[4] = thirsday;
		day[5] = friday;
		day[6] = saturday;
		
		this.monday = monday;
		this.tuesday = tuesday;
		this.wednesday = wednesday;
		this.thirsday = thirsday;
		this.friday = friday;
		this.saturday = saturday;
		this.sunday = sunday;		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%s] 월 : %s 화: %s 수: %s 목: %s 금: %s 토: %s 일: %s", time, monday, tuesday, wednesday,
				thirsday,friday,saturday,sunday);
	}
	public void setThirsday(String thirsday) {
		this.thirsday = thirsday;
	}
	public void setFriday(String friday) {
		this.friday = friday;
	}
	public void setMonday(String monday) {
		this.monday = monday;
	}
	public void setSaturday(String saturday) {
		this.saturday = saturday;
	}
	public void setSunday(String sunday) {
		this.sunday = sunday;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setTuesday(String tuesday) {
		this.tuesday = tuesday;
	}
	public void setWednesday(String wednesday) {
		this.wednesday = wednesday;
	}
	
	
	public String getFriday() {
		return friday;
	}
	public String getMonday() {
		return monday;
	}
	public String getSaturday() {
		return saturday;
	}
	public String getSunday() {
		return sunday;
	}
	public String getThirsday() {
		return thirsday;
	}
	public String getTime() {
		return time;
	}
	public String getTuesday() {
		return tuesday;
	}
	public String getWednesday() {
		return wednesday;
	}
	
}
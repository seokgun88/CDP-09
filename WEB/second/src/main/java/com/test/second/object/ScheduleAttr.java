package com.test.second.object;

public class ScheduleAttr {
	//String []daylist = { "�ð�", "��", "ȭ","��","��","��","��","��" };
	String time;
	String monday;
	String tuesday;
	String wednesday;
	String thirsday;
	String friday;
	String saturday;
	String sunday;
	
	public ScheduleAttr(String time,String monday,String tuesday,String wednesday,
			String thirsday,String friday,String saturday,String sunday) {
		this.time = time;
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
		return String.format("[%s] �� : %s ȭ: %s ��: %s ��: %s ��: %s ��: %s ��: %s", time, monday, tuesday, wednesday,
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
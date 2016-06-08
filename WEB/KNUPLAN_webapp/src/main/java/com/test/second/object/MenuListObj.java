package com.test.second.object;

import java.util.ArrayList;

public class MenuListObj {
	private String buildname;
	private boolean breakfast;
	private boolean lunch;
	private boolean dinner;
	private ArrayList<MenuObj> breakfast_MenuList;
	private ArrayList<MenuObj> lunch_MenuList;
	private ArrayList<MenuObj> dinner_MenuList;
	
	public MenuListObj(){
		this.breakfast = false;
		this.lunch = false;
		this.dinner = false;		
	}
	
	public MenuListObj(String buildname,
			boolean breakfast ,ArrayList<MenuObj> breakfast_MenuList,
			boolean lunch ,ArrayList<MenuObj> lunch_MenuList,
			boolean dinner ,ArrayList<MenuObj> dinner_MenuList
			) {
		this.buildname = buildname;
		this.breakfast = false;
		this.lunch = false;
		this.dinner = false;
		this.breakfast_MenuList = breakfast_MenuList;		
		this.lunch_MenuList = lunch_MenuList;		
		this.dinner_MenuList = dinner_MenuList;
	}	
	
	public void setBuildname(String buildname) {
		this.buildname = buildname;
	}
	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}
	public void setBreakfast_MenuList(ArrayList<MenuObj> breakfast_MenuList) {
		this.breakfast_MenuList = breakfast_MenuList;
	}
	public void setLunch(boolean lunch) {
		this.lunch = lunch;
	}
	public void setLunch_MenuList(ArrayList<MenuObj> lunch_MenuList) {
		this.lunch_MenuList = lunch_MenuList;
	}
	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}
	public void setDinner_MenuList(ArrayList<MenuObj> dinner_MenuList) {
		this.dinner_MenuList = dinner_MenuList;
	}
	
	public String getBuildname() {
		return buildname;
	}
	public ArrayList<MenuObj> getBreakfast_MenuList() {
		return breakfast_MenuList;
	}
	public ArrayList<MenuObj> getDinner_MenuList() {
		return dinner_MenuList;
	}	
	public ArrayList<MenuObj> getLunch_MenuList() {
		return lunch_MenuList;
	}
	
	public boolean getBreakfast(){
		return breakfast;
	}
	public boolean getLunch(){
		return lunch;
	}
	public boolean getDinner(){
		return dinner;
	}

	@Override
	public String toString() { 
		return getBuildname()+ "\n아침 : " + getBreakfast() + getBreakfast_MenuList() + "\n점심 : " + getLunch()+ getLunch_MenuList()
		+ "\n저녁 : " + getDinner() + getDinner_MenuList();
	}
}

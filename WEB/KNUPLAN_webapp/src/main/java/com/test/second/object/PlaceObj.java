package com.test.second.object;

import java.util.ArrayList;

public class PlaceObj {
	int Num;
	String PlaceName;
	String PlaceNum;
	ArrayList<ClassObj> ClassList;

	public PlaceObj(int Num, String PlaceName,String PlaceNum, ArrayList<ClassObj> ClassList){
		this.Num = Num;
		this.PlaceName = PlaceName;
		this.PlaceNum = PlaceNum;
		this.ClassList = ClassList;
	}
	
	public void setNum(int num) {
		Num = num;
	}
	public void setPlaceName(String placeName) {
		PlaceName = placeName;
	}
	public void setPlaceNum(String placeNum) {
		PlaceNum = placeNum;
	}
	public void setClassList(ArrayList<ClassObj> classList) {
		ClassList = classList;
	}
	public int getNum() {
		return Num;
	}
	public String getPlaceName() {
		return PlaceName;
	}
	public String getPlaceNum() {
		return PlaceNum;
	}
	public ArrayList<ClassObj> getClassList() {
		return ClassList;
	}
	
	@Override
	public String toString() {
		return getNum()+"/"+getPlaceName()+"/"+getPlaceNum()+"/"+getClassList().toString();
	}

}

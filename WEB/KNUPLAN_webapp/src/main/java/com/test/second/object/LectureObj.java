package com.test.second.object;

import java.util.*;
import java.util.regex.*;

public class LectureObj {
	//교양 인덱스 : 2 , 3 , 4 , 8 , 9 , 10
	int num;
	String reg_college;
	String subject_number;
	String subject_name;	
	String professor;
	String time;
	String place;
	String LectureExplainUrl;
	
		
	public LectureObj(int num,String reg_college, String subject_number, String subject_name,
			String professor, String time, String place){
		this.num = num;
		this.reg_college = reg_college;
		this.subject_number = subject_number;
		this.subject_name = subject_name;
		this.professor = professor;
		this.time = time;
		this.place = place;		
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("[%d] %s %s %s %s %s %s %s", num,reg_college,
				subject_number,subject_name,professor,time,place,LectureExplainUrl);
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	public void setProfessor(String professor) {
		this.professor = professor;
	}
	public void setReg_college(String reg_college) {
		this.reg_college = reg_college;
	}
	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}
	public void setSubject_number(String subject_number) {
		this.subject_number = subject_number;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setLectureExplainUrl(String lectureExplainUrl) {
		LectureExplainUrl = lectureExplainUrl;
	}
	
	public String getPlace() {
		return place;
	}
	public String getProfessor() {
		return professor;
	}
	public String getReg_college() {
		return reg_college;
	}
	public String getSubject_name() {
		return subject_name;
	}
	public String getSubject_number() {
		return subject_number;
	}
	public String getTime() {
		return time;
	}
	public String getLectureExplainUrl() {
		return LectureExplainUrl;
	}
	
}

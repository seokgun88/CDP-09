package com.test.second.object;

public class ClassObj {
	String Time;
	String Professor;
	String Subject;
	String SubjectNum;
	String reg_college;
	String Link;
	
	public void setReg_college(String reg_college) {
		this.reg_college = reg_college;
	}
	public void setLink(String link) {
		Link = link;
	}
	public void setProfessor(String professor) {
		Professor = professor;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public void setSubjectNum(String subjectNum) {
		SubjectNum = subjectNum;
	}
	public void setTime(String time) {
		Time = time;
	}
	
	public String getReg_college() {
		return reg_college;
	}
	public String getLink() {
		return Link;
	}
	public String getProfessor() {
		return Professor;
	}
	public String getSubject() {
		return Subject;
	}
	public String getSubjectNum() {
		return SubjectNum;
	}
	public String getTime() {
		return Time;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getTime()+"/"+getProfessor()+"/"+getSubject()+"/"+getSubjectNum()+"/"+getReg_college()+"/"+getLink();
	}

}

package object;

public class CollegeObj {
	private String date;
	private String name;
	
	public String getDate() {
		return date;
	}
	public String getName() {
		return name;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
	
		return "Date : " + getDate() + " Name : "+ getName();
	}

}

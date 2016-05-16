import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.nodes.Element;

import object.CollegeObj;
import object.HolidayObj;
import object.LectureObj;
import object.ScheduleAttr;
import parser.CollegePlan;
import parser.HolidayPlan;
import parser.LecturePlan;
import parser.UserSchedulePlan;

// TEST

public class Test {

	public static void main(String[] args) {

		//				-----------------------------------------------
		//				[*]������ �Ľ�

		/*for(int n=1; n <= 12 ; n++){
					System.out.println("month : " + n);
					HolidayPlan obj = new HolidayPlan(2016,n);
					ArrayList<HolidayObj> HolidayList = obj.getHolidayList();

					for(int i=0 ; i < HolidayList.size() ; i++){			
						System.out.println("[List] "+HolidayList.get(i).toString());
					}
				}*/

		//		-----------------------------------------------
		//		[*]�л� ���� �Ľ�

		//				CollegePlan collobj = new CollegePlan(2016);
		//
		//				ArrayList<CollegeObj> CollegeList = collobj.getCollegeList();
		//				for(CollegeObj e: CollegeList){
		//					System.out.println("[List] "+ e.toString());		
		//				}

		//		-----------------------------------------------
		//		[*]���ǰ�ȹ�� �Ľ�

//		LecturePlan lp = new LecturePlan();
////		lp.TotalParse();
//		lp.SectionParse(1);
//		ArrayList<LectureObj> LectureList = lp.getLectureList();
//
//		for(LectureObj e: LectureList){
//			System.out.println("[List] "+ e.toString());
//		}

//		-----------------------------------------
//		[*]���� ������ �Ľ�(lms)
		String id = "aa0507a";
		String pw = "+123qwe!!!@";
		UserSchedulePlan UserTime = new UserSchedulePlan();
		
		String temp = "";
		for(int i=0;i<pw.length() ; i++){
			char c = pw.charAt(i);
			if(c == '+'||c == '='||c == '-'||c == '/'||c == '*'){
				temp += "%"+String.valueOf(c);				
			}
			temp += String.valueOf(c);
		}
		System.out.println(temp);

		if(UserTime.StartRequest(id, temp) == false){
			System.out.println("Request ���� !!");
		}
		else{
			System.out.println("Request ���� !!");
			ArrayList<ScheduleAttr> attrList = UserTime.getScheduleList();

			for(int i = 0 ;i<attrList.size();i++){			
				System.out.println(attrList.get(i).toString());
			}
		}
		
//		System.out.println("**************************");
//		PlaceManage PM = new PlaceManage();


	}

}

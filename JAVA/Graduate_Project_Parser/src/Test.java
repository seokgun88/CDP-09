import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.jsoup.nodes.Element;

import object.CollegeObj;
import object.HolidayObj;
import object.LectureObj;
import parser.CollegePlan;
import parser.HolidayPlan;
import parser.LecturePlan;

public class Test {

	public static void main(String[] args) {
		
//		for(int n=1; n <= 12 ; n++){
//			System.out.println("month : " + n);
//			HolidayPlan obj = new HolidayPlan(2016,n);
//			ArrayList<HolidayObj> HolidayList = obj.getHolidayList();
//
//			for(int i=0 ; i < HolidayList.size() ; i++){			
//				System.out.println("[List] "+HolidayList.get(i).toString());
//			}
//		}
		
//		-----------------------------------------------
		
//		CollegePlan collobj = new CollegePlan(2016);


//		ArrayList<CollegeObj> CollegeList = collobj.getCollegeList();
//		for(CollegeObj e: CollegeList){
//			System.out.println("[List] "+ e.toString());		
//		}
		
//		-----------------------------------------------
		
		BufferedWriter write = null;
		
		try {		
			write = new BufferedWriter(new FileWriter("output.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		LecturePlan lp = new LecturePlan();
		lp.TotalParse();
		//lp.SectionParse(1);
		ArrayList<LectureObj> LectureList = lp.getLectureList();
		
		for(LectureObj e: LectureList){
			System.out.println("[List] "+ e.toString());
			try {
				write.write("[List] "+ e.toString() + "\n" );
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		try {
			write.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

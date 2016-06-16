package com.test.second;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.test.second.object.CalendarObj;
import com.test.second.object.ScheduleAttr;

public class TimetableManage {
	public ArrayList<CalendarObj> calList;

	public String ConvertTimesymbol(String time){
		String result ="";

		// 0A : 08:00~08:30
		// 0B : 08:30~09:00		
		for(int i=0;i<=18;i++){
			if(time.equals(Integer.toString(i)+"A") == true){
				result = String.format("%02d:00:00~%02d:30:00",i+8,i+8 );
				break;
			}
			else if(time.equals(Integer.toString(i)+"B") == true){
				result = String.format("%02d:30:00~%02d:00:00",i+8,i+9 );
				break;
			}
		}						
		return result;
	}

	public ArrayList<CalendarObj> getCalList(ArrayList<ScheduleAttr> scheduleList) {
		calList = new ArrayList<CalendarObj>();

		// 초기화
		boolean []lstflag = new boolean[7];
		String []lsttempstr = new String[7];
		String []lststarttime = new String[7];
		String []lstendtime = new String[7];
		CalendarObj[] lstcalobj = new CalendarObj[7];
		for(int j=0; j<7;j++){
			lstflag[j] = false;
			lsttempstr[j] = "null";
			lststarttime[j] = "";
			lstendtime[j] = "";
			lstcalobj[j] = new CalendarObj();
		}

		ArrayList<String> timestr = new ArrayList<String>(); 

		for(int i=0; i< scheduleList.size();i++){
			String timesym = scheduleList.get(i).getTime();
			timestr.add(ConvertTimesymbol(timesym));			
		}

		CalendarObj calobj = new CalendarObj();

		for(int i=0; i< scheduleList.size();i++){
			String []day = scheduleList.get(i).getDay();

			for(int j=0; j<7;j++){
				if(lsttempstr[j].equals(day[j])==true){
					lstflag[j] = true;
				}
				else if(lstflag[j] == true){
					lstendtime[j] = timestr.get(i-1);				
					lstcalobj[j].setStart(Integer.toString(j+1)+"/"+lststarttime[j].substring(0,8));
					lstcalobj[j].setEnd(lstendtime[j].substring(9));
					calList.add(lstcalobj[j]);
//					System.out.println(lstcalobj[j]);
					lstcalobj[j] = new CalendarObj();
					lstflag[j] = false;				
				}			

				if(day[j].equals("") == false && lstflag[j] == false){
					lsttempstr[j] = day[j];
					lststarttime[j] = timestr.get(i);
					lstcalobj[j].setTitle(lsttempstr[j]);
				}
			}
		}

//		for(CalendarObj e : calList){
//			System.out.println(e.toString());
//		}

		return calList;
	}

	/**
	 * 특정 날짜에 대하여 요일을 구함(일 ~ 토)
	 * @param date
	 * @param dateType
	 * @return
	 * @throws Exception
	 */
	public int getDateDay(String date, String dateType){

		SimpleDateFormat dateFormat = new SimpleDateFormat(dateType) ;
		Date nDate;
		int dayNum = -1;
		try {
			nDate = dateFormat.parse(date);
			Calendar cal = Calendar.getInstance() ;
			cal.setTime(nDate);
			dayNum = cal.get(Calendar.DAY_OF_WEEK);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dayNum ;
	}

}
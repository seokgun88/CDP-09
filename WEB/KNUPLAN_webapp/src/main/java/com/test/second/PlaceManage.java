package com.test.second;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.test.second.object.ClassObj;
import com.test.second.object.LectureObj;
import com.test.second.object.PlaceObj;
import com.test.second.parser.LecturePlan;


public class PlaceManage {
	private ArrayList<LectureObj> LectureList;
	private ArrayList<PlaceObj> PlaceList;

	public PlaceManage(){
		PlaceList = new ArrayList<PlaceObj>();
		getLecture();
		setPlace();
	}

	public ArrayList<PlaceObj> getElementPlaceList(String place) {

		ArrayList<PlaceObj> result = new ArrayList<PlaceObj>();

		for(PlaceObj e: PlaceList){
			System.out.println(e);
			if(e.getPlaceName().equals(place)){
				result.add(e);
			}
		}

		return result;
	}

	public void setPlace(){		
		HashMap<String, ArrayList<ClassObj>> map = new HashMap<String, ArrayList<ClassObj>>();
		ArrayList<ClassObj> tempClassList = null;

		for(int i=0;i<LectureList.size();i++){

			LectureObj e = LectureList.get(i);
			String pl = e.getPlace();
			if(pl.contains("상주캠퍼스")){
				continue;
			}
			if(pl.equals("-")){
				continue;
			}

			tempClassList = map.get(pl);
			if(tempClassList == null){
				tempClassList = new ArrayList<ClassObj>();
			}

			ClassObj ClassE = new ClassObj();

			if(e.getNum() == 1){
				ClassE.setSubject(e.getSubject_name()+" 온라인강의");				
			}
			else{
				ClassE.setSubject(e.getSubject_name());
			}

			ClassE.setLink(e.getLectureExplainUrl());
			ClassE.setProfessor(e.getProfessor());
			ClassE.setReg_college(e.getReg_college());						
			ClassE.setSubjectNum(e.getSubject_number());
			ClassE.setTime(e.getTime());

			tempClassList.add(ClassE);

			map.put(pl, tempClassList);			

		}

		int cnt=1;
		for( String key : map.keySet() ){
			//        	System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
			String []Placearr = key.split("-");
			PlaceObj e;
			if(Placearr.length==1){            	
				e = new PlaceObj(cnt, Placearr[0], "NULL", map.get(key));            	
			}
			else{
				//            	System.out.println( String.format("키 : %s _ %s, 값 : %s", Placearr[0], Placearr[1], map.get(key)) );
				e = new PlaceObj(cnt, Placearr[0], Placearr[1], map.get(key));
			}

			PlaceList.add(e);
			cnt+=1;
		}
		PlaceObj c;

		Collections.sort(PlaceList, new Comparator<PlaceObj>(){
			public int compare(PlaceObj obj1, PlaceObj obj2)
			{

				return obj1.getPlaceNum().compareToIgnoreCase(obj2.getPlaceNum());            
			}
		}); 

		for(PlaceObj e: PlaceList){
			System.out.println(e);
		}
	}

	public void getLecture(){
		LecturePlan lp = new LecturePlan();

		//		for(int i=1;i<=57;i++){
		//			// 계절학기
		//			if(i==3) continue;
		//			lp.SectionParse(i);
		//		}	

		// IT대학 파싱		
		lp.SectionParse(33);
		LectureList = lp.getLectureList();

		//		for(LectureObj e: LectureList){
		//			System.out.println("[List] "+ e.toString());
		//		}

	}

}

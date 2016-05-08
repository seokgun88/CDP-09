

import java.util.ArrayList;
import java.util.HashMap;

import object.ClassObj;
import object.LectureObj;
import object.PlaceObj;
import parser.LecturePlan;


public class PlaceManage {
	private ArrayList<LectureObj> LectureList;
	private ArrayList<PlaceObj> PlaceList;
	
	public PlaceManage(){
		PlaceList = new ArrayList<PlaceObj>();
		getLecture();
		setPlace();
	}
	
	public ArrayList<PlaceObj> getPlaceList() {
		return PlaceList;
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
		
		//[*]
		HashMap<String, String> STRmap = new HashMap<String, String>();
		
		int cnt=1;
        for( String key : map.keySet() ){
//        	System.out.println( String.format("키 : %s, 값 : %s", key, map.get(key)) );
            String []Placearr = key.split("-");
            PlaceObj e;
            if(Placearr.length==1){            	
            	e = new PlaceObj(cnt, Placearr[0], "NULL", map.get(key));
            	STRmap.put(Placearr[0], "PLACE");
            }
            else{
//            	System.out.println( String.format("키 : %s _ %s, 값 : %s", Placearr[0], Placearr[1], map.get(key)) );
            	e = new PlaceObj(cnt, Placearr[0], Placearr[1], map.get(key));
            	STRmap.put(Placearr[0], "PLACE");
            }
                        
            PlaceList.add(e);
            cnt+=1;
        }
        
        for( String key : STRmap.keySet() ){
        	System.out.println( String.format("키 : %s, 값 : %s", key, STRmap.get(key)) );
        }
//        for(PlaceObj e: PlaceList){
//        	System.out.println(e);
//        }
	}
	
	public void getLecture(){
		LecturePlan lp = new LecturePlan();
		
		for(int i=1;i<=57;i++){
			// 계절학기
			if(i==3) continue;
			lp.SectionParse(i);
		}	
		
//		lp.SectionParse(1);
//		lp.SectionParse(2);
//		lp.SectionParse(4);
		LectureList = lp.getLectureList();
		
//		for(LectureObj e: LectureList){
//			System.out.println("[List] "+ e.toString());
//		}
		
	}

}

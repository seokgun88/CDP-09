package com.test.second.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.second.object.LectureObj;

public class LecturePlan {
	private ArrayList<LectureObj> LectureList;

	public ArrayList<LectureObj> getLectureList() {
		return LectureList;
	}

	public LecturePlan(){
		LectureList = new ArrayList<LectureObj>();
	}
	
	public void TotalParse(){
				
		for(int i=1;i<=57;i++){
			SectionParse(i);
		}		
	}
	
	// input : [1,57]
	// output : true �Ϸ� , false ����
	public boolean SectionParse(int n){
		
		if(1<=n&&n<=3){
			if(ParseEtc(n) == false){
				return false;
			}
		}
		else if(4<=n&&n<=57){
			if(ParseStart(n) == false){
				return false;
			}
		}
		
		return true;
	}

	// input : ����Ʈ���� 1, ���� 2, ���� 3,
	// outpu : tru ���� , false ����
	public boolean ParseEtc(int n){
		if( 1 > n && n > 3 ){			
			return false;
		}
		int offset = 1;

		// [*] td strong a[href]					
		Document doc = null;
		try {
			String url = "http://knu.ac.kr/wbbs/wbbs/contents/"
					+ "index.action?menu_url=curriculum2/index&noDeco=true";
			Connection con = Jsoup.connect(url);
			con.timeout(1000 * 60);
			doc = con.get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Elements refs = doc.select("tbody tbody tbody td strong a[href]");
		
		// ���� [0,2] ���� ��ȯ�� ����
		int start = n - offset;
		int end = n - offset;
		
		// �ε��� 3 �̻��� ������ 4��  ; �˻�, ���ǰ�ȹ��, �ǹ� �� ���������ڵ�, �����ð�
		for(int i=start;i<=end;i++){
			Element e = refs.get(i);

			String tempurl = e.attr("abs:href");
//			String tempname = e.text();
//			System.out.println(tempurl +"\n" + tempname + "\n");

			Document subcolldoc = null;
			try {						
				Connection con3 = Jsoup.connect(tempurl);
				con3.timeout(1000 * 60);
				subcolldoc = con3.get();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// ���� ���� ��ȹ�� �Ľ�
			Elements tables = subcolldoc.select("table.courTable tbody tr td");
			String []tempvalue = new String[13];
			int subcnt = 0;
			
			Elements lechrefs = subcolldoc.select("table.courTable tbody tr td a[href]");
			String temphref = "NULL";
			int hrefcnt = 0;

			//���� �ε��� : 2 , 3 , 7 , 8 , 9			
			for(Element ele: tables){			
				tempvalue[subcnt] = ele.text();
								
				subcnt+=1;

				//System.out.println( cnt +"td: " + tempvalue[cnt-1]);

				if( subcnt%13 == 0){
					//System.out.println("----------------");
					LectureObj Aobj = new LectureObj(i + offset,"NULL",tempvalue[2],tempvalue[3],
							tempvalue[7],tempvalue[8],tempvalue[9]);
					
					Element lechref = lechrefs.get(hrefcnt);
					temphref = lechref.attr("abs:href");					
					Aobj.setLectureExplainUrl(temphref);
					
					LectureList.add(Aobj);

//					System.out.println(Aobj.toString());
					
					subcnt = 0;
					hrefcnt+=1;
				}
			}
		}

		return true;		
	}

	// input : 4���� 57���� ����
	// output : false : ����,  true : ����
	public boolean ParseStart(int n){
		if( 4 > n && n > 57 ){
			return false;
		}	
		int offset = 4;

		Document doc = null;
		try {
			String url = "http://knu.ac.kr/wbbs/wbbs/contents/"
					+ "index.action?menu_url=curriculum2/index&noDeco=true";
			Connection con = Jsoup.connect(url);
			con.timeout(1000 * 60);
			doc = con.get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// ���а� ���п�
		//		Elements titles = doc.select("tbody tbody tbody span.style2");
		Elements refs = doc.select("tbody tbody tbody span.style2 a[href]");

		int start = n - offset;
		int end = n - offset;
		//���� [0, refs.size()) ���� ���� ��ȯ�� �����ϴ�.
		for(int i=start;i<=end;i++){
			Element e = refs.get(i);
			String ref = e.attr("abs:href");
//			System.out.println("refs: " + e.attr("abs:href"));
//			System.out.println("name: " + e.text());
			
			if(i<13){
				// ���� �������� �ѹ���				
				Document subdoc = null;
				try {					
					Connection con1 = Jsoup.connect(ref);
					con1.timeout(1000 * 60);
					subdoc = con1.get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// ���� ���� ��ȹ�� �Ľ�
				Elements tds = subdoc.select("table.courTable tbody tr td");
				String []tempvalue = new String[14];
				
				Elements lechrefs = subdoc.select("table.courTable tbody tr td a[href]");
				String temphref = "NULL";
				int hrefcnt = 0;
								
				int cnt = 0;
				//���� �ε��� : 2 , 3 , 4 , 8 , 9 , 10			
				for(Element ele: tds){			
					tempvalue[cnt] = ele.text();
					
					cnt+=1;					

					//System.out.println( cnt +"td: " + tempvalue[cnt-1]);

					if( cnt%14 == 0){
						//System.out.println("----------------");
						LectureObj Aobj = new LectureObj(i + offset,tempvalue[2],tempvalue[3],tempvalue[4],
								tempvalue[8],tempvalue[9],tempvalue[10]);
						
						Element lechref = lechrefs.get(hrefcnt);
						temphref = lechref.attr("abs:href");						
						Aobj.setLectureExplainUrl(temphref);
						
						LectureList.add(Aobj);

//						System.out.println(Aobj.toString());
						
						cnt = 0;
					}
				}
			}
			else{
				Document subdoc = null;
				try {					
					Connection con2 = Jsoup.connect(ref);
					con2.timeout(1000 * 60);
					subdoc = con2.get();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


				// college
				Elements collrefs = subdoc.select("a[href]");

				for(Element collref : collrefs){
					String collsuburl = collref.attr("abs:href");
					String collname = collref.text();
//					System.out.println("collref : " + collsuburl);
//					System.out.println("collname : " + collname);
					
					// �����̿� ���� �������� �ѹ���				
					Document subcolldoc = null;
					try {						
						Connection con3 = Jsoup.connect(collsuburl);
						con3.timeout(1000 * 60);
						subcolldoc = con3.get();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// ���� ���� ��ȹ�� �Ľ�
					Elements tables = subcolldoc.select("table.courTable tbody tr td");
					String []tempvalue = new String[13];
					int subcnt = 0;
					
					Elements lechrefs = subcolldoc.select("table.courTable tbody tr td a[href]");
					String temphref = "NULL";
					int hrefcnt = 0;

					//���� �ε��� : 2 , 3 , 7 , 8 , 9			
					for(Element ele: tables){
						
						tempvalue[subcnt] = ele.text();
						subcnt+=1;

						//System.out.println( cnt +"td: " + tempvalue[cnt-1]);

						if( subcnt%13 == 0){
							//System.out.println("----------------");
							LectureObj Aobj = new LectureObj( i + offset,collname,tempvalue[2],tempvalue[3],
									tempvalue[7],tempvalue[8],tempvalue[9]);
							Element lechref = lechrefs.get(hrefcnt);
							temphref = lechref.attr("abs:href");							
							Aobj.setLectureExplainUrl(temphref);
							LectureList.add(Aobj);

//							System.out.println(Aobj.toString());
							
							subcnt = 0;
						}
					}


				}				

			}

		}
		
		return true;

	}

}

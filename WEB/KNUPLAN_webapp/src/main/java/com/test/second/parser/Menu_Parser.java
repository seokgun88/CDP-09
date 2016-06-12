package com.test.second.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.test.second.object.MenuListObj;
import com.test.second.object.MenuObj;

public class Menu_Parser
{

	private MenuListObj tempMenuList;
	private HashMap<Integer, String> map;

	public Menu_Parser() {		
		map = new HashMap<Integer, String>();
		// 46,40,37,56,35
		// 해쉬맵 초기화
		map.put(46, "GP감꽃푸드코트");
		map.put(40, "공학관 학생식당");
		map.put(37, "복지관 학생식당");
		map.put(56, "복현회관 학생식당");
		map.put(35, "정보센터식당");
	}
	
	public MenuListObj getTempMenuList() {
		return tempMenuList;
	}

	public void ParseStart(int num)
	{
		ArrayList<MenuObj> Menuelement= new ArrayList<MenuObj>();
		tempMenuList = new MenuListObj();
		tempMenuList.setBuildname(map.get(num));
		
		Document doc = null;
		try {
			doc = Jsoup.connect("http://coop.knu.ac.kr/sub03/sub01_01.html?shop_sqno="+num).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    doc.outputSettings(new Document.OutputSettings().prettyPrint(false));//makes html() preserve linebreaks and spacing
	    doc.select("br").append("/");

		Elements titles = doc.select("table.tac caption, table.tac td.on ul.menu_im li");
		
		ArrayList<MenuObj> element_MenuList = new ArrayList<MenuObj>();

		for(Element e: titles){
			MenuObj Temp_MenuElement = new MenuObj();
			String temp = e.text();
			
			if(temp.contains("조식")){
				// 조식 List 넣기 시작
				element_MenuList = new ArrayList<MenuObj>();
				tempMenuList.setBreakfast(true);
			}
			else if(temp.contains("중식")){
				if(tempMenuList.getBreakfast() == true && element_MenuList.size() != 0){
					tempMenuList.setBreakfast_MenuList(element_MenuList);
				}
				element_MenuList = new ArrayList<MenuObj>();
				tempMenuList.setLunch(true);
			}
			else if(temp.contains("석식")){
				if(tempMenuList.getLunch() == true && element_MenuList.size() != 0){
					tempMenuList.setLunch_MenuList(element_MenuList);
				}
				element_MenuList = new ArrayList<MenuObj>();
				tempMenuList.setDinner(true);
			}
			// 조식, 중식, 석식 포항 안시킴
			else{
				if(temp.contains("￦") == true){
					String [] tempList = temp.split("￦ ");
					// 원소 오브젝트 초기화
					Temp_MenuElement.setMenu_str(tempList[0]);
					Temp_MenuElement.setPrice(tempList[1]);

					// 리스트에 추가
					element_MenuList.add(Temp_MenuElement);					
				}else{
					// 원소 오브젝트 초기화
					Temp_MenuElement.setMenu_str(temp);
					Temp_MenuElement.setPrice("");

					// 리스트에 추가
					element_MenuList.add(Temp_MenuElement);
				}
			}			
		}
		if(element_MenuList.size() != 0){
			if(tempMenuList.getDinner() == true){
				tempMenuList.setDinner_MenuList(element_MenuList);
			} 
			//점심은 있는데 저녁은 없는 경우
			else{
				if(tempMenuList.getLunch() == true){
					tempMenuList.setLunch_MenuList(element_MenuList);			
				}
			}
		}
		
		if(tempMenuList.getBreakfast_MenuList() == null){
			tempMenuList.setBreakfast(false);
		}
		if(tempMenuList.getLunch_MenuList() == null){
			tempMenuList.setLunch(false);
		}
		if(tempMenuList.getDinner_MenuList() == null){
			tempMenuList.setDinner(false);
		}

	}

}

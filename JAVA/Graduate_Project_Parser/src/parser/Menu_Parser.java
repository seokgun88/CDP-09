package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import object.MenuListObj;
import object.MenuObj;

public class Menu_Parser
{

	private MenuListObj tempMenuList;
	private HashMap<Integer, String> map;

	public Menu_Parser() {		
		map = new HashMap<Integer, String>();
		// 46,40,37,56,35
		// �ؽ��� �ʱ�ȭ
		map.put(46, "GP����Ǫ����Ʈ");
		map.put(40, "���а� �л��Ĵ�");
		map.put(37, "������ �л��Ĵ�");
		map.put(56, "����ȸ�� �л��Ĵ�");
		map.put(35, "�������ͽĴ�");
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

		//	Elements titles = doc.select("table.tac caption, table.tac td.on ul.menu_im li");
		Elements titles = doc.select("table.tac caption, table.tac td.on ul.menu_im li");
		
		ArrayList<MenuObj> element_MenuList = new ArrayList<MenuObj>();

		for(Element e: titles){
			MenuObj Temp_MenuElement = new MenuObj();
			String temp = e.text();
			
			if(temp.contains("����")){
				// ���� List �ֱ� ����
				element_MenuList = new ArrayList<MenuObj>();
				tempMenuList.setBreakfast(true);
			}
			else if(temp.contains("�߽�")){
				if(tempMenuList.getBreakfast() == true && element_MenuList.size() != 0){
					tempMenuList.setBreakfast_MenuList(element_MenuList);
				}
				element_MenuList = new ArrayList<MenuObj>();
				tempMenuList.setLunch(true);
			}
			else if(temp.contains("����")){
				if(tempMenuList.getLunch() == true && element_MenuList.size() != 0){
					tempMenuList.setLunch_MenuList(element_MenuList);
				}
				element_MenuList = new ArrayList<MenuObj>();
				tempMenuList.setDinner(true);
			}
			// ����, �߽�, ���� ���� �Ƚ�Ŵ
			else{
				if(temp.contains("��") == true){
					String [] tempList = temp.split("�� ");
					// ���� ������Ʈ �ʱ�ȭ
					Temp_MenuElement.setMenu_str(tempList[0]);
					Temp_MenuElement.setPrice(tempList[1]);

					// ����Ʈ�� �߰�
					element_MenuList.add(Temp_MenuElement);
//					System.out.println("[*]"+Temp_MenuElement);					
				}else{
					// ���� ������Ʈ �ʱ�ȭ
					Temp_MenuElement.setMenu_str(temp);

					// ����Ʈ�� �߰�
					element_MenuList.add(Temp_MenuElement);
//					System.out.println("[*][*]"+Temp_MenuElement);
				}				
			}			
		}
		if(tempMenuList.getDinner() == true && element_MenuList.size() != 0){
			tempMenuList.setDinner_MenuList(element_MenuList);
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

//		System.out.println(tempMenuList);
	}

//	public static void main(String[] args) throws Exception
//	{
//		Menu_Parser m = new Menu_Parser(); // ��ü ����
//		int []numList = {
//				46,40,37,56,35
//		};
//		for(int cnt = 0; cnt < numList.length; cnt++)
//		{
//			m.ParseStart(numList[cnt]);		
//			System.out.println(m.getTempMenuList()+"\n");
//		}
//
//	}
}

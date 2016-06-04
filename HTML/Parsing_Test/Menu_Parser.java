package parsing;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 
public class Menu_Parser
{
	public void ParseStart(int num)
	{
		Document doc = null;
		try {
			doc = Jsoup.connect("http://coop.knu.ac.kr/sub03/sub01_01.html?shop_sqno="+num).get();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Elements titles = doc.select("table.tac caption, table.tac td.on ul.menu_im li");
		
		for(Element e: titles){
				System.out.println(e.text());
		}
	}

	public static void main(String[] args) throws Exception
	{
		Menu_Parser m = new Menu_Parser(); // 객체 생성
		ArrayList<Integer> menu_num = new ArrayList<Integer>();

		menu_num.add(46);	// GP감꽃푸드코트
		menu_num.add(40);	// 공학관 학생식당
		menu_num.add(37);	// 복지관 학생식당
		menu_num.add(56);	// 복현회관 학생식당
		menu_num.add(35);	// 정보센터식당
		
		for(int cnt = 0; cnt < menu_num.size(); cnt++)
		{
			m.ParseStart(menu_num.get(cnt));
			System.out.println();
		}
	}
}

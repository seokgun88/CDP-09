package parsing;

import java.io.IOException;
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

		Elements titles = doc.select("ul.menu_im li.first p");
				
		for(Element e: titles){									
			System.out.println(e.text());			
		}
	}

	public static void main(String[] args) throws Exception
	{
		Menu_Parser m = new Menu_Parser(); // 객체 생성
		int num = 35;
		
		// GP감꽃푸드코트	num = 46
		// 공학관 학생식당		num = 40
		// 복지관 학생식당		num = 37
		// 복현회관 학생식당	num = 56
		// 정보센터식당		num = 35
		
		m.ParseStart(num);
	}
}
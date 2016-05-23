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
		Menu_Parser m = new Menu_Parser(); // ��ü ����
		int num = 35;
		
		// GP����Ǫ����Ʈ	num = 46
		// ���а� �л��Ĵ�		num = 40
		// ������ �л��Ĵ�		num = 37
		// ����ȸ�� �л��Ĵ�	num = 56
		// �������ͽĴ�		num = 35
		
		m.ParseStart(num);
	}
}
package parser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import object.HolidayObj;

public class HolidayPlan {
	ArrayList<HolidayObj> HolidayList;

	public HolidayPlan(int year, int month){
		HolidayList = new ArrayList<HolidayObj>();
		ParseStart(year, month);		
	}

	public ArrayList<HolidayObj> getHolidayList() {
		return HolidayList;
	}

	public String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();

		Node nValue = (Node) nlList.item(0);

		return nValue.getNodeValue();
	}


	// �Ľ��ؼ� output�� �����ش�.
	// output : ��¥, �����̸�
	public void ParseStart(int year, int month){
		URL url = null;
		String strmonth = String.format("%02d", month);
		String servicekey = "J2UXMfiSPxMk2YYBo9rNEG47s7fvy0GxhNmqM%2Bdf6STlt1oSGcfY5Ej9EKxnfmaT4Ph5uyRp9aYcmHG60sIA8Q%3D%3D";
		String urlStr = "http://apis.data.go.kr/B090041/"
				+ "openapi/service/SpcdeInfoService/"
				+ "getRestDeInfo?solYear="+year+"&solMonth="+strmonth+"&ServiceKey="+servicekey;
		HttpURLConnection connection = null;

		try {
			url = new URL(urlStr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			connection = (HttpURLConnection)url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		connection.setRequestProperty("Content-Type", "application/xml"); // ������ûŸ��

		BufferedReader rd = null;
		try {
			rd = new BufferedReader(
					new InputStreamReader(connection.getInputStream(),"UTF-8"));			
		} catch (IOException e) {
			e.printStackTrace();
		}

		InputSource is = new InputSource(rd);

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc;
			doc = dBuilder.parse(is);

			doc.getDocumentElement().normalize();
			//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("item");
			
			//NodeList sizenode = doc.getElementsByTagName("body");
//			String sizev = getTagValue("totalCount", (Element)sizenode.item(0));
//			sizenode = null;
//			System.out.println(sizev);
//			int sizei = Integer.parseInt(sizev);

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					HolidayObj HoObj = new HolidayObj();

					String dateName = getTagValue("dateName", eElement);
					//String isHoliday = getTagValue("isHoliday", eElement);
					String locdate = getTagValue("locdate", eElement);
					String TempDate = "";
					TempDate += locdate.substring(0, 4) + "-" + locdate.substring(4,6) + "-" + locdate.substring(6); 
						

					HoObj.setDate(TempDate);
					HoObj.setName(dateName);
					HolidayList.add(HoObj);

//					System.out.println("dateName : " + getTagValue("dateName", eElement));
//					System.out.println("isHoliday : " + getTagValue("isHoliday", eElement));
//					System.out.println("locdate : " + getTagValue("locdate", eElement));			        

				}
			}

		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}




		try {
			rd.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}

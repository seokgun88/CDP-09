import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
 
public class Web_Parser_Default {
   
    private final String USER_AGENT = "Mozilla/5.0"; //���� ����� ���� [����]
    private String Address; // ������ �ּ�
    private URL Url; // URL��ü
    private BufferedReader br; // ������ �о�帮�� ���� ���۸���
    private HttpURLConnection con; // ������ ���� Ŀ�ؼ�
    private String protocol = "GET"; // POST OR GET
   
    public Web_Parser_Default() throws Exception
    {
        // 1. ������ �ּ� ����
        Address = "http://navercast.naver.com/magazine_contents.nhn?rid=2832&attrId=&contents_id=66995&leafId=2819&NaPm=ct%3Di0mgpuz4%7Cci%3D3c37fb9ab4ae53e79c1933812aa3949bd7d8e146%7Ctr%3Dsbsc2%7Csn%3D95694%7Chk%3Df5b256ea884b41932f8edfa2349f4cde27d96612";  
       
        // 2. URL ��ü ����
        Url = new URL(this.Address);
       
        // 3. �� ����Ʈ�� ���� ��ü ������
        con = (HttpURLConnection)Url.openConnection(); // �ش� �ּҿ� Ŀ�ؼ� ������
        con.setRequestMethod(protocol); // �������� ����
        con.setRequestProperty("User-Agent", USER_AGENT);  // ���� ����� ���� ����
       
        // 4. �о�� ��Ʈ�� �غ�
        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
       
       
        //5. ���� �о���� readLine()�� ���ؼ� ���پ� �о�ͼ� ȭ���� ǥ��
        String line;
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
        } // while end
       
        // 6. �� �о���� ��Ʈ�� �ݱ�
        br.close();
       
       
    }
 
    public static void main(String[] args) throws Exception {
       
        Web_Parser_Default w = new Web_Parser_Default(); // ��ü ����
 
    }
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
 
 
public class Web_Parser_Default {
   
    private final String USER_AGENT = "Mozilla/5.0"; //접속 사용자 정보 [선택]
    private String Address; // 접속할 주소
    private URL Url; // URL객체
    private BufferedReader br; // 내용을 읽어드리기 위한 버퍼리더
    private HttpURLConnection con; // 웹과의 연결 커넥션
    private String protocol = "GET"; // POST OR GET
   
    public Web_Parser_Default() throws Exception
    {
        // 1. 접속할 주소 설정
        Address = "http://navercast.naver.com/magazine_contents.nhn?rid=2832&attrId=&contents_id=66995&leafId=2819&NaPm=ct%3Di0mgpuz4%7Cci%3D3c37fb9ab4ae53e79c1933812aa3949bd7d8e146%7Ctr%3Dsbsc2%7Csn%3D95694%7Chk%3Df5b256ea884b41932f8edfa2349f4cde27d96612";  
       
        // 2. URL 객체 생성
        Url = new URL(this.Address);
       
        // 3. 웹 사이트에 연결 객체 얻어오기
        con = (HttpURLConnection)Url.openConnection(); // 해당 주소에 커넥션 얻어오기
        con.setRequestMethod(protocol); // 프로토콜 설정
        con.setRequestProperty("User-Agent", USER_AGENT);  // 접속 사용자 정보 선택
       
        // 4. 읽어올 스트림 준비
        br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
       
       
        //5. 내용 읽어오기 readLine()를 통해서 한줄씩 읽어와서 화면이 표시
        String line;
        while ((line = br.readLine()) != null)
        {
            System.out.println(line);
        } // while end
       
        // 6. 다 읽어오면 스트림 닫기
        br.close();
       
       
    }
 
    public static void main(String[] args) throws Exception {
       
        Web_Parser_Default w = new Web_Parser_Default(); // 객체 생성
 
    }
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
 
 
public abstract class Web_Parser
{
 
    private final String USER_AGENT = "Mozilla/5.0"; //접속 사용자 정보 [선택]
    private String Address; // 접속할 주소
    private URL Url; // URL객체
    private BufferedReader br; // 내용을 읽어드리기 위한 버퍼리더
    private HttpURLConnection con; // 웹과의 연결 커넥션
  
    private Web_Parser()
    {
   
    }
   
    public Web_Parser(String address)
    {
        this.Address = address;
       
        try
        {
            Url = new URL(this.Address);
        } // try end
        catch (Exception e) {System.err.println(e);} // catch end
    }
   
       
    public boolean Connection(String protocol)
    {
       
        if(this.Address==null || this.Address.length()==0)
        {
            System.err.println("address setup -> setUrl(String str)");
            return false;
        }
        else if(!protocol.equals("POST") && !protocol.equals("GET"))
        {
            System.err.println("POST or GET");
            return false;
        }
        else
        {
            try
            {
                con = (HttpURLConnection)Url.openConnection();
                con.setRequestMethod(protocol);
                con.setRequestProperty("User-Agent", USER_AGENT);  // 접속 사용자 정보 선택
               
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                return true;
            }
            catch (Exception e) {System.err.println(e); return false;} // catch end
        }
   
    }
   
    public boolean WebParsing()
    {
        String line = null;
        int count =0;
       
        if(br==null)
        {
            System.err.println("Connection error -> Connection(Post/Get)");
            return false;
        } // if end
        else
        {
            try
            {
                while ((line = br.readLine()) != null)
                {
                    Parser(line);
                } // while end
                    br.close();
                    return true;
            } // try end
                catch (Exception e) {System.err.println(e); return false;} // catch end
        }// else end
    }
 
       
    public URL getUrl()
    {
        return Url;
    }
 
    public void setUrl(String url)
    {
        try
        {
            Url = new URL(url);
        } // try end
        catch (MalformedURLException e) {System.err.println(e);} // catch end
    }
   
   
    public abstract void Parser(String line);
 
}

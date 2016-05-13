public class Main extends Web_Parser{
    // Web_Parser 상속시에는 반드시 2가지 메소드를 정의해주어야 합니다.
    public Main(String address)
    {
        super(address);
    }
   
    @Override
    public void Parser(String line) // 파싱이 성공할경우 호출되는 메소드
    {
        System.out.println(line);
    }
 
    public static void main(String[] args)
    {
       
        String address = "http://www.naver.com";
 
       
       
        Main web = new Main(address);
        // boolean 형태로 리턴되기에 if문을 사용 했습니다.
        if(web.Connection("POST")) // 연결 성공여부
        {
            if(web.WebParsing()){} // 연결 성공시 실질적인 파싱 실행
            else
            {
                System.err.println("실패");
            }
           
        }
        else
        {
            System.err.println("실패");
        }
       
    }
 
 
}
public class Main{
 
 
    public static void main(String[] args)
    {
       
        String address = "http://www.naver.com";
 
               
        Web_Parser ww = new Web_Parser(address) {
 
            @Override
            public void Parser(String line) {
                System.out.println(line); 
            }
        };
       
        ww.Connection("POST");
        ww.WebParsing();
    }
}
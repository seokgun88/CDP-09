public class Main extends Web_Parser{
    // Web_Parser ��ӽÿ��� �ݵ�� 2���� �޼ҵ带 �������־�� �մϴ�.
    public Main(String address)
    {
        super(address);
    }
   
    @Override
    public void Parser(String line) // �Ľ��� �����Ұ�� ȣ��Ǵ� �޼ҵ�
    {
        System.out.println(line);
    }
 
    public static void main(String[] args)
    {
       
        String address = "http://www.naver.com";
 
       
       
        Main web = new Main(address);
        // boolean ���·� ���ϵǱ⿡ if���� ��� �߽��ϴ�.
        if(web.Connection("POST")) // ���� ��������
        {
            if(web.WebParsing()){} // ���� ������ �������� �Ľ� ����
            else
            {
                System.err.println("����");
            }
           
        }
        else
        {
            System.err.println("����");
        }
       
    }
 
 
}
import java.net.*;
import java.io.*;
class Client{
    Socket socket; 
   BufferedReader br;
   PrintWriter out;
   public  Client(){
    System.out.println("Akash");
        try {
            System.out.println("Sending request to server");
            socket=new Socket("127.0.0.1",7777);
            System.out.println("Conection Done");
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            startReading();
            StartWriting();
            
        } catch (Exception e) {
            e.getStackTrace();
        }
        
   }
   public void startReading() {
    Runnable r1=()->{
        System.out.println("reader starter....");
        while(true){

            try {
                 String msg=br.readLine();
                if(msg.equals("exit")){
                    System.out.println("Server terminated the chat");
                    break;
                }
                System.out.println("Server:"+msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
               
        }
    };
       new Thread(r1).start();
}
public void StartWriting(){
     Runnable r2=()->{
        while (true) {
            try {
                 BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                 String content=br1.readLine();
                 out.println(content);
                 out.flush();
            }
             catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
    
            }
    };
    new Thread(r2).start();


}
    public static void main(String[] args) {
        System.out.println("Tapu");
       new Client();
    }
}
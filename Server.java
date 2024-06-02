import java.net.*;
import java.io.*;
public class Server {
   ServerSocket server;
   Socket socket; 
   BufferedReader br;
   PrintWriter out;
   public Server(){
    try{
         server=new ServerSocket(7777);
         System.out.println("Server is waiting to accept conection");
         System.out.println("Server is waiting....");
         socket=server.accept();
         br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
         out=new PrintWriter(socket.getOutputStream());
         startReading();
         StartWriting();
    }catch(Exception e){
        System.out.println(e);
    }
       
   }
   public void startReading() {
        Runnable r1=()->{
            System.out.println("reader starter....");
            while(true){

                try {
                     String msg=br.readLine();
                    if(msg.equals("exit")){
                        System.out.println("Client terminated the chat");
                        break;
                    }
                    System.out.println("Client:"+msg);
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
       new Server();
    }
    
}

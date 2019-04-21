//Server for communication with all clients.
import java.io.*; 
import java.net.*; 

public class BankServer
{ 
    public static void main(String[] args) throws IOException  
    { 
        //Port number for server to listen to
        ServerSocket ss = new ServerSocket(9001); 
          
        //Wait for clients to connect
        while (true)  
        { 
            Socket s = null; 
            try 
            { 
                //Receive incoming client requests 
                s = ss.accept(); 
                
                // obtaining input and out streams 
                DataInputStream dis = new DataInputStream(s.getInputStream()); 
                DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
  
                // create a new thread object 
                Thread t = new ClientHandler(s, dis, dos); 
  
                // Invoking the start() method
                t.start(); 
            } 
            catch (Exception e){ 
                s.close(); 
                e.printStackTrace(); 
            } 
        } 
    } 
}

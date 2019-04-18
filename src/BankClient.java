import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
  
// Client class 
public class BankClient
{ 
    public Socket s = null;
    public BankClient(String ip)
    { 
        try
        { 
            Scanner scn = new Scanner(System.in); 
      
            //Establish the connection with server port
            int port = 5052;
            s = new Socket(ip, port); 
            
            //Close scanner
            scn.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    }
    
    public boolean verifyUser(String user, String pwd)
    {
        String received = "";
        try
        { 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 

            String tosend = "00:" + user + "|" + pwd; 
            dos.writeUTF(tosend);
            received = dis.readUTF();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        if(received.equals("true")){return true;}
        else {return false;}
    }
} 

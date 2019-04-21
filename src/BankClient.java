import java.io.*; 
import java.net.*;
import java.util.*;
  
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
            int port = 9001;
            s = new Socket(ip, port); 
            
            //Close scanner
            scn.close(); 
        }catch(Exception e){ 
            e.printStackTrace(); 
        } 
    }
    
    public String getData(String user, String cmd)
    {
        String received = "";
        try
        { 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            //Use username to gather all user data
            String tosend = cmd + ":" + user; 
            dos.writeUTF(tosend);
            
            //Receive status of user
            received = dis.readUTF();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        return received;
    }
    
    public boolean isFrozen(String user)
    {
        String ret = getData(user, "10");
        if(ret.equals("frozen"))
        {
            return true;
        }
        else {
            return false;
        }
    }
    
    public String changeData(String user, String data, String cmd)
    {
        String received = "";
        try
        { 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            //Use username to gather all user data
            String tosend = cmd + ":" + user + "|" + data; 
            dos.writeUTF(tosend);
            
            //Receive status of user
            received = dis.readUTF();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        return received;
    }
    
    public boolean verifyUser(String user, String pwd)
    {
        String received = "";
        try
        { 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            //Send username and password to server with command 00
            String tosend = "00:" + user + "|" + pwd; 
            dos.writeUTF(tosend);
            
            //Receive status of user
            received = dis.readUTF();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        if(received.equals("true")){return true;}
        else {return false;}
    }
    
    public boolean createAcc(String user, String pwd, String fname, String lname)
    {
        String received = "";
        Random rand = new Random();
        
        try
        { 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            //Send new account info to server with command 01
            String tosend = "01:" + user + "|" + pwd + "|" + fname + "|" + lname + "|0.00|0.00|" + Integer.toString((rand.nextInt(201) + 600)) + "|" + "acceptable\n"; 
            dos.writeUTF(tosend);
            
            //Receive status of user
            received = dis.readUTF();
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
        if(received.equals("true")){return true;}
        else {return false;}
    }
    
    public void endConnection()
    {
        try
        { 
            // obtaining input and out streams 
            DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            
            //Use username to gather all user data
            String tosend = "99:"; 
            dos.writeUTF(tosend);
        }catch(Exception e){ 
            e.printStackTrace(); 
        }
    }
} 

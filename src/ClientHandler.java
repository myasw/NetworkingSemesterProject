import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

// ClientHandler class 
class ClientHandler extends Thread  
{ 
    final DataInputStream dis;
    final DataOutputStream dos; 
    final Socket s; 
    
    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)  
    { 
        this.s = s; 
        this.dis = dis;
        this.dos = dos; 
    } 
  
    @Override
    public void run()  
    { 
        String received; 
        String toreturn;
        String cmd, data;
        while (true)  
        { 
            try {                  
                // receive message from client 
                received = dis.readUTF();
                System.out.println(received);
                
                //First 3 characters set command
                cmd = received.substring(0,3);
                data = received.substring(3,received.length());
                //React to client
                switch (cmd) { 
                    case "00:":
                        dos.writeUTF(hasAccount(data));
                        break;
                    case "99:":
                        this.s.close(); 
                        break; 
                    default: 
                        dos.writeUTF("Invalid input"); 
                        break; 
                } 
            } catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
    }
    
    public String hasAccount(String data)
    {
        BufferedReader br = null;
        String ret = "false";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();  
            while (line != null)  
            {  
                if(line.equals(data)) {ret = "true";}  
                line = br.readLine();  
            } 
        } catch(IOException e) {e.printStackTrace();}
        return ret;
    }
} 
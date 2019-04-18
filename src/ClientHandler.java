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
                    case "01:":
                        dos.writeUTF(accCreated(data));
                        break;
                    case "99:":
                        this.s.close();
                        break; 
                } 
            } catch (IOException e) { 
                e.printStackTrace();
                break;
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
            ArrayList<String> userData;
            String userAndPass = "";
            while (line != null)  
            {
                userData = viewUser(line);
                userAndPass = userData.get(0) + "|" + userData.get(1);
                if(userAndPass.equals(data)) {ret = "true";}  
                line = br.readLine();  
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return ret;
    }
    
    public ArrayList<String> viewUser(String line)
    {
        //Separate all of a user's information
        int bar = line.indexOf("|");
        ArrayList<String> userData = new ArrayList<String>();
        String info = "";
        while(bar != -1)
        {
            info=line.substring(0,bar);
            line=line.substring(bar+1);
            userData.add(info);
            bar = line.indexOf("|");
        }
        userData.add(line);
        return userData;
    }
    
    public String accCreated(String data)
    {
        BufferedWriter bw = null;
        BufferedReader br = null;
        String ret = "true";
        String temp1 = "", temp2 = "";
        try{
            temp1=data.substring(0, data.indexOf("|"));
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            while (line != null)  
            {  
                //Checks username against all existing users
                temp2 = line.substring(0, line.indexOf("|"));
                if(temp1.equals(temp2)) {ret = "false";}  
                line = br.readLine();  
            }
            //If user does not exist, create account
            if(ret.equals("true"))
            {
                bw = new BufferedWriter(new FileWriter("users.txt", true));
                bw.newLine();
                bw.write(data);
                bw.close();
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return ret;
    }
} 
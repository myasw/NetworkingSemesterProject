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
        boolean done = false;
        while (!done)  
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
                    case "00:": //Verify user before they enter server
                        dos.writeUTF(hasAccount(data));
                        break;
                    case "01:": //Ensures that no two usernames are the same
                        dos.writeUTF(accCreated(data));
                        break;
                    case "02:": //Gets full name
                        dos.writeUTF(getFullName(data));
                        break;
                    case "03:": //Gets the checking balance
                        dos.writeUTF(getInfo(data, 4));
                        break;
                    case "04:": //Gets the savings balance
                        dos.writeUTF(getInfo(data, 5));
                        break;
                    case "05:": //Adds to checking
                        dos.writeUTF(changeBalance(data, 4, ""));
                        break;
                    case "06:": //Adds to savings
                        dos.writeUTF(changeBalance(data, 5, ""));
                        break;
                    case "07:": //Removes from checking
                        dos.writeUTF(changeBalance(data, 4, "-"));
                        break;
                    case "08:": //Removes from savings
                        dos.writeUTF(changeBalance(data, 5, "-"));
                        break;
                    case "09:": //Gets the credit score
                        dos.writeUTF(getInfo(data, 6));
                        break;
                    case "10:": //Returns whether account is frozen
                        dos.writeUTF(getInfo(data, 7));
                        break;
                    case "11:": //Freezes accounts
                        dos.writeUTF(changeFrozen(data));
                        break;
                    case "99:":
                        this.s.close();
                        done = true;
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
    
    public String getInfo(String data, int id)
    {
        BufferedReader br = null;
        String user = "", info = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            while (line != null)  
            {
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(data)) {info = userData.get(id);} 
                line = br.readLine();  
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return info;
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
    
    public String getFullName(String data)
    {
        BufferedReader br = null;
        String name = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            String user = "";
            while (line != null)  
            {
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(data)) {name = userData.get(2) + " " + userData.get(3);}  
                line = br.readLine();  
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return name;
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
                bw.write(data);
                bw.close();
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return ret;
    }
    
    public String changeBalance(String data, int balID, String neg)
    {
        BufferedReader br = null;
        String user = "";
        String balance = "";
        double newBal = 0;

        String oldInfo = "";
        String newInfo = "";
        String oldLine = "";
        String newLine = "";
        String temp1, temp2 = neg;
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            temp1 = data.substring(0, data.indexOf("|"));
            temp2 += data.substring(data.indexOf("|") + 1);
            while (line != null)  
            {
                oldInfo = oldInfo + line + "\n";
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(temp1)) {balance = userData.get(balID);} 
                line = br.readLine();  
            }
            br.close();
            br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            newBal = Double.parseDouble(balance) + Double.parseDouble(temp2);
            
            while (line != null) {
                userData = viewUser(line);
                user = userData.get(0);
                String newStr = line.substring(line.indexOf("|") + 1);
                String newStr2 = "";
                if (user.equals(temp1)) {
                    for (int i = 0; i < balID - 1; i++) {
                        newStr = newStr.substring(newStr.indexOf("|") + 1);
                    }
                    newStr2 = newStr.substring(newStr.indexOf("|"));
                    oldLine = line;
                    line = line.substring(0, line.indexOf(newStr)) + String.format("%.2f", newBal) + newStr2;
                    newLine = line;
                }
                
                line = br.readLine();
            }
            oldLine = oldLine.replace('|', '@');
            newLine = newLine.replace('|', '@');
            
            oldInfo = oldInfo.replace('|', '@');
            newInfo = oldInfo.replaceAll(oldLine, newLine);
            newInfo = newInfo.replace('@', '|');
            
            FileWriter writer = new FileWriter("users.txt");
            writer.write(newInfo);
            writer.close();
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return balance;
    }
    
    public String changeFrozen(String data)
    {
        BufferedReader br = null;
        String user = "";
        String oldInfo = "";
        String newInfo = "";
        String oldLine = "";
        String newLine = "";
        String username, frozen ="";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            username = data.substring(0, data.indexOf("|"));
            while (line != null)  
            {
                oldInfo = oldInfo + line + "\n";
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(username)) {frozen = userData.get(7);} 
                line = br.readLine();  
            }
            br.close();
            br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            if(frozen.equals("acceptable"))
            {
                frozen = "frozen";
            } else {
                frozen = "acceptable";
            }
            
            while (line != null) {
                userData = viewUser(line);
                user = userData.get(0);
                String newStr = line.substring(line.indexOf("|") + 1);
                if (user.equals(username)) {
                    for (int i = 0; i < 6; i++) {
                        newStr = newStr.substring(newStr.indexOf("|") + 1);
                    }
                    oldLine = line;
                    line = line.substring(0, line.indexOf(newStr)) + frozen;
                    newLine = line;
                }
                
                line = br.readLine();
            }
            oldLine = oldLine.replace('|', '@');
            newLine = newLine.replace('|', '@');
            
            oldInfo = oldInfo.replace('|', '@');
            newInfo = oldInfo.replaceAll(oldLine, newLine);
            newInfo = newInfo.replace('@', '|');
            
            FileWriter writer = new FileWriter("users.txt");
            writer.write(newInfo);
            writer.close();
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return frozen;
    }
} 
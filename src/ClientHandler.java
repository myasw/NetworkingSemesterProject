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
                    case "00:":
                        dos.writeUTF(hasAccount(data));
                        break;
                    case "01:":
                        dos.writeUTF(accCreated(data));
                        break;
                    case "02:":
                        dos.writeUTF(getFullName(data));
                        break;
                    case "03:":
                    	dos.writeUTF(getCheckingBalance(data));
                    	break;
                    case "04:":
                    	dos.writeUTF(getSavingsBalance(data));
                    	break;
                    case "05:":
                    	dos.writeUTF(addToChecking(data));
                    	break;
                    case "06:":
                    	dos.writeUTF(addToSavings(data));
                    	break;
                    case "07:":
                    	dos.writeUTF(removeFromChecking(data));
                    	break;
                    case "08:":
                    	dos.writeUTF(removeFromSavings(data));
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
    
    public String getCheckingBalance(String data)
    {
        BufferedReader br = null;
        String user = "";
        String checkingBalance = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            while (line != null)  
            {
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(data)) {checkingBalance = userData.get(4);} 
                line = br.readLine();  
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return checkingBalance;
    }
    
    public String getSavingsBalance(String data)
    {
        BufferedReader br = null;
        String user = "";
        String savingsBalance = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            while (line != null)  
            {
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(data)) {savingsBalance = userData.get(5);} 
                line = br.readLine();  
            }
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return savingsBalance;
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
    
    public String addToChecking(String data)
    {
    	BufferedReader br = null;
        String user = "";
        String checkingBalance = "";
        double newChecking = 0;

        String oldInfo = "";
        String newInfo = "";
        String oldLine = "";
        String newLine = "";
        String temp1 = "", temp2 = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            temp1 = data.substring(0, data.indexOf("|"));
            temp2 = data.substring(data.indexOf("|") + 1);
            while (line != null)  
            {
            	oldInfo = oldInfo + line + "\n";
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(temp1)) {checkingBalance = userData.get(4);} 
                line = br.readLine();  
            }
            br.close();
            br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            newChecking = Double.parseDouble(checkingBalance) + Double.parseDouble(temp2);
            
            while (line != null) {
            	userData = viewUser(line);
            	user = userData.get(0);
            	String newStr = line.substring(line.indexOf("|") + 1);
            	String newStr2 = "";
            	if (user.equals(temp1)) {
	            	for (int i = 0; i < 3; i++) {
	            		newStr = newStr.substring(newStr.indexOf("|") + 1);
	            	}
	            	newStr2 = newStr.substring(newStr.indexOf("|"));
	            	oldLine = line;
	            	line = line.substring(0, line.indexOf(newStr)) + String.format("%.2f", newChecking) + newStr2;
	            	newLine = line;
            	}
            	
            	line = br.readLine();
            }
            oldLine = oldLine.replace('|', ' ');
            newLine = newLine.replace('|', ' ');
            
            oldInfo = oldInfo.replace('|', ' ');
            newInfo = oldInfo.replaceAll(oldLine, newLine);
            newInfo = newInfo.replace(' ', '|');
            
            FileWriter writer = new FileWriter("users.txt");
            writer.write(newInfo);
            writer.close();
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return checkingBalance;
    }
    
    public String addToSavings(String data)
    {
    	BufferedReader br = null;
        String user = "";
        String savingsBalance = "";
        double newSavings = 0;

        String oldInfo = "";
        String newInfo = "";
        String oldLine = "";
        String newLine = "";
        String temp1 = "", temp2 = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            temp1 = data.substring(0, data.indexOf("|"));
            temp2 = data.substring(data.indexOf("|") + 1);
            while (line != null)  
            {
            	oldInfo = oldInfo + line + "\n";
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(temp1)) {savingsBalance = userData.get(5);} 
                line = br.readLine();  
            }
            br.close();
            br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            newSavings = Double.parseDouble(savingsBalance) + Double.parseDouble(temp2);
            
            while (line != null) {
            	userData = viewUser(line);
            	user = userData.get(0);
            	String newStr = line.substring(line.indexOf("|") + 1);
            	String newStr2 = "";
            	if (user.equals(temp1)) {
	            	for (int i = 0; i < 4; i++) {
	            		newStr = newStr.substring(newStr.indexOf("|") + 1);
	            	}
	            	newStr2 = newStr.substring(newStr.indexOf("|"));
	            	oldLine = line;
	            	line = line.substring(0, line.indexOf(newStr)) + String.format("%.2f", newSavings) + newStr2;
	            	newLine = line;
            	}
            	
            	line = br.readLine();
            }
            oldLine = oldLine.replace('|', ' ');
            newLine = newLine.replace('|', ' ');
            
            oldInfo = oldInfo.replace('|', ' ');
            newInfo = oldInfo.replaceAll(oldLine, newLine);
            newInfo = newInfo.replace(' ', '|');
            
            FileWriter writer = new FileWriter("users.txt");
            writer.write(newInfo);
            writer.close();
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return savingsBalance;
    }
    
    public String removeFromChecking(String data)
    {
    	BufferedReader br = null;
        String user = "";
        String checkingBalance = "";
        double newChecking = 0;

        String oldInfo = "";
        String newInfo = "";
        String oldLine = "";
        String newLine = "";
        String temp1 = "", temp2 = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            temp1 = data.substring(0, data.indexOf("|"));
            temp2 = data.substring(data.indexOf("|") + 1);
            while (line != null)  
            {
            	oldInfo = oldInfo + line + "\n";
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(temp1)) {checkingBalance = userData.get(4);} 
                line = br.readLine();  
            }
            br.close();
            br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            newChecking = Double.parseDouble(checkingBalance) - Double.parseDouble(temp2);
            
            while (line != null) {
            	userData = viewUser(line);
            	user = userData.get(0);
            	String newStr = line.substring(line.indexOf("|") + 1);
            	String newStr2 = "";
            	if (user.equals(temp1)) {
	            	for (int i = 0; i < 3; i++) {
	            		newStr = newStr.substring(newStr.indexOf("|") + 1);
	            	}
	            	newStr2 = newStr.substring(newStr.indexOf("|"));
	            	oldLine = line;
	            	line = line.substring(0, line.indexOf(newStr)) + String.format("%.2f", newChecking) + newStr2;
	            	newLine = line;
            	}
            	
            	line = br.readLine();
            }
            oldLine = oldLine.replace('|', ' ');
            newLine = newLine.replace('|', ' ');
            
            oldInfo = oldInfo.replace('|', ' ');
            newInfo = oldInfo.replaceAll(oldLine, newLine);
            newInfo = newInfo.replace(' ', '|');
            
            FileWriter writer = new FileWriter("users.txt");
            writer.write(newInfo);
            writer.close();
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return checkingBalance;
    }
    
    public String removeFromSavings(String data)
    {
    	BufferedReader br = null;
        String user = "";
        String savingsBalance = "";
        double newSavings = 0;

        String oldInfo = "";
        String newInfo = "";
        String oldLine = "";
        String newLine = "";
        String temp1 = "", temp2 = "";
        try{
            br = new BufferedReader(new FileReader("users.txt"));
            String line = br.readLine();
            ArrayList<String> userData;
            temp1 = data.substring(0, data.indexOf("|"));
            temp2 = data.substring(data.indexOf("|") + 1);
            while (line != null)  
            {
            	oldInfo = oldInfo + line + "\n";
                userData = viewUser(line);
                user = userData.get(0);
                if(user.equals(temp1)) {savingsBalance = userData.get(5);} 
                line = br.readLine();  
            }
            br.close();
            br = new BufferedReader(new FileReader("users.txt"));
            line = br.readLine();
            newSavings = Double.parseDouble(savingsBalance) - Double.parseDouble(temp2);
            
            while (line != null) {
            	userData = viewUser(line);
            	user = userData.get(0);
            	String newStr = line.substring(line.indexOf("|") + 1);
            	String newStr2 = "";
            	if (user.equals(temp1)) {
	            	for (int i = 0; i < 4; i++) {
	            		newStr = newStr.substring(newStr.indexOf("|") + 1);
	            	}
	            	newStr2 = newStr.substring(newStr.indexOf("|"));
	            	oldLine = line;
	            	line = line.substring(0, line.indexOf(newStr)) + String.format("%.2f", newSavings) + newStr2;
	            	newLine = line;
            	}
            	
            	line = br.readLine();
            }
            oldLine = oldLine.replace('|', ' ');
            newLine = newLine.replace('|', ' ');
            
            oldInfo = oldInfo.replace('|', ' ');
            newInfo = oldInfo.replaceAll(oldLine, newLine);
            newInfo = newInfo.replace(' ', '|');
            
            FileWriter writer = new FileWriter("users.txt");
            writer.write(newInfo);
            writer.close();
            br.close();
        } catch(IOException e) {e.printStackTrace();}
        return savingsBalance;
    }

} 
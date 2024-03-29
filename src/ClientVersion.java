//Matthew Yaswinski & Joshua Del Toro
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientVersion {
    public ClientVersion(BankClient client, String user)
    {
        JFrame f = new JFrame("Choose a version");
        f.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        f.setBounds(0, 0, 800, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setLayout(null);
        f.setVisible(true);

        //text describing the GUI
        JLabel iDFText2 = new JLabel("Choose a version");
        iDFText2.setHorizontalAlignment(SwingConstants.CENTER);
        iDFText2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        iDFText2.setBounds(148, 152, 501, 58);
        f.getContentPane().add(iDFText2);
                
        //button to select mobile GUI
        JButton mobileBtn = new JButton("Mobile");
        mobileBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        mobileBtn.setBounds(90, 385, 218, 38);
        f.getContentPane().add(mobileBtn);
        //selects mobile GUI
        mobileBtn.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) { 
                f.dispose();
                ClientMobile mobile = new ClientMobile(client, user);
            }
        });
                
        //button for selecting ATM GUI
        JButton atmBtn = new JButton("ATM");
        atmBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        atmBtn.setBounds(457, 385, 218, 38);
        f.getContentPane().add(atmBtn);
        //selects ATM GUI
        atmBtn.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) { 
                f.dispose();
                ClientATM atm = new ClientATM(client, user);
            }
        });
        
        f.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                client.endConnection();
                System.exit(0);
            }
        });
    }
}
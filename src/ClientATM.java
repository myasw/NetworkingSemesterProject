import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class ClientATM {
 	public ClientATM(BankClient client)
 	{
 	    JFrame frame = new JFrame("ATM Banking GUI");
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
 	    JLabel iDFText = new JLabel("Hello...\nWhat would you like to do?");
		iDFText.setHorizontalAlignment(SwingConstants.CENTER);
		iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
		iDFText.setBounds(148, 152, 501, 58);
		frame.getContentPane().add(iDFText);
		
		JButton disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		disconnectBtn.setBounds(457, 100, 218, 42);
		frame.getContentPane().add(disconnectBtn);
		disconnectBtn.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) { 
                frame.dispose();
                ClientLogin c = new ClientLogin();
            }
        });
		
		JTextField balanceField = new JTextField();
		balanceField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		balanceField.setBounds(90, 334, 218, 39);
		frame.getContentPane().add(balanceField);
		balanceField.setColumns(10);
		
		JButton balanceBtn = new JButton("Balance");
		balanceBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		balanceBtn.setBounds(90, 385, 218, 38);
		frame.getContentPane().add(balanceBtn);
		
		JTextField depositField = new JTextField();
		depositField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		depositField.setBounds(457, 334, 218, 39);
		frame.getContentPane().add(depositField);
		depositField.setColumns(10);

 		JTextField withdrawField = new JTextField();
		withdrawField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		withdrawField.setBounds(457, 459, 218, 38);
		frame.getContentPane().add(withdrawField);
		withdrawField.setColumns(10);
		
		JButton depositBtn = new JButton("Deposit");
		depositBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		depositBtn.setBounds(457, 385, 218, 38);
		frame.getContentPane().add(depositBtn);

 		JButton withdrawBtn = new JButton("Withdraw");
		withdrawBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		withdrawBtn.setBounds(457, 509, 218, 48);
		frame.getContentPane().add(withdrawBtn);
		
		JPanel radioPanel2 = new JPanel();
		radioPanel2.setBounds(105, 526, 186, 99);
		frame.getContentPane().add(radioPanel2);
		
		ButtonGroup group2 = new ButtonGroup();
		JRadioButton checkingBtn2 = new JRadioButton("Checking");
		checkingBtn2.setHorizontalAlignment(SwingConstants.CENTER);
		checkingBtn2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JRadioButton savingsBtn2 = new JRadioButton("Savings");
		savingsBtn2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		savingsBtn2.setHorizontalAlignment(SwingConstants.CENTER);
		group2.add(checkingBtn2);
		group2.add(savingsBtn2);

 		radioPanel2.add(checkingBtn2);
		radioPanel2.add(savingsBtn2);
 	}
}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class ClientMobile {
 	public ClientMobile(BankClient client, String user)
 	{
 	    JFrame frame = new JFrame("Mobile Banking GUI");
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		String name = client.getName(user);
		
 	    JLabel iDFText = new JLabel("Hello " + name + ".");
		iDFText.setHorizontalAlignment(SwingConstants.CENTER);
		iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
		iDFText.setBounds(148, 152, 501, 58);
		frame.getContentPane().add(iDFText);
		
		JLabel iDFText2 = new JLabel("What would you like to do?");
		iDFText2.setHorizontalAlignment(SwingConstants.CENTER);
		iDFText2.setFont(new Font("Times New Roman", Font.BOLD, 30));
		iDFText2.setBounds(148, 220, 501, 58);
		frame.getContentPane().add(iDFText2);
		
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
		
		JTextField scoreField = new JTextField();
		scoreField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scoreField.setBounds(457, 334, 218, 39);
		frame.getContentPane().add(scoreField);
		scoreField.setColumns(10);

 		JButton freezeBtn = new JButton("Freeze Credit Card");
		freezeBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		freezeBtn.setBounds(457, 460, 218, 48);
		frame.getContentPane().add(freezeBtn);

 		JTextField transferField = new JTextField();
		transferField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		transferField.setBounds(457, 558, 218, 38);
		frame.getContentPane().add(transferField);
		transferField.setColumns(10);

 		JButton scoreBtn = new JButton("Credit Score");
		scoreBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		scoreBtn.setBounds(457, 385, 218, 38);
		frame.getContentPane().add(scoreBtn);

 		JButton transferBtn = new JButton("Transfer To...");
		transferBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		transferBtn.setBounds(457, 608, 218, 48);
		frame.getContentPane().add(transferBtn);
		
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
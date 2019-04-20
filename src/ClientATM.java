import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class ClientATM {
    public ClientATM(BankClient client, String user)
    {
        JFrame frame = new JFrame("ATM Banking GUI");
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
                client.endConnection();
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
        group2. add(savingsBtn2);

        radioPanel2.add(checkingBtn2);
        radioPanel2.add(savingsBtn2);
        
        JLabel errLabel = new JLabel("");
        errLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        errLabel.setForeground(Color.RED);
        errLabel.setBounds(90, 435, 218, 39);
        frame.getContentPane().add(errLabel);
        
        JLabel errLabel2 = new JLabel("");
        errLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        errLabel2.setForeground(Color.RED);
        errLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        errLabel2.setBounds(90, 472, 218, 39);
        frame.getContentPane().add(errLabel2);
        
        balanceBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (checkingBtn2.isSelected()) {
                    balanceField.setText("$" + client.getCheckingBalance(user));
                    errLabel.setText("");
                    errLabel2.setText("");
                }
                else if (savingsBtn2.isSelected()) {
                    balanceField.setText("$" + client.getSavingsBalance(user));
                    errLabel.setText("");
                    errLabel2.setText("");
                }
            }
    
        });
        
        depositBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) {
				String depValue = depositField.getText();
				if (checkingBtn2.isSelected()) {
					client.addToChecking(user, depValue);
					errLabel.setText("");
					errLabel2.setText("");
				}
				else if (savingsBtn2.isSelected()) {
					client.addToSavings(user, depValue);
					errLabel.setText("");
					errLabel2.setText("");
				}
			}
		});
        
        withdrawBtn.addActionListener(new ActionListener()
		{
        	public void actionPerformed(ActionEvent e) {
        		String withValue = withdrawField.getText();
        		if (checkingBtn2.isSelected()) {
        			if (Double.parseDouble(client.getCheckingBalance(user)) < Double.parseDouble(withValue)) {
        				errLabel.setText("Cannot withdraw more");
        				errLabel2.setText("than in account");
        			} else {
        				client.removeFromChecking(user, withValue);
        				errLabel.setText("");
    					errLabel2.setText("");
        			}
        		} else if (savingsBtn2.isSelected()) {
        			if (Double.parseDouble(client.getSavingsBalance(user)) < Double.parseDouble(withValue)) {
        				errLabel.setText("Cannot withdraw more");
        				errLabel2.setText("than in account");
        			} else {
        				client.removeFromSavings(user, withValue);
        				errLabel.setText("");
    					errLabel2.setText("");
        			}
        		}
        	}
		});
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                client.endConnection();
                System.exit(0);
            }
        });
    }
}
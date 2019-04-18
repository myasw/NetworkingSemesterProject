import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class AccountCreation {
	public AccountCreation(BankClient client){
		JFrame frame = new JFrame("Create an Account");
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

 		JLabel iDFText = new JLabel("Enter the following information");
		iDFText.setHorizontalAlignment(SwingConstants.CENTER);
		iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
		iDFText.setBounds(148, 186, 501, 58);
		frame.getContentPane().add(iDFText);

 		JButton exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		exitBtn.setBounds(457, 100, 218, 42);
		frame.getContentPane().add(exitBtn);

 		JTextField firstNameField = new JTextField();
		firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		firstNameField.setBounds(430, 310, 218, 39);
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);

 		JTextField lastNameField = new JTextField();
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lastNameField.setBounds(431, 378, 218, 39);
		frame.getContentPane().add(lastNameField);
		lastNameField.setColumns(10);

 		JTextField usernameField = new JTextField();
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameField.setBounds(431, 449, 218, 38);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		firstLabel.setBounds(49, 304, 294, 42);
		frame.getContentPane().add(firstLabel);
		
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lastLabel.setBounds(49, 372, 294, 42);
		frame.getContentPane().add(lastLabel);
		
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField.setColumns(10);
		passwordField.setBounds(431, 514, 218, 38);
		frame.getContentPane().add(passwordField);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		usernameLabel.setBounds(49, 443, 294, 42);
		frame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		passwordLabel.setBounds(49, 508, 294, 42);
		frame.getContentPane().add(passwordLabel);
		
		JLabel errText = new JLabel("");
		errText.setHorizontalAlignment(SwingConstants.CENTER);
		errText.setFont(new Font("Times New Roman", Font.BOLD, 20));
		errText.setForeground(Color.RED);
		errText.setBounds(0, 550, 501, 58);
		frame.getContentPane().add(errText);
		
		JButton createBtn = new JButton("Create Account");
		createBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		createBtn.setBounds(267, 637, 218, 48);
		frame.getContentPane().add(createBtn);
		createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                String fname = firstNameField.getText();
                String lname = lastNameField.getText();
                boolean created = client.createAcc(user, pwd, fname, lname);
                
                if(created) {
                    frame.dispose();
                    ClientVersion cv = new ClientVersion(client);
                }
                else
                {
                    errText.setText("That username is taken.");
                }
          }
        });
 	}
}
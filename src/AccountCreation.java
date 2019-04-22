//Matthew Yaswinski & Joshua Del Toro
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

		//large text describing GUI
 		JLabel iDFText = new JLabel("Enter the following information");
		iDFText.setHorizontalAlignment(SwingConstants.CENTER);
		iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
		iDFText.setBounds(148, 186, 501, 58);
		frame.getContentPane().add(iDFText);

		//button to return to login screen
 		JButton exitBtn = new JButton("Exit");
		exitBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		exitBtn.setBounds(457, 100, 218, 42);
		frame.getContentPane().add(exitBtn);
		//returns to login frame
		exitBtn.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) { 
                frame.dispose();
                client.endConnection();
                ClientLogin c = new ClientLogin();
            }
        });

		//text field for first name
 		JTextField firstNameField = new JTextField();
		firstNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		firstNameField.setBounds(430, 310, 218, 39);
		firstNameField.setText("");
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);

		//text field for last name
 		JTextField lastNameField = new JTextField();
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lastNameField.setBounds(431, 378, 218, 39);
		lastNameField.setText("");
		frame.getContentPane().add(lastNameField);
		lastNameField.setColumns(10);

		//text field for username
 		JTextField usernameField = new JTextField();
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameField.setBounds(431, 449, 218, 38);
		usernameField.setText("");
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		//label for first name
		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		firstLabel.setBounds(49, 304, 294, 42);
		frame.getContentPane().add(firstLabel);
		
		//label for last name
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lastLabel.setBounds(49, 372, 294, 42);
		frame.getContentPane().add(lastLabel);
		
		//text field for password
		JPasswordField passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField.setColumns(10);
		passwordField.setBounds(431, 514, 218, 38);
		passwordField.setText("");
		frame.getContentPane().add(passwordField);
		
		//label for username
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		usernameLabel.setBounds(49, 443, 294, 42);
		frame.getContentPane().add(usernameLabel);
		
		//label for password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		passwordLabel.setBounds(49, 508, 294, 42);
		frame.getContentPane().add(passwordLabel);
		
		//label for error messages
		JLabel errText = new JLabel("");
		errText.setHorizontalAlignment(SwingConstants.CENTER);
		errText.setFont(new Font("Times New Roman", Font.BOLD, 20));
		errText.setForeground(Color.RED);
		errText.setBounds(12, 550, 773, 58);
		errText.setText(" ");
		frame.getContentPane().add(errText);
		
		//button to create an account
		JButton createBtn = new JButton("Create Account");
		createBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		createBtn.setBounds(267, 637, 218, 48);
		frame.getContentPane().add(createBtn);
		//creates the account
		createBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pwd = String.valueOf(passwordField.getPassword());
                String fname = firstNameField.getText();
                String lname = lastNameField.getText();
                boolean created = false;
                if (!user.equals("") || !pwd.equals("") || !fname.equals("") || !lname.equals("")) {
                	created = client.createAcc(user, pwd, fname, lname);
                }
                
                //if no errors when entering information
                if(created) {
                    frame.dispose();
                    ClientVersion cv = new ClientVersion(client, user);
                }
                else
                {
                    errText.setText("You must enter an untaken username and enter text for each field");
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
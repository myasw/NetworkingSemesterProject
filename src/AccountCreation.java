import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class AccountCreation {
	public AccountCreation(){
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
		firstNameField.setBounds(90, 334, 218, 39);
		frame.getContentPane().add(firstNameField);
		firstNameField.setColumns(10);

 		JTextField lastNameField = new JTextField();
		lastNameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lastNameField.setBounds(90, 403, 218, 39);
		frame.getContentPane().add(lastNameField);
		lastNameField.setColumns(10);

 		JTextField usernameField = new JTextField();
		usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameField.setBounds(90, 482, 218, 38);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);

 		JButton createBtn = new JButton("Create Account");
		createBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		createBtn.setBounds(270, 643, 218, 48);
		frame.getContentPane().add(createBtn);
		
		JLabel firstLabel = new JLabel("First Name");
		firstLabel.setHorizontalAlignment(SwingConstants.CENTER);
		firstLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		firstLabel.setBounds(381, 328, 294, 42);
		frame.getContentPane().add(firstLabel);
		
		JLabel lastLabel = new JLabel("Last Name");
		lastLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lastLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lastLabel.setBounds(381, 397, 294, 42);
		frame.getContentPane().add(lastLabel);
		
		JTextField passwordField = new JTextField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		passwordField.setColumns(10);
		passwordField.setBounds(90, 552, 218, 38);
		frame.getContentPane().add(passwordField);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		usernameLabel.setBounds(381, 482, 294, 42);
		frame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		passwordLabel.setBounds(381, 548, 294, 42);
		frame.getContentPane().add(passwordLabel);
 	}
}
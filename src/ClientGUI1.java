import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JMenu;
import java.awt.Panel;
import javax.swing.JPanel;

 public class ClientGUI1 {

 	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JButton btnNewButton_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton_2;
	private JTextField textField_4;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_5;

 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI1 window = new ClientGUI1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

 	/**
	 * Create the application.
	 */
	public ClientGUI1() {
		initialize();
	}

 	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Banking GUI");
		frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		frame.setBounds(0, 0, 800, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

 		JLabel lblNewLabel = new JLabel("Hello...\nWhat would you like to do?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setBounds(142, 187, 501, 58);
		frame.getContentPane().add(lblNewLabel);

 		textField = new JTextField();
		textField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField.setBounds(90, 42, 218, 33);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

 		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_1.setBounds(457, 42, 218, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

 		lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_1.setBounds(90, 15, 218, 15);
		frame.getContentPane().add(lblNewLabel_1);

 		lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblNewLabel_2.setBounds(457, 12, 218, 21);
		frame.getContentPane().add(lblNewLabel_2);

 		JButton btnNewButton = new JButton("Connect");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(90, 102, 218, 38);
		frame.getContentPane().add(btnNewButton);

 		btnNewButton_1 = new JButton("Disconnect");
		btnNewButton_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton_1.setBounds(457, 100, 218, 42);
		frame.getContentPane().add(btnNewButton_1);

 		textField_2 = new JTextField();
		textField_2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_2.setBounds(90, 334, 218, 39);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);

 		textField_3 = new JTextField();
		textField_3.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_3.setBounds(457, 334, 218, 39);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);

 		btnNewButton_2 = new JButton("Freeze Credit Card");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_2.setBounds(457, 460, 218, 48);
		frame.getContentPane().add(btnNewButton_2);

 		textField_4 = new JTextField();
		textField_4.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_4.setBounds(457, 558, 218, 38);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);

 		btnNewButton_3 = new JButton("Balance");
		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_3.setBounds(90, 385, 218, 38);
		frame.getContentPane().add(btnNewButton_3);

 		btnNewButton_4 = new JButton("Credit Score");
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_4.setBounds(457, 385, 218, 38);
		frame.getContentPane().add(btnNewButton_4);

 		btnNewButton_5 = new JButton("Transfer To...");
		btnNewButton_5.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_5.setBounds(457, 608, 218, 48);
		frame.getContentPane().add(btnNewButton_5);

 		JPanel panel = new JPanel();
		panel.setBounds(105, 526, 186, 99);
		frame.getContentPane().add(panel);



 		ButtonGroup group = new ButtonGroup();
		JRadioButton checkingButton = new JRadioButton("Checking");
		checkingButton.setHorizontalAlignment(SwingConstants.CENTER);
		checkingButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JRadioButton savingsButton = new JRadioButton("Savings");
		savingsButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		savingsButton.setHorizontalAlignment(SwingConstants.CENTER);
		group.add(checkingButton);
		group.add(savingsButton);

 		panel.add(checkingButton);
		panel.add(savingsButton);



 	}
}
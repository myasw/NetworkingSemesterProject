import javax.swing.*;
import java.awt.*;

 public class ClientMobile {

 	private JFrame frame;
	private JTextField userTextField;
	private JTextField textField_1;
	private JLabel userLabel;
	private JLabel pswdTextField;
	private JButton disconnectBtn;
	private JTextField balanceField;
	private JTextField scoreField;
	private JButton freezeBtn;
	private JTextField transferField;
	private JButton balanceBtn;
	private JButton scoreBtn;
	private JButton transferBtn;
	private JButton newAcctBtn;

 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMobile window = new ClientMobile();
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
	public ClientMobile() {
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

 		JLabel iDFText = new JLabel("Hello...\nWhat would you like to do?");
		iDFText.setHorizontalAlignment(SwingConstants.CENTER);
		iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
		iDFText.setBounds(148, 152, 501, 58);
		frame.getContentPane().add(iDFText);

 		userTextField = new JTextField();
		userTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		userTextField.setBounds(90, 42, 218, 33);
		frame.getContentPane().add(userTextField);
		userTextField.setColumns(10);

 		textField_1 = new JTextField();
		textField_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textField_1.setBounds(457, 42, 218, 33);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

 		userLabel = new JLabel("Username");
		userLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
		userLabel.setBounds(90, 15, 218, 15);
		frame.getContentPane().add(userLabel);

 		pswdTextField = new JLabel("Password");
		pswdTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pswdTextField.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pswdTextField.setBounds(457, 12, 218, 21);
		frame.getContentPane().add(pswdTextField);

 		JButton connectBtn = new JButton("Connect");
		connectBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		connectBtn.setBounds(90, 102, 218, 38);
		frame.getContentPane().add(connectBtn);

 		disconnectBtn = new JButton("Disconnect");
		disconnectBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		disconnectBtn.setBounds(457, 100, 218, 42);
		frame.getContentPane().add(disconnectBtn);

 		balanceField = new JTextField();
		balanceField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		balanceField.setBounds(90, 334, 218, 39);
		frame.getContentPane().add(balanceField);
		balanceField.setColumns(10);

 		scoreField = new JTextField();
		scoreField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		scoreField.setBounds(457, 334, 218, 39);
		frame.getContentPane().add(scoreField);
		scoreField.setColumns(10);

 		freezeBtn = new JButton("Freeze Credit Card");
		freezeBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		freezeBtn.setBounds(457, 460, 218, 48);
		frame.getContentPane().add(freezeBtn);

 		transferField = new JTextField();
		transferField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		transferField.setBounds(457, 558, 218, 38);
		frame.getContentPane().add(transferField);
		transferField.setColumns(10);

 		balanceBtn = new JButton("Balance");
		balanceBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		balanceBtn.setBounds(90, 385, 218, 38);
		frame.getContentPane().add(balanceBtn);

 		scoreBtn = new JButton("Credit Score");
		scoreBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		scoreBtn.setBounds(457, 385, 218, 38);
		frame.getContentPane().add(scoreBtn);

 		transferBtn = new JButton("Transfer To...");
		transferBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		transferBtn.setBounds(457, 608, 218, 48);
		frame.getContentPane().add(transferBtn);

 		JPanel radioPanel = new JPanel();
		radioPanel.setBounds(105, 526, 186, 99);
		frame.getContentPane().add(radioPanel);



 		ButtonGroup group = new ButtonGroup();
		JRadioButton checkingBtn = new JRadioButton("Checking");
		checkingBtn.setHorizontalAlignment(SwingConstants.CENTER);
		checkingBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JRadioButton savingsBtn = new JRadioButton("Savings");
		savingsBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		savingsBtn.setHorizontalAlignment(SwingConstants.CENTER);
		group.add(checkingBtn);
		group.add(savingsBtn);

 		radioPanel.add(checkingBtn);
		radioPanel.add(savingsBtn);
		
		newAcctBtn = new JButton("New Account");
		newAcctBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
		newAcctBtn.setBounds(281, 242, 218, 38);
		frame.getContentPane().add(newAcctBtn);



 	}
}
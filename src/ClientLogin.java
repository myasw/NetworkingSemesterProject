import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

 public class ClientLogin {

    private JFrame frame;
    private JTextField userTextField;
    private JPasswordField pswdTextField;
    private JLabel userLabel;
    private JLabel pswdLabel;
    private JButton newAcctBtn;
    private JButton connectBtn;
    private JTextField ipTextField;
    BankClient client;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClientLogin window = new ClientLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public ClientLogin() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Login");
        frame.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 14));
        frame.setBounds(0, 0, 800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setVisible(true);
        
        userTextField = new JTextField();
        userTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        userTextField.setBounds(90, 42, 218, 33);
        frame.getContentPane().add(userTextField);
        userTextField.setColumns(10);
        
        pswdTextField = new JPasswordField();
        pswdTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pswdTextField.setBounds(457, 42, 218, 33);
        frame.getContentPane().add(pswdTextField);
        pswdTextField.setColumns(10);
        
        userLabel = new JLabel("Username");
        userLabel.setHorizontalAlignment(SwingConstants.CENTER);
        userLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        userLabel.setBounds(90, 15, 218, 33);
        frame.getContentPane().add(userLabel);
        
        pswdLabel = new JLabel("Password");
        pswdLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pswdLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        pswdLabel.setBounds(457, 12, 218, 33);
        frame.getContentPane().add(pswdLabel);
        
        JLabel ipLabel = new JLabel("IP Address of Server");
        ipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ipLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        ipLabel.setBounds(457, 117, 218, 33);
        frame.getContentPane().add(ipLabel);
        
        ipTextField = new JTextField();
        ipTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        ipTextField.setColumns(10);
        ipTextField.setBounds(457, 144, 218, 33);
        frame.getContentPane().add(ipTextField);
        
        JLabel errText = new JLabel("");
		errText.setFont(new Font("Times New Roman", Font.BOLD, 20));
		errText.setForeground(Color.RED);
		errText.setBounds(90, 125, 501, 58);
		frame.getContentPane().add(errText);
        
        connectBtn = new JButton("Connect");
        connectBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
        connectBtn.setBounds(90, 102, 218, 38);
        frame.getContentPane().add(connectBtn);
        connectBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String user = userTextField.getText();
                String pwd = String.valueOf(pswdTextField.getPassword());
                String ip = ipTextField.getText();
                client = new BankClient(ip);
                boolean verified = client.verifyUser(user, pwd);
                if(verified) {
                    frame.dispose();
                    ClientVersion cv = new ClientVersion(client, user);
                }
                else
                {
                    errText.setText("Username or password is incorrect.");
                    client.endConnection();
                }
          }
        });

        newAcctBtn = new JButton("New Account");
        newAcctBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        newAcctBtn.setBounds(281, 242, 218, 38);
        frame.getContentPane().add(newAcctBtn);
        newAcctBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                String ip = ipTextField.getText();
                client = new BankClient(ip);
                AccountCreation ac = new AccountCreation(client);
          }
        });
    }
}

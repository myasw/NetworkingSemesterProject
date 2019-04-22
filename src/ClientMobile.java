//Matthew Yaswinski & Joshua Del Toro
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
        
        String name = client.getData(user, "02"); //Print the name of the user
        
        //text printing the user's name
        JLabel iDFText = new JLabel("Hello " + name + ".");
        iDFText.setHorizontalAlignment(SwingConstants.CENTER);
        iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
        iDFText.setBounds(148, 152, 501, 58);
        frame.getContentPane().add(iDFText);
        
        //text asking the user to do an action
        JLabel iDFText2 = new JLabel("What would you like to do?");
        iDFText2.setHorizontalAlignment(SwingConstants.CENTER);
        iDFText2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        iDFText2.setBounds(148, 220, 501, 58);
        frame.getContentPane().add(iDFText2);
        
        //button for disconnecting from the server
        JButton disconnectBtn = new JButton("Disconnect");
        disconnectBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
        disconnectBtn.setBounds(457, 100, 218, 42);
        frame.getContentPane().add(disconnectBtn);
        //disconnects from the server
        disconnectBtn.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) { 
                frame.dispose();
                client.endConnection();
                ClientLogin c = new ClientLogin();
            }
        });
        
        //field to display the balance of an account
        JTextField balanceField = new JTextField();
        balanceField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        balanceField.setBounds(90, 334, 218, 39);
        frame.getContentPane().add(balanceField);
        balanceField.setColumns(10);
        
        //button for displaying the balance of an account
        JButton balanceBtn = new JButton("Balance");
        balanceBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        balanceBtn.setBounds(90, 385, 218, 38);
        frame.getContentPane().add(balanceBtn);
        
        //field to display the credit score of a user account
        JTextField scoreField = new JTextField();
        scoreField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        scoreField.setBounds(457, 334, 218, 39);
        frame.getContentPane().add(scoreField);
        scoreField.setColumns(10);

        //button to display the freeze status of a user account
        JButton freezeBtn = new JButton("Freeze/Unfreeze Account");
        freezeBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        freezeBtn.setBounds(423, 460, 291, 48);
        frame.getContentPane().add(freezeBtn);
        //displays freeze status of a user account
        freezeBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                    String s = client.changeData(user, "", "11");
            }
    
        });

        //field for entering the amount of money to be transferred
        JTextField transferField = new JTextField();
        transferField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        transferField.setBounds(457, 558, 218, 38);
        frame.getContentPane().add(transferField);
        transferField.setColumns(10);

        //button to display the credit score of a user account
        JButton scoreBtn = new JButton("Credit Score");
        scoreBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        scoreBtn.setBounds(457, 385, 218, 38);
        frame.getContentPane().add(scoreBtn);
        //displays the credit score of a user account
        scoreBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                    scoreField.setText(client.getData(user, "09"));
            }
    
        });

        //button for transferring money between accounts
        JButton transferBtn = new JButton("Transfer To...");
        transferBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        transferBtn.setBounds(457, 608, 218, 48);
        frame.getContentPane().add(transferBtn);
        
        //panel to hold radio buttons for the two accounts
        JPanel radioPanel2 = new JPanel();
        radioPanel2.setBounds(105, 526, 186, 99);
        frame.getContentPane().add(radioPanel2);
        
        //label for dispaying error messages
        JLabel errLabel = new JLabel("");
        errLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        errLabel.setForeground(Color.RED);
        errLabel.setBounds(90, 435, 218, 39);
        frame.getContentPane().add(errLabel);
        
        //label for displaying error messages
        JLabel errLabel2 = new JLabel("");
        errLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        errLabel2.setForeground(Color.RED);
        errLabel2.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        errLabel2.setBounds(90, 472, 218, 39);
        frame.getContentPane().add(errLabel2);
        
        //for adding radio buttons to a group
        ButtonGroup group2 = new ButtonGroup();
        JRadioButton checkingBtn2 = new JRadioButton("Checking");
        checkingBtn2.setHorizontalAlignment(SwingConstants.CENTER);
        checkingBtn2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        JRadioButton savingsBtn2 = new JRadioButton("Savings");
        savingsBtn2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        savingsBtn2.setHorizontalAlignment(SwingConstants.CENTER);
        //add the two buttons to the group
        group2.add(checkingBtn2);
        group2.add(savingsBtn2);

        //add the buttons to the radio panel
        radioPanel2.add(checkingBtn2);
        radioPanel2.add(savingsBtn2);
        
        //displays the balance of checking or savings account
        balanceBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (checkingBtn2.isSelected()) { //checking
                    balanceField.setText("$" + client.getData(user, "03"));
                    errLabel.setText("");
                    errLabel2.setText("");
                }
                else if (savingsBtn2.isSelected()) { //savings
                    balanceField.setText("$" + client.getData(user, "04"));
                    errLabel.setText("");
                    errLabel2.setText("");
                }
            }
    
        });
        
        //transfers money between the two accounts
        transferBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(client.isFrozen(user)) { //display error message if user account is frozen
                    errLabel.setText("Cannot alter balance");
                    errLabel2.setText("while frozen.");
                }
                else
                {
                    String tranValue = transferField.getText();
                    tranValue = tranValue.replaceAll("[^\\d.]", ""); //remove characters that are not digits or decimal points
                    //checking
                    if (checkingBtn2.isSelected() && !tranValue.equals("") && tranValue.matches("^[0-9]*\\.?[0-9]*$") && tranValue.matches(".*[0-9].*")) {
                        //if amount to withdraw is higher than balance
                    	if (Double.parseDouble(client.getData(user, "04")) < Double.parseDouble(tranValue)) {
                            errLabel.setText("Cannot withdraw more");
                            errLabel2.setText("than in account.");
                        } else {
                            client.changeData(user, tranValue, "08");
                            client.changeData(user, tranValue, "05");
                            errLabel.setText("");
                            errLabel2.setText("");
                        }
                    //savings
                    } else if (savingsBtn2.isSelected() && !tranValue.equals("") && tranValue.matches("^[0-9]*\\.?[0-9]*$") && tranValue.matches(".*[0-9].*")) {
                        if (Double.parseDouble(client.getData(user, "03")) < Double.parseDouble(tranValue)) {
                            errLabel.setText("Cannot withdraw more");
                            errLabel2.setText("than in account");
                        } else {
                            client.changeData(user, tranValue, "07");
                            client.changeData(user, tranValue, "06");
                            errLabel.setText("");
                            errLabel2.setText("");
                        }
                    //too many decimal points
                    } else if (!tranValue.matches("^[0-9]*\\.?[0-9]*$")) {
                            errLabel.setText("Invalid input:");
                        errLabel2.setText("too many decimal points");
                    //no digits entered
                    } else if (!tranValue.matches(".*[0-9].*")) {
                        errLabel.setText("Invalid input:");
                        errLabel2.setText("not enough digits");
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
//Matthew Yaswinski & Joshua Del Toro
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
        
        String name = client.getData(user, "02"); //full name of user
        
        //for printing full name of user
        JLabel iDFText = new JLabel("Hello " + name + ".");
        iDFText.setHorizontalAlignment(SwingConstants.CENTER);
        iDFText.setFont(new Font("Times New Roman", Font.BOLD, 30));
        iDFText.setBounds(148, 152, 501, 58);
        frame.getContentPane().add(iDFText);
        
        //prompting user to do an action
        JLabel iDFText2 = new JLabel("What would you like to do?");
        iDFText2.setHorizontalAlignment(SwingConstants.CENTER);
        iDFText2.setFont(new Font("Times New Roman", Font.BOLD, 30));
        iDFText2.setBounds(148, 220, 501, 58);
        frame.getContentPane().add(iDFText2);
        
        //button for disconnecting from server
        JButton disconnectBtn = new JButton("Disconnect");
        disconnectBtn.setFont(new Font("Times New Roman", Font.BOLD, 18));
        disconnectBtn.setBounds(457, 100, 218, 42);
        frame.getContentPane().add(disconnectBtn);
        //disconnects from server
        disconnectBtn.addActionListener(new ActionListener() 
        { 
            public void actionPerformed(ActionEvent e) { 
                frame.dispose();
                client.endConnection();
                ClientLogin c = new ClientLogin();
            }
        });
        
        //field for displaying the balance of an account
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
        
        //field for entering an amount of money to deposit into an account
        JTextField depositField = new JTextField();
        depositField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        depositField.setBounds(457, 334, 218, 39);
        frame.getContentPane().add(depositField);
        depositField.setColumns(10);

        //field for entering an amount to withdraw from an account
        JTextField withdrawField = new JTextField();
        withdrawField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        withdrawField.setBounds(457, 459, 218, 38);
        frame.getContentPane().add(withdrawField);
        withdrawField.setColumns(10);
        
        //button for depositing money into an account
        JButton depositBtn = new JButton("Deposit");
        depositBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        depositBtn.setBounds(457, 385, 218, 38);
        frame.getContentPane().add(depositBtn);

        //button for withdrawing money from an account
        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setFont(new Font("Times New Roman", Font.BOLD, 20));
        withdrawBtn.setBounds(457, 509, 218, 48);
        frame.getContentPane().add(withdrawBtn);
        
        //panel to hold radio buttons for the two accounts
        JPanel radioPanel2 = new JPanel();
        radioPanel2.setBounds(105, 526, 186, 99);
        frame.getContentPane().add(radioPanel2);
        
        //for adding radio buttons to a group
        ButtonGroup group2 = new ButtonGroup();
        JRadioButton checkingBtn2 = new JRadioButton("Checking");
        checkingBtn2.setHorizontalAlignment(SwingConstants.CENTER);
        checkingBtn2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        JRadioButton savingsBtn2 = new JRadioButton("Savings");
        savingsBtn2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        savingsBtn2.setHorizontalAlignment(SwingConstants.CENTER);
        //add the radio buttons to the group
        group2.add(checkingBtn2);
        group2. add(savingsBtn2);

        //add the radio buttons to the panel
        radioPanel2.add(checkingBtn2);
        radioPanel2.add(savingsBtn2);
        
        //label for displaying error messages
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
        
        //displays the balance of an account
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
        
        //deposits money into an account
        depositBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	//checks if user account is frozen
                if(client.isFrozen(user)) {
                    errLabel.setText("Cannot alter balance");
                    errLabel2.setText("while frozen.");
                }
                else
                {
                    String depValue = depositField.getText();
                    depValue = depValue.replaceAll("[^\\d.]", ""); //removes everything except digits and decimal points
                    //checking
                    if (checkingBtn2.isSelected() && !depValue.equals("") && depValue.matches("^[0-9]*\\.?[0-9]*$") && depValue.matches(".*[0-9].*")) {
                        client.changeData(user, depValue, "05");
                        errLabel.setText("");
                        errLabel2.setText("");
                    }
                    //savings
                    else if (savingsBtn2.isSelected() && !depValue.equals("") && depValue.matches("^[0-9]*\\.?[0-9]*$") && depValue.matches(".*[0-9].*")) {
                        client.changeData(user, depValue, "06");
                        errLabel.setText("");
                        errLabel2.setText("");
                    //if too many decimal points
                    } else if (!depValue.matches("^[0-9]*\\.?[0-9]*$")) {
                        errLabel.setText("Invalid input:");
                        errLabel2.setText("too many decimal points");
                    //if not enough digits
                    } else if (!depValue.matches(".*[0-9].*")) {
                        errLabel.setText("Invalid input:");
                        errLabel2.setText("not enough digits");
                    }
                }
            }
        });
        
        //withdraws money from an account
        withdrawBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
            	//checks if account is frozen
                if(client.isFrozen(user)) {
                    errLabel.setText("Cannot alter balance");
                    errLabel2.setText("while frozen.");
                }
                else
                {
                    String withValue = withdrawField.getText();
                    withValue = withValue.replaceAll("[^\\d.]", ""); //removes all values that are not digits or decimal points
                    //checking
                    if (checkingBtn2.isSelected() && !withValue.equals("") && withValue.matches("^[0-9]*\\.?[0-9]*$") && withValue.matches(".*[0-9].*")) {
                    	//if value to be withdrawn is not larger than current balance
                        if (Double.parseDouble(client.getData(user, "03")) < Double.parseDouble(withValue)) {
                            errLabel.setText("Cannot withdraw more");
                            errLabel2.setText("than in account");
                        } else {
                            client.changeData(user, withValue, "07");
                            errLabel.setText("");
                            errLabel2.setText("");
                        }
                    //savings
                    } else if (savingsBtn2.isSelected() && !withValue.equals("") && withValue.matches("^[0-9]*\\.?[0-9]*$") && withValue.matches(".*[0-9].*")) {
                    	//if value to be withdrawn is not larger than current balance
                        if (Double.parseDouble(client.getData(user, "04")) < Double.parseDouble(withValue)) {
                            errLabel.setText("Cannot withdraw more");
                            errLabel2.setText("than in account");
                        } else {
                            client.changeData(user, withValue, "08");
                            errLabel.setText("");
                            errLabel2.setText("");
                        }
                    //if too many decimal points
                    } else if (!withValue.matches("^[0-9]*\\.?[0-9]*$")) {
                        errLabel.setText("Invalid input:");
                        errLabel2.setText("too many decimal points");
                    //if not enough digits
                    } else if (!withValue.matches(".*[0-9].*")) {
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
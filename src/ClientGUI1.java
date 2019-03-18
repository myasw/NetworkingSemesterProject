import javax.swing.*;
import java.awt.*;

public class ClientGUI1 {

    private JFrame frame;
    private JTextField textFields[] = new JTextField[5];
    private JLabel labels[] = new JLabel[3];
    private JButton buttons[] = new JButton[6];

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
        
        //Locations of text fields
        int[][] tf_bounds = {{90, 42, 218, 33},
                            {457, 42, 218, 33},
                            {90, 334, 218, 39},
                            {457, 334, 218, 39},
                            {457, 558, 218, 38}};
                            
        //Create all text fields
        int k = 0;
        for(JTextField tf : textFields)
        {
            tf = new JTextField();
            tf.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            tf.setBounds(tf_bounds[k][0], tf_bounds[k][1], tf_bounds[k][2], tf_bounds[k][3]);
            frame.getContentPane().add(tf);
            tf.setColumns(10);
            k++;
        }
        
        //Locations of labels
        int[][] l_bounds = {{142, 187, 501, 58},{90, 15, 218, 15},{457, 12, 218, 21}};
                            
        //Text for labels
        String[] textL = {"Hello...\nWhat would you like to do?","Username","Password"};
            
        //Font sizes
        int[] fontSizes = {30,20,20};
        
        //Create all labels
        k=0;
        for(JLabel l : labels)
        {
            l = new JLabel(textL[k]);
            l.setHorizontalAlignment(SwingConstants.CENTER);
            l.setFont(new Font("Times New Roman", Font.BOLD, fontSizes[k]));
            l.setBounds(l_bounds[k][0], l_bounds[k][1], l_bounds[k][2], l_bounds[k][3]);
            frame.getContentPane().add(l);
            k++;
        }
        
        //Text for buttons
        String[] textB = {"Connect", "Disconnect", "Freeze Credit Card",
        "Balance","Credit Score","Transfer To..."};
        int[][] b_bounds = {{90, 102, 218, 38},{457, 100, 218, 42},{457, 460, 218, 48},
        {90, 385, 218, 38},{457, 385, 218, 38},{457, 608, 218, 48}};
        
        //Create all buttons
        k=0;
        for(JButton b : buttons)
        {
            b = new JButton(textB[k]);
            b.setFont(new Font("Times New Roman", Font.BOLD, 18));
            b.setBounds(b_bounds[k][0], b_bounds[k][1], b_bounds[k][2], b_bounds[k][3]);
            frame.getContentPane().add(b);
            k++;
        }
        
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
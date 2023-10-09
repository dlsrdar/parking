package park;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MobilePayGUI extends JFrame implements ActionListener {
    private JLabel emailLabel, passwordLabel, otpLabel;
    private JTextField emailField, passwordField, otpField;
    private JButton payButton;

    public MobilePayGUI() {
        super("Mobile Pay GUI");

        // Set layout for the frame
        setLayout(new GridLayout(4, 2));

        // Create and add components to the frame
        emailLabel = new JLabel("Email ID:");
        emailField = new JTextField();
        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        otpLabel = new JLabel("OTP:");
        otpField = new JTextField();
        payButton = new JButton("Pay");

        add(emailLabel);
        add(emailField);
        add(passwordLabel);
        add(passwordField);
        add(otpLabel);
        add(otpField);
        add(new JLabel());
        add(payButton);

        // Add action listener to the pay button
        payButton.addActionListener(this);

        // Set the size and visibility of the frame
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Implement actionPerformed method for ActionListener interface
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payButton) {
            // Add code to process payment here
            JOptionPane.showMessageDialog(null, "Payment successful!");
            dispose();
        }
    }

  
}


package park;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class creditGUI extends JFrame implements ActionListener {
    // GUI components
    private JLabel cardNumberLabel, expiryDateLabel, cvvLabel;
    private JTextField cardNumberField, expiryDateField, cvvField;
    private JButton payButton;

    public creditGUI() {
        // Set up the window
        super("Debit Card Payment");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Create the GUI components
        cardNumberLabel = new JLabel("Credit Card Number:");
        cardNumberField = new JTextField(20);
        expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        expiryDateField = new JTextField(5);
        cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField(3);
        payButton = new JButton("Pay");
        payButton.addActionListener(this);

        // Create a layout for the components
        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(expiryDateLabel);
        panel.add(expiryDateField);
        panel.add(cvvLabel);
        panel.add(cvvField);
        panel.add(new JLabel());
        panel.add(payButton);

        // Add the panel to the window
        setContentPane(panel);
        setVisible(true);
    }

    // Handle the pay button click event
    public void actionPerformed(ActionEvent e) {
        // Get the card details from the text fields
        String cardNumber = cardNumberField.getText();
        String expiryDate = expiryDateField.getText();
        String cvv = cvvField.getText();

        // Process the payment using the card details
        // TODO: implement payment processing logic here

        // Show a confirmation message
        JOptionPane.showMessageDialog(this, "Payment processed successfully!");
        dispose();
    }

   
}

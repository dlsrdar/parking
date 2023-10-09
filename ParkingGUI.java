package park;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ParkingGUI extends JFrame implements ActionListener {
    
    // GUI components
    protected JLabel labelPlate, labelLot, labelAvailable;
    protected JTextField fieldPlate;
    protected JComboBox<Integer> comboLot;
    protected JButton buttonSubmit;
    
    public ParkingGUI() {
    	
    
        // Set up the frame
        setTitle("Parking");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the components
        labelPlate = new JLabel("License Plate Number:");
        labelLot = new JLabel("Parking Lot Number:");
        labelAvailable = new JLabel("Parking spot available.");
        fieldPlate = new JTextField(10);
        Integer[] lots = {1, 2}; // Replace with actual lot numbers
        comboLot = new JComboBox<>(lots);
        buttonSubmit = new JButton("Submit");
        buttonSubmit.addActionListener(this);
        
        // Create the layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(labelPlate);
        panel.add(fieldPlate);
        panel.add(labelLot);
        panel.add(comboLot);
        panel.add(labelAvailable);
        panel.add(buttonSubmit);
        
        // Add the panel to the frame
        add(panel);
        
        // Make the frame visible
        setVisible(true);
    }
   
    // Handle button click events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSubmit) {
            // Get the information from the GUI
        	
        	/*
        	Req8: To book a parking space, clients need to provide a valid licence plate number. A client can edit or
			cancel her/his bookings before the starting time of a booking.
        	 * **/
            String plate = fieldPlate.getText();
            int lot = (int) comboLot.getSelectedItem();
            
            // Check if the parking spot is available
            boolean available = checkAvailability(plate, lot);
            
            
            
            // OBSERVER Design Pattern Here
            SuperManager superManager = new SuperManager();
            ManagementTeam managementTeam = new ManagementTeam(superManager);
            for(int i = 0; i < 5; i++) {
            	managementTeam.AutoAccountGeneration();
            }
            
            for(int j = 0; j < 100; j++) {
            	managementTeam.addParking();
            }
            
            
            // Update the label to display the availability
            if (available) {
                labelAvailable.setText("Parking spot available.");
                
            } 
            
            // Update the label to display the availability
            if (available) {
                labelAvailable.setText("Parking spot available.");
                
                Parkingcharges park = new Parkingcharges();
                dispose();
            } 
            dispose();
        }
    }
    
    // Replace this method with actual availability checking code
    protected boolean checkAvailability(String plate, int lot) {
        // Always returns true for demonstration purposes
        return true;
    }
   
}

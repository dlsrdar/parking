package park;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Pattern;



public class Parkingcharges extends JFrame implements ActionListener {

	protected JLabel costLabel, typeLabel, hoursLabel, depositLabel, lblPayment;
	protected JComboBox typeComboBox, paymentComboBox;
	protected JTextField hoursTextField, depositTextField,rateTextField;
	protected JButton bookButton;

	public Parkingcharges() {
		setTitle("Parking Booking");
		setSize(500, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		//setLayout(new GridLayout(5, 2));


		JPanel contentPane = new JPanel();
		contentPane.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;


		c.gridy++;
		c.gridwidth = 1;

		//lblLicensePlate = new JLabel("License Plate:");
		//contentPane.add(lblLicensePlate, c);

		//c.gridx++;

		//txtLicensePlate = new JTextField(10);
		//contentPane.add(txtLicensePlate, c);


		c.gridx = 0;
		c.gridy++;
		typeLabel = new JLabel("Type of client:");
		contentPane.add(typeLabel, c);
		c.gridx++;
		String[] clientTypes = { "Student", "Faculty Member", "Non-faculty Staff", "Visitor" };
		typeComboBox = new JComboBox(clientTypes);
		contentPane.add(typeComboBox, c);

		c.gridx = 0;
		c.gridy++;
		hoursLabel = new JLabel("Number of hours:");
		contentPane.add(hoursLabel, c);
		c.gridx++;
		hoursTextField = new JTextField(10);
		contentPane.add(hoursTextField, c);

		c.gridx = 0;
		c.gridy++;
		costLabel = new JLabel("Cost per hour:");
		contentPane.add(costLabel, c);
		c.gridx++;
		rateTextField = new JTextField(3);
		rateTextField.setEditable(false);
		contentPane.add(rateTextField, c);

		c.gridx = 0;
		c.gridy++;
		depositLabel = new JLabel("Total: ");
		contentPane.add(depositLabel, c);
		c.gridx++;
		depositTextField = new JTextField(30);
		depositTextField.setEditable(false);
		contentPane.add(depositTextField, c);

		c.gridx = 0;
		c.gridy++;
		lblPayment = new JLabel("Payment Method:");
		contentPane.add(lblPayment, c);
		c.gridx++;
		paymentComboBox = new JComboBox<String>(new String[]{"Debit Card", "Credit Card", "Mobile Payment"});
		contentPane.add(paymentComboBox, c);

		c.gridx = 0;
		c.gridy++;
		c.gridwidth = 2;
		c.insets = new Insets(20, 0, 0, 0);
		bookButton = new JButton("Book Parking");
		bookButton.addActionListener(this);
		contentPane.add(bookButton, c);

		setContentPane(contentPane);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent event) {


		// Decorator with interface parkingrate and classes implementing the interface 

		/*
			Req4: Booking a parking space requires the cost of an hour (of the type of a client) as the deposit, which will
			not be refunded if a no-show happens in the first 1 hour of the booked parking period. Otherwise, the deposit
			will be deducted when checking out.
		 * **/

		String payment = (String) paymentComboBox.getSelectedItem();
		ParkingRateDecorator prd;

		if (event.getSource() == bookButton) {
			int cost = 0;
			switch (typeComboBox.getSelectedIndex()) {
			case 0:
				ParkingRate sr = new StudentRate();
				prd = new EarlyBirdDecorator(sr);
				cost = prd.calculateRate(1);
				rateTextField.setText("$" + cost);
				break;
			case 1:
				ParkingRate fr = new FacultyRate();
				prd = new EarlyBirdDecorator(fr);
				cost = prd.calculateRate(1);
				rateTextField.setText("$" + cost);
				break;
			case 2:
				ParkingRate sar = new StaffRate();
				prd = new EarlyBirdDecorator(sar);
				cost = prd.calculateRate(1);
				rateTextField.setText("$" + cost);
				break;
			case 3:
				ParkingRate vr = new VisitorRate();
				prd = new EarlyBirdDecorator(vr);
				cost = prd.calculateRate(1);
				rateTextField.setText("$" + cost);
				break;
			}
			int hours = Integer.parseInt(hoursTextField.getText());
			int deposit = hours * cost;
			if (hours > 1) {
				depositTextField.setText("$" + deposit);
			} else {
				depositTextField.setText("$" + cost + " (Non-refundable after 1 hour of booking period)");
			}
		}

		/*
			Req9: Clients can extend a parking time before the expiration.
        	Req10: Clients can pay their parking fee via different payment options, i.e., debit cards, credit cards, mobile
			payments, etc.
		 * **/
		if(payment.equals("Debit Card")) {


			// stategy pattern with strategypaymnet interface and debitcard, credicard  mobile java class with GUI respectively poping from collect payment

			paymentstrategy ps=new debitcard();
			ps.collectpaymentdetails();

		}
		else if (payment.equals("Credit Card")) {

			paymentstrategy ps=new creditcard();
			ps.collectpaymentdetails();

		}
		else if (payment.equals("Mobile Payment")) {

			paymentstrategy ps=new mobilepayment();
			ps.collectpaymentdetails();

		}

		//JOptionPane.showMessageDialog(ParkingBooking.this, "Booking Successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
		//dispose();
	}


}

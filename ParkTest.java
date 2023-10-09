package park;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionEvent;

import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.*;


import org.junit.Test;

public class ParkTest {

	@Test
	public void testUser1() {
		User tom = new User("Tom", 45678, "tom@gmail.com", "password1233", 12);
		tom.setLiscensePlate("ABCD123");
		tom.setDeposit(100);
		tom.setValidationStatus(true);

		assertEquals("Tom", tom.getName());
		assertEquals("tom@gmail.com", tom.getEmail());
		assertEquals("password1233", tom.getPassword());
		assertEquals("ABCD123", tom.getLiscensePlate());
		assertEquals(true, tom.getValidationStatus());
		assertEquals(45678, tom.getId());
		assertEquals(false, tom.isNoShow(2));
		//assertEquals(100.0, tom.getDeposit());
		
		tom.setName("Tom S");
		assertEquals("Tom S", tom.getName());
		
		tom.setEmail("tom@hotmail.com");
		assertEquals("tom@hotmail.com", tom.getEmail());
		
		tom.setId(12345);
		assertEquals(12345, tom.getId());
		
		tom.setPassword("afaer23123");
		assertEquals("afaer23123", tom.getPassword());
	}
	
	

	@Test
	public void testUser2(){
		//Cover MaintainUser.java

		String type = "Student";
		String email = "kulp00@gmail.com";
		String password = "Ms@123456";

		String type2 = "Student";
		String email2 = "sha11@gmail.com";
		String password2 = "Jk@125456";

		String type3 = "Visitor";
		String email3 = "DANIEL911a@gmail.com";
		String password3 = "Lol@42069";

		MaintainUser.add(type, email, password);
		MaintainUser.add(type2, email2, password2);
		MaintainUser.add(type3, email3, password3);


		assertFalse(MaintainUser.check(type, email, password));
		assertFalse(MaintainUser.check(type2, email2, password2));
		assertFalse(MaintainUser.check(type3, email3, password3));
	}

	@Test
	public void testUser3(){
		//Cover UserFactory.java

		String type = "Student";
		String email = "kulp00@gmail.com";
		String password = "Ms@123456";

		String type2 = "Faculty Member";
		String email2 = "sha11@gmail.com";
		String password2 = "Jk@125456";

		String type3 = "Non-Faculty Staff";
		String email3 = "yoloUWU911@gmail.com";
		String password3 = "Lol@42069rr";

		String type4 = "Visitor";
		String email4 = "DANIEL911a@gmail.com";
		String password4 = "Lol@42069";

		client temp = UserFactory.getInstance(type, email, password);
		client temp2 = UserFactory.getInstance(type2, email2, password2);
		client temp3 = UserFactory.getInstance(type3, email3, password3);
		client temp4 = UserFactory.getInstance(type4, email4, password4);

		assertEquals(type, temp.type());
		assertEquals(type2, temp2.type());
		assertEquals(type3, temp3.type());
		assertEquals(type4, temp4.type());

		assertEquals(email, temp.getemail());
		assertEquals(email2, temp2.getemail());
		assertEquals(email3, temp3.getemail());
		assertEquals(email4, temp4.getemail());

		assertEquals(password, temp.getPassword());
		assertEquals(password2, temp2.getPassword());
		assertEquals(password3, temp3.getPassword());
		assertEquals(password4, temp4.getPassword());
	}


	@Test
	public void testManager1() {
		Manager manager = new Manager();
		assertNotNull(manager);
		assertNotNull(manager.getName());
		assertNotNull(manager.getPassword());

		User tom = new User("Tom", 45678, "tom@gmail.com", "password1233", 12);
		manager.addUser(tom);
		assertTrue(tom.validationStatus);


		manager.removeUser(tom);
		assertFalse(tom.validationStatus);
	}


	@Test
	public void testManager2() {
		Manager manager = new Manager();
		User tom = new User("Tom", 45678, "tom@gmail.com", "password1233", 12);
		manager.addUser(tom);

		String password = manager.generatePassword();
		assertNotNull(password);
		assertTrue(password.length() >= 12);
		// assertTrue(password.matches("(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()\\-_+=<>,./?;:\"\\[\\]{}\\\\|]).*"));


		assertTrue(manager.validateUser("john.doe@yorku.ca"));
		assertTrue(manager.validateUser("john.doe@my.yorku.ca"));
		assertFalse(manager.validateUser("john.doe@example.com"));
	}

	@Test
	public void testAddParking() {

		ManagementTeam managementTeam = new ManagementTeam(SuperManager.getInstance());
		managementTeam.addParking();
		assertEquals(1, managementTeam.parkingSystem.parkingSpaces.size());
		assertNotNull(managementTeam.parkingSystem.parkingSpaces.get(0));
	}

	@Test
	public void testAddParkingWhenLimitReached() {
		ManagementTeam managementTeam = new ManagementTeam(SuperManager.getInstance());
		for (int i = 0; i < 100; i++) {
			managementTeam.addParking();
		}
		managementTeam.addParking();
		assertEquals(100, managementTeam.parkingSystem.parkingSpaces.size());
	}

	@Test
	public void testRemoveParking() {
		ManagementTeam managementTeam = new ManagementTeam(SuperManager.getInstance());
		managementTeam.addParking();
		ParkingSpace parkingSpace = managementTeam.parkingSystem.parkingSpaces.get(0);
		managementTeam.removeParking(parkingSpace);
		assertEquals(0, managementTeam.parkingSystem.parkingSpaces.size());
	}

	@Test
	public void testRemoveParkingWhenEmpty() {
		ManagementTeam managementTeam = new ManagementTeam(SuperManager.getInstance());
		ParkingSpace parkingSpace = new ParkingSpace(1);
		managementTeam.removeParking(parkingSpace);
		assertEquals(0, managementTeam.parkingSystem.parkingSpaces.size());
	}

	@Test
	public void testAutoAccountGeneration() {
		SuperManager superManager = SuperManager.getInstance();
		ManagementTeam managementTeam = new ManagementTeam(superManager);
		managementTeam.AutoAccountGeneration();
		assertEquals(1, managementTeam.managers.size());
		Manager manager = managementTeam.managers.get(0);
		assertNotNull(manager.username);
		assertNotNull(manager.password);
	}

	@Test
	public void testCollectPaymentDetailsCC() {
		creditcard creditCard = new creditcard();
		creditCard.collectpaymentdetails();

	}

	@Test
	public void testValidateCreditCard() {
		creditcard creditCard = new creditcard();
		boolean result = creditCard.validate();
		assertFalse(result); // TODO: Replace with appropriate assertions
	}

	@Test
	public void testValidateDebitCard() {
		debitcard debitCard = new debitcard();
		boolean result = debitCard.validate();
		assertFalse(result); // TODO: Replace with appropriate assertions
	}

	@Test
	public void testCollectPaymentDetailsDC() {
		debitcard debitCard = new debitcard();
		debitCard.collectpaymentdetails();

	}

	@Test
	public void testValidateMobilePay() {
		mobilepayment mobilePayment = new mobilepayment();
		boolean result = mobilePayment .validate();
		assertFalse(result); // TODO: Replace with appropriate assertions
	}

	@Test
	public void testCollectPaymentDetailsMP() {
		mobilepayment mobilePayment  = new mobilepayment();
		mobilePayment.collectpaymentdetails();

	}

	public class creditGUITest {
		private creditGUI gui;

		@Before
		public void setUp() throws Exception {
			gui = new creditGUI();
		}

		@After
		public void tearDown() throws Exception {
			gui.dispose();
		}

		@Test
		public void testGUIComponents() {
			// Check that all the GUI components were created
			Component[] components = gui.getContentPane().getComponents();
			assertEquals(7, components.length);
			assertTrue(components[0] instanceof JLabel);
			assertTrue(components[1] instanceof JTextField);
			assertTrue(components[2] instanceof JLabel);
			assertTrue(components[3] instanceof JTextField);
			assertTrue(components[4] instanceof JLabel);
			assertTrue(components[5] instanceof JTextField);
			assertTrue(components[6] instanceof JPanel);

			// Check that the pay button was created with the correct text
			JPanel panel = (JPanel) components[6];
			Component[] panelComponents = panel.getComponents();
			assertEquals(2, panelComponents.length);
			assertTrue(panelComponents[1] instanceof JButton);
			JButton payButton = (JButton) panelComponents[1];
			assertEquals("Pay", payButton.getText());
		}

	}


	@Test
	public void testStudentRate() {
		ParkingRate rate = new StudentRate();
		assertEquals(20, rate.calculateRate(4));
	}

	@Test
	public void testFacultyRate() {
		ParkingRate rate = new FacultyRate();
		assertEquals(16, rate.calculateRate(2));
	}

	@Test
	public void testStaffRate() {
		ParkingRate rate = new StaffRate();
		assertEquals(50, rate.calculateRate(5));
	}

	@Test
	public void testVisitorRate() {
		ParkingRate rate = new VisitorRate();
		assertEquals(75, rate.calculateRate(5));
	}

	@Test
	public void testEarlyBirdDecorator() {
		ParkingRate rate = new VisitorRate();
		ParkingRateDecorator decorator = new EarlyBirdDecorator(rate);
		assertEquals(75, decorator.calculateRate(5)); // rate should be unchanged
	}

	@Test
	public void testConstructor() {
		Parkingcharges parking = new Parkingcharges();
		assertNotNull(parking);
	}

	@Test
	public void testPaymentSelection() {
		Parkingcharges parking = new Parkingcharges();
		parking.paymentComboBox.setSelectedIndex(0);
		assertEquals(parking.paymentComboBox.getSelectedItem(), "Debit Card");
		parking.paymentComboBox.setSelectedIndex(1);
		assertEquals(parking.paymentComboBox.getSelectedItem(), "Credit Card");
		parking.paymentComboBox.setSelectedIndex(2);
		assertEquals(parking.paymentComboBox.getSelectedItem(), "Mobile Payment");
	}

	@Test
	public void testEarlyBirdStudentRate() {
		Parkingcharges parking = new Parkingcharges();
		parking.typeComboBox.setSelectedIndex(0); // Student
		parking.hoursTextField.setText("2");
		parking.bookButton.doClick();
		assertEquals(parking.rateTextField.getText(), "$5");
		assertEquals(parking.depositTextField.getText(), "$10");
	}

	@Test
	public void testEarlyBirdFacultyRate() {
		Parkingcharges parking = new Parkingcharges();
		parking.typeComboBox.setSelectedIndex(1); // Faculty Member
		parking.hoursTextField.setText("3");
		parking.bookButton.doClick();
		assertEquals(parking.rateTextField.getText(), "$8");
		assertEquals(parking.depositTextField.getText(), "$24");
	}

	@Test
	public void testEarlyBirdStaffRate() {
		Parkingcharges parking = new Parkingcharges();
		parking.typeComboBox.setSelectedIndex(2); // Non-faculty Staff
		parking.hoursTextField.setText("1");
		parking.bookButton.doClick();
		assertEquals(parking.rateTextField.getText(), "$10");
		assertEquals(parking.depositTextField.getText(), "$10 (Non-refundable after 1 hour of booking period)");
	}

	@Test
	public void testEarlyBirdVisitorRate() {
		Parkingcharges parking = new Parkingcharges();
		parking.typeComboBox.setSelectedIndex(3); // Visitor
		parking.hoursTextField.setText("4");
		parking.bookButton.doClick();
		assertEquals(parking.rateTextField.getText(), "$15");
		assertEquals(parking.depositTextField.getText(), "$60");
	}

	@Test
	public void testPaymentDebitCard() {
		Parkingcharges parking = new Parkingcharges();
		parking.paymentComboBox.setSelectedIndex(0); // Debit Card
		// parking.actionPerformed(new ActionEvent(parking.bookButton, 0, ""));
	}

	@Test
	public void testPaymentCreditCard() {
		Parkingcharges parking = new Parkingcharges();
		parking.paymentComboBox.setSelectedIndex(1); // Credit Card
		//  parking.actionPerformed(new ActionEvent(parking.bookButton, 0, ""));
	}

	@Test
	public void testPaymentMobilePayment() {
		Parkingcharges parking = new Parkingcharges();
		parking.paymentComboBox.setSelectedIndex(2); // Mobile Payment
		// parking.actionPerformed(new ActionEvent(parking.bookButton, 0, ""));
	}

	// Test the checkAvailability() method when the parking spot is available
	@Test
	public void testCheckAvailabilityAvailable() {
		ParkingGUI gui = new ParkingGUI();
		boolean available = gui.checkAvailability("ABC123", 1);
		assertTrue(available);
	}

	// Test the actionPerformed() method when the parking spot is available
	@Test
	public void testActionPerformedAvailable() {
		ParkingGUI gui = new ParkingGUI();
		gui.fieldPlate.setText("ABC123");
		gui.comboLot.setSelectedIndex(0);
		gui.buttonSubmit.doClick();
		assertEquals("Parking spot available.", gui.labelAvailable.getText());
		assertNull(gui.getParent());
	}


	@Test
	public void testEmailValidation() {
		UserRegistration ur = new UserRegistration();
		assertEquals(true, ur.isValidEmail("test@gmail.com"));
		assertEquals(true, ur.isValidEmail("test@example"));
		assertEquals(false, ur.isValidEmail("testexample.com"));
	}

	@Test
	public void testPasswordValidation() {
		UserRegistration ur = new UserRegistration();
		assertEquals(true, ur.isValidPassword("P@ssword123"));
		assertEquals(false, ur.isValidPassword("password123"));
		assertEquals(false, ur.isValidPassword("PASSWORD123"));
		assertEquals(false, ur.isValidPassword("p@ssword"));
		assertEquals(false, ur.isValidPassword("P@ssword"));
		assertEquals(true, ur.isValidPassword("P@ssword123!"));
	}

	@Test
	public void testComponents() {
		UserRegistration ur = new UserRegistration();
		JPanel panel = (JPanel) ur.getContentPane().getComponent(0);
		assertEquals(10, panel.getComponentCount());

		assertTrue(panel.getComponent(0) instanceof JLabel);
		assertEquals(((JLabel) panel.getComponent(0)).getText(), "Name");

		assertTrue(panel.getComponent(1) instanceof JTextField);

		assertTrue(panel.getComponent(2) instanceof JLabel);
		assertEquals(((JLabel) panel.getComponent(2)).getText(), "Email:");

		assertTrue(panel.getComponent(3) instanceof JTextField);

		assertTrue(panel.getComponent(4) instanceof JLabel);
		assertEquals(((JLabel) panel.getComponent(4)).getText(), "Password:");

		assertTrue(panel.getComponent(5) instanceof JPasswordField);

		assertTrue(panel.getComponent(6) instanceof JLabel);
		assertEquals(((JLabel) panel.getComponent(6)).getText(), "User Type:");

		assertTrue(panel.getComponent(7) instanceof JComboBox);
		assertEquals(((JComboBox) panel.getComponent(7)).getItemCount(), 4);

		assertTrue(panel.getComponent(8) instanceof JButton);
		assertEquals(((JButton) panel.getComponent(8)).getText(), "Register");

		assertTrue(panel.getComponent(9) instanceof JButton);
		assertEquals(((JButton) panel.getComponent(9)).getText(), "Login");
	}

}



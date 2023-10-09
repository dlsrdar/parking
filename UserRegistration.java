package park;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.Pattern;

public class UserRegistration extends JFrame {
	private JLabel nameLabel,emailLabel, passwordLabel, userTypeLabel;
	private JTextField nameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private JComboBox<String> userTypeComboBox;
	private JButton registerButton, loginButton;

	public UserRegistration() {


		// Singleton
		register.getInstance();

		setTitle("Parking services");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Creating Components
		nameLabel =  new JLabel("Name");
		emailLabel = new JLabel("Email:");
		passwordLabel = new JLabel("Password:");
		userTypeLabel = new JLabel("User Type:");
		nameTextField = new JTextField();
		emailTextField = new JTextField();
		passwordField = new JPasswordField();
		userTypeComboBox = new JComboBox<>(new String[] {"Student", "Faculty Member", "Non-Faculty Staff", "Visitor"});
		registerButton = new JButton("Register");
		loginButton = new JButton("Login");

		// Adding Components
		JPanel panel = new JPanel(new GridLayout(5, 2));
		panel.add(nameLabel);
		panel.add(nameTextField);
		panel.add(emailTextField);
		panel.add(emailLabel);
		panel.add(emailTextField);
		panel.add(passwordLabel);
		panel.add(passwordField);
		panel.add(userTypeLabel);
		panel.add(userTypeComboBox);
		panel.add(registerButton);
		panel.add(loginButton);
		add(panel, BorderLayout.CENTER);

		// Register Button Action Listener

		/*
		 * Req1: Any client should be able to register as a user of the system with a unique/valid email and strong
		 * password (i.e., a combination of uppercase letters, lowercase letters, numbers, and symbols). The system
		 * currently allows four types of clients to be registered, i.e., students, faculty members, non-faculty staffs, and
		 * visitors, while it�s open for new types in the future. If a client registers as a student, a faculty member or a
		 * non-faculty staff, her/his registration requires a further validation from the management teams.
		 **/
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String email = emailTextField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String userType = (String) userTypeComboBox.getSelectedItem();

				// Validating Email and Password
				if (!isValidEmail(email)) {

					JOptionPane.showMessageDialog(UserRegistration.this, "Invalid Email Address.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!isValidPassword(password)) {
					JOptionPane.showMessageDialog(UserRegistration.this, "Invalid Password.\nPassword should contain at least 8 characters including uppercase letters, lowercase letters, numbers, and symbols.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}

				// Validating User Type for Student, Faculty Member, Non-Faculty Staff
				if (userType.equals("Student") || userType.equals("Faculty Member") || userType.equals("Non-Faculty Staff")) {
					// factory pattern
					UserFactory.getInstance(userType,email,password);
					Manager managementTeam = new Manager();
					boolean validationStatus = managementTeam.validateUser(email);

					if(validationStatus == true) {
						// Registration Successful
						MaintainUser.add(userType,email,password);
						JOptionPane.showMessageDialog(UserRegistration.this, "Registration Successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(UserRegistration.this, "Registration invalid. Please register with your valid university-issued email ID.", "Error", JOptionPane.ERROR_MESSAGE);
					}

				}


			}
		});

		// Cancel Button Action Listener
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String email = emailTextField.getText();
				String password = String.valueOf(passwordField.getPassword());
				String userType = (String) userTypeComboBox.getSelectedItem();
				System.out.println("hello");


				if (MaintainUser.check(userType,email,password) == true) {
					System.out.println("hello");


					ParkingGUI pk = new ParkingGUI();
					pk.setVisible(true);
					dispose();

				}

			}
		});

		setVisible(true);
	}

	// Email Validation
	protected boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

	// Password Validation
	protected boolean isValidPassword(String password) {
		String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(password).matches();
	}

	public static void main(String[] args) {

		new UserRegistration();


	}
}
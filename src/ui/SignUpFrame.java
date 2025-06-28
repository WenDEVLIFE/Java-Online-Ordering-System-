package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import database.AccountService;

public class SignUpFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField fullNameField;
	private JTextField emailField;
	private JTextField contactNumberField;
	private JTextField DeliveryAddressField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( new com.formdev.flatlaf.FlatDarculaLaf());
					SignUpFrame frame = new SignUpFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignUpFrame() {
		setTitle("Online Ordering System Registration");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 674);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 128, 64));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegister = new JLabel("Register");
		lblRegister.setFont(new Font("SansSerif", Font.BOLD, 30));
		lblRegister.setBounds(268, 11, 156, 43);
		contentPane.add(lblRegister);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblUsername.setBounds(83, 315, 103, 43);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPassword.setBounds(83, 373, 103, 43);
		contentPane.add(lblPassword);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("SansSerif", Font.BOLD, 15));
		usernameField.setColumns(10);
		usernameField.setBounds(196, 315, 389, 43);
		contentPane.add(usernameField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.BOLD, 15));
		passwordField.setBounds(196, 377, 389, 43);
		contentPane.add(passwordField);
		
		JButton btnSignUp_1 = new JButton("Sign Up");
		btnSignUp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String username = usernameField.getText();
				String password = new String(passwordField.getPassword());
				String confirmPassword = new String(confirmPasswordField.getPassword());
				String fullName = fullNameField.getText();
				String email = emailField.getText();
				String contactNumber = contactNumberField.getText();
				String deliveryAddress = DeliveryAddressField.getText();
				
				
				if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullName.isEmpty() || email.isEmpty() || contactNumber.isEmpty() || deliveryAddress.isEmpty()) {
					// Show error message for empty fields
					System.out.println("Please fill in all fields.");
					JOptionPane.showMessageDialog(contentPane, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!password.equals(confirmPassword)) {
					// Show error message for password mismatch
					System.out.println("Passwords do not match.");
					JOptionPane.showMessageDialog(contentPane, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else if (!email.contains("@") || !email.contains(".")) {
					// Show error message for invalid email format
					System.out.println("Invalid email format.");
					JOptionPane.showMessageDialog(contentPane, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
				} 
				else if(password.length() < 8) {
					// Show error message for password length
					System.out.println("Password must be at least 8 characters long.");
					JOptionPane.showMessageDialog(contentPane, "Password must be at least 8 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!username.matches("[a-zA-Z0-9_]+")) {
					// Show error message for invalid username format
					System.out.println("Username can only contain letters, numbers, and underscores.");
					JOptionPane.showMessageDialog(contentPane, "Username can only contain letters, numbers, and underscores.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
				    System.out.println("Password must contain at least one special character.");
				    JOptionPane.showMessageDialog(contentPane, "Password must contain at least one special character.", "Error", JOptionPane.ERROR_MESSAGE);
				} else if (!password.matches(".*[A-Z].*")) {
				    System.out.println("Password must contain at least one uppercase letter.");
				    JOptionPane.showMessageDialog(contentPane, "Password must contain at least one uppercase letter.", "Error", JOptionPane.ERROR_MESSAGE);
				}

				else if (contactNumber.length() != 11 || !contactNumber.matches("\\d+")) {
					// Show error message for invalid contact number
					System.out.println("Invalid contact number. It should be 11 digits long.");
					JOptionPane.showMessageDialog(contentPane, "Invalid contact number. It should be 11 digits long.", "Error", JOptionPane.ERROR_MESSAGE);
					
					Map <String, String> userDetails = new HashMap<>();
					userDetails.put("username", username);
					userDetails.put("password", password);
					userDetails.put("fullName", fullName);
					userDetails.put("email", email);
					userDetails.put("contactNumber", contactNumber);
					userDetails.put("deliveryAddress", deliveryAddress);
					
					boolean register = AccountService.getInstance().registerUser(userDetails);
					
					
					if (register) {
						// Show success message
						System.out.println("Registration successful.");
						JOptionPane.showMessageDialog(contentPane, "Registration successful.", "Success", JOptionPane.INFORMATION_MESSAGE);
						
						LoginFrame loginFrame = new LoginFrame();
						loginFrame.setVisible(true);
						dispose(); // Close the SignUpFrame
					} else {
						// Show error message for registration failure
						System.out.println("Registration failed. Please try again.");
						JOptionPane.showMessageDialog(contentPane, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
				}
			}
		});
		btnSignUp_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSignUp_1.setBounds(214, 515, 277, 49);
		contentPane.add(btnSignUp_1);
		
		JButton btnBackToLogin = new JButton("Back to login");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LoginFrame loginFrame = new LoginFrame();
				loginFrame.setVisible(true);
				dispose(); // Close the SignUpFrame
			}
		});
		btnBackToLogin.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnBackToLogin.setBounds(214, 575, 277, 49);
		contentPane.add(btnBackToLogin);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblConfirmPassword.setBounds(10, 442, 176, 43);
		contentPane.add(lblConfirmPassword);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("SansSerif", Font.BOLD, 15));
		confirmPasswordField.setBounds(196, 446, 389, 43);
		contentPane.add(confirmPasswordField);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblFullName.setBounds(83, 77, 103, 43);
		contentPane.add(lblFullName);
		
		fullNameField = new JTextField();
		fullNameField.setFont(new Font("SansSerif", Font.BOLD, 15));
		fullNameField.setColumns(10);
		fullNameField.setBounds(196, 77, 389, 43);
		contentPane.add(fullNameField);
		
		emailField = new JTextField();
		emailField.setFont(new Font("SansSerif", Font.BOLD, 15));
		emailField.setColumns(10);
		emailField.setBounds(196, 138, 389, 43);
		contentPane.add(emailField);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmail.setBounds(121, 131, 56, 43);
		contentPane.add(lblEmail);
		
		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblContactNumber.setBounds(30, 195, 156, 43);
		contentPane.add(lblContactNumber);
		
		contactNumberField = new JTextField();
		contactNumberField.setFont(new Font("SansSerif", Font.BOLD, 15));
		contactNumberField.setColumns(10);
		contactNumberField.setBounds(198, 199, 389, 43);
		contentPane.add(contactNumberField);
		
		JLabel lblDeliveryAddress = new JLabel("Delivery Address");
		lblDeliveryAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDeliveryAddress.setBounds(20, 261, 166, 43);
		contentPane.add(lblDeliveryAddress);
		
		DeliveryAddressField = new JTextField();
		DeliveryAddressField.setFont(new Font("SansSerif", Font.BOLD, 15));
		DeliveryAddressField.setColumns(10);
		DeliveryAddressField.setBounds(196, 261, 389, 43);
		contentPane.add(DeliveryAddressField);

	}
}

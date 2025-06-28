package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

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
		
		textField = new JTextField();
		textField.setFont(new Font("SansSerif", Font.BOLD, 15));
		textField.setColumns(10);
		textField.setBounds(196, 315, 389, 43);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("SansSerif", Font.BOLD, 15));
		passwordField.setBounds(196, 377, 389, 43);
		contentPane.add(passwordField);
		
		JButton btnSignUp_1 = new JButton("Sign Up");
		btnSignUp_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		passwordField_1.setBounds(196, 446, 389, 43);
		contentPane.add(passwordField_1);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblFullName.setBounds(83, 77, 103, 43);
		contentPane.add(lblFullName);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("SansSerif", Font.BOLD, 15));
		textField_1.setColumns(10);
		textField_1.setBounds(196, 77, 389, 43);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		textField_2.setColumns(10);
		textField_2.setBounds(196, 138, 389, 43);
		contentPane.add(textField_2);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmail.setBounds(121, 131, 56, 43);
		contentPane.add(lblEmail);
		
		JLabel lblContactNumber = new JLabel("Contact Number");
		lblContactNumber.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblContactNumber.setBounds(30, 195, 156, 43);
		contentPane.add(lblContactNumber);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("SansSerif", Font.BOLD, 15));
		textField_3.setColumns(10);
		textField_3.setBounds(198, 199, 389, 43);
		contentPane.add(textField_3);
		
		JLabel lblDeliveryAddress = new JLabel("Delivery Address");
		lblDeliveryAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDeliveryAddress.setBounds(20, 261, 166, 43);
		contentPane.add(lblDeliveryAddress);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("SansSerif", Font.BOLD, 15));
		textField_4.setColumns(10);
		textField_4.setBounds(196, 261, 389, 43);
		contentPane.add(textField_4);

	}
}

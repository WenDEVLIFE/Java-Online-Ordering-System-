package dialog;

import javax.swing.*;

import database.MyProfileService;

import java.awt.*;
import java.util.Map;

public class MyProfileDialog extends JDialog {
    private JTextField fullNameField;
    private JTextField emailField;
    private JTextField contactField;
    private JTextArea addressArea;
    private boolean isSaved = false;

    public MyProfileDialog(JFrame parent, Map<String, String> userProfile) {
        super(parent, "My Profile", true);
        setLayout(null);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        
        String fullName = userProfile.getOrDefault("fullName", "");
        String email = userProfile.getOrDefault("email", "");
        String contact = userProfile.getOrDefault("contact", "");
        String address = userProfile.getOrDefault("address", "");
        String userId = userProfile.getOrDefault("userId", "");

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        add(nameLabel);

        fullNameField = new JTextField();
        fullNameField.setText(fullName);
        fullNameField.setBounds(130, 20, 220, 25);
        add(fullNameField);

        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setBounds(20, 60, 100, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setText(email);
        emailField.setBounds(130, 60, 220, 25);
        add(emailField);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(20, 100, 120, 25);
        add(contactLabel);

        contactField = new JTextField();
        contactField.setText(contact);
        contactField.setBounds(130, 100, 220, 25);
        add(contactField);

        JLabel addressLabel = new JLabel("Delivery Address:");
        addressLabel.setBounds(20, 140, 120, 25);
        add(addressLabel);

        addressArea = new JTextArea();
        addressArea.setText(address);
        JScrollPane scrollPane = new JScrollPane(addressArea);
        scrollPane.setBounds(130, 140, 220, 60);
        add(scrollPane);

        JButton okButton = new JButton("OK");
        okButton.setBounds(80, 230, 90, 30);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 230, 90, 30);
        add(cancelButton);

        okButton.addActionListener(e -> {
        	String fullName1 = fullNameField.getText().trim();
			String email1 = emailField.getText().trim();
			String contact1 = contactField.getText().trim();
			String address1 = addressArea.getText().trim();

			if (fullName1.isEmpty() || email1.isEmpty() || contact1.isEmpty() || address1.isEmpty()) {
				JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (!email1.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				 JOptionPane.showMessageDialog(this, "Invalid email format.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!contact1.matches("^\\d{11}$")) {
				JOptionPane.showMessageDialog(this, "Contact number must be 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			boolean isUpdated = MyProfileService.updateProfile(userId, fullName1, email1, contact1, address1);
			if (isUpdated) {
				JOptionPane.showMessageDialog(this, "Profile updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				isSaved = true;
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Failed to update profile. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				isSaved = false;
			}

	
           
        });

        cancelButton.addActionListener(e -> {
            isSaved = false;
            dispose();
        });
    }

    public boolean isSaved() {
        return isSaved;
    }

   
}

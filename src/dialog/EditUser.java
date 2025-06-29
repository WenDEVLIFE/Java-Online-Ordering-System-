
package dialog;

import model.UserModel;
import ui.AdminFrame;

import javax.swing.*;

import database.AccountService;

public class EditUser extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton saveButton, cancelButton;
    
    public boolean isUserEdited = false;

    public EditUser(AdminFrame adminFrame, UserModel selectedUser) {
        super(adminFrame, "Edit User", true);
        setLayout(null);
        setSize(350, 250);
        setLocationRelativeTo(adminFrame);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 30, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField(selectedUser.getUserName());
        usernameField.setBounds(120, 30, 180, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField(selectedUser.getPassword());
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(30, 110, 80, 25);
        add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"Admin", "User", "Manager"});
        roleComboBox.setBounds(120, 110, 180, 25);
        roleComboBox.setSelectedItem(selectedUser.getRole());
        add(roleComboBox);

        saveButton = new JButton("Save");
        saveButton.setBounds(60, 160, 90, 30);
        add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 160, 90, 30);
        add(cancelButton);

        cancelButton.addActionListener(e -> dispose());
        saveButton.addActionListener(e -> {
        
            String username = usernameField.getText().trim();
			String password = new String(passwordField.getPassword()).trim();
			String role = (String) roleComboBox.getSelectedItem();

			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username and Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

		         boolean isUpdated = AccountService.getInstance().updateUser(selectedUser.getUserId(), username, password, role); 
		         
		         if (isUpdated) {
		             JOptionPane.showMessageDialog(this, "User updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
		             adminFrame.refreshAccountTable(); // Refresh the account table in AdminFrame
		             dispose();
		             isUserEdited = true; // Set the flag to true
		         } else {
		             JOptionPane.showMessageDialog(this, "Failed to update user", "Error", JOptionPane.ERROR_MESSAGE);
		             isUserEdited = false; // Set the flag to false
		         }
        });
    }
    
    public boolean isUserEdited() {
		return isUserEdited;
    }
}

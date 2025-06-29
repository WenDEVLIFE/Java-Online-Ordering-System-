package dialog;

import javax.swing.*;

import database.AccountService;

import java.awt.event.*;

public class AddUser extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    private JButton addButton, cancelButton;

    public boolean isUserAdded = false;
    public AddUser(JFrame parent) {
        super(parent, "Add User", true);
        setLayout(null);
        setSize(350, 250);
        setLocationRelativeTo(parent);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 30, 80, 25);
        add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 180, 25);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(30, 70, 80, 25);
        add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 180, 25);
        add(passwordField);

        JLabel roleLabel = new JLabel("Role:");
        roleLabel.setBounds(30, 110, 80, 25);
        add(roleLabel);

        roleComboBox = new JComboBox<>(new String[]{"Admin", "Restaurant-Admin"});
        roleComboBox.setBounds(120, 110, 180, 25);
        add(roleComboBox);

        addButton = new JButton("Add");
        addButton.setBounds(60, 160, 90, 30);
        add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 160, 90, 30);
        add(cancelButton);

        cancelButton.addActionListener(e -> dispose());
        addButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
			String password = new String(passwordField.getPassword()).trim();
			String role = (String) roleComboBox.getSelectedItem();

			if (username.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Username and Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			} else {
				
				AccountService.getInstance().InsertAccount(username, password, role);
				System.out.println("User Added: " + username + ", Role: " + role);
				isUserAdded = true;
				dispose();
			}
        });
    }
    
    public boolean isUserAdded() {
		return isUserAdded;
	}
}

package dialog;

import javax.swing.*;

import database.AccountService;
import ui.AdminFrame;

public class ChangePasswordDialog extends JDialog {
    private JPasswordField oldPasswordField;
    private JPasswordField newPasswordField;
    private boolean isChanged = false;

    public ChangePasswordDialog(JFrame parent, int userId) {
        super(parent, "Change Password", true);
        setLayout(null);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        JLabel oldLabel = new JLabel("Old Password:");
        oldLabel.setBounds(20, 30, 100, 25);
        add(oldLabel);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(130, 30, 180, 25);
        add(oldPasswordField);

        JLabel newLabel = new JLabel("New Password:");
        newLabel.setBounds(20, 70, 100, 25);
        add(newLabel);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(130, 70, 180, 25);
        add(newPasswordField);

        JButton okButton = new JButton("OK");
        okButton.setBounds(60, 120, 90, 30);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 120, 90, 30);
        add(cancelButton);

        okButton.addActionListener(e -> {
        	String oldPassword = new String(oldPasswordField.getPassword()).trim();
			String newPassword = new String(newPasswordField.getPassword()).trim();
			
			if (oldPassword.isEmpty() || newPassword.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Old Password and New Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			AccountService accountService = AccountService.getInstance();
			boolean isPasswordChanged = accountService.updatePassword(userId, oldPassword, newPassword);
			
			if (isPasswordChanged) {
				JOptionPane.showMessageDialog(this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
				isChanged = true;
				oldPasswordField.setText("");
				newPasswordField.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Failed to update password. Please check your old password.", "Error", JOptionPane.ERROR_MESSAGE);
				isChanged = false;
			}
        });

        cancelButton.addActionListener(e -> {
            isChanged = false;
            dispose();
        });
    }

    public boolean isChanged() {
        return isChanged;
    }

    public String getOldPassword() {
        return new String(oldPasswordField.getPassword());
    }

    public String getNewPassword() {
        return new String(newPasswordField.getPassword());
    }
}

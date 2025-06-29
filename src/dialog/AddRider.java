package dialog;

import javax.swing.*;

public class AddRider extends JDialog {
    private JTextField riderField;
    private JTextField phoneField;
    private String riderName;
    private String phoneNumber;
    
    private static boolean isRiderAdded = false;

    public AddRider(JFrame parent) {
        super(parent, "Add Rider", true);
        setLayout(null);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        JLabel nameLabel = new JLabel("Rider Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        add(nameLabel);

        riderField = new JTextField();
        riderField.setBounds(130, 20, 220, 25);
        add(riderField);

        JLabel phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(20, 60, 100, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(130, 60, 220, 25);
        add(phoneField);

        JButton okButton = new JButton("OK");
        okButton.setBounds(80, 110, 90, 30);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 110, 90, 30);
        add(cancelButton);

        okButton.addActionListener(e -> {
            riderName = riderField.getText().trim();
            phoneNumber = phoneField.getText().trim();
            
            if (riderName.isEmpty() || phoneNumber.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
			} 
            if (!phoneNumber.matches("\\d{11}")) {
            	 				JOptionPane.showMessageDialog(this, "Phone number must be 11 digits.", "Error", JOptionPane.ERROR_MESSAGE);
            	 				return;
            }
            else {
				
				boolean isAdded = database.RiderService.getInstance().AddRider(riderName, phoneNumber);
				boolean riderExists = database.RiderService.getInstance().RiderExists(riderName);
				 if(riderExists) {
						JOptionPane.showMessageDialog(this, "Rider already exists.", "Error", JOptionPane.ERROR_MESSAGE);
						
						return;
				 } 
				 
				 else {
					 if (isAdded) {
							isRiderAdded = true;
							JOptionPane.showMessageDialog(this, "Rider added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} else {
							JOptionPane.showMessageDialog(this, "Failed to add rider. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
							isRiderAdded = false;
						}
				 }
				 
				
				 
				 
           
           
			}
        });

        cancelButton.addActionListener(e -> {
            riderName = null;
            phoneNumber = null;
            dispose();
        });
    }
    
    public static boolean isRiderAdded() {
				return isRiderAdded;
	}
	

   
}

package dialog;

import javax.swing.*;

import database.OrderService;
import model.MenuItemModel;

public class SelectItemDialog extends JDialog {
    private JTextField quantityField;
    private int quantity = 0;
    private boolean isSelected = false;

    public SelectItemDialog(JFrame parent, MenuItemModel menuItem, int userId) {
        super(parent, "Select Quantity", true);
        setLayout(null);
        setSize(300, 180);
        setLocationRelativeTo(parent);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setBounds(30, 30, 80, 25);
        add(quantityLabel);

        quantityField = new JTextField();
        quantityField.setBounds(120, 30, 120, 25);
        add(quantityField);

        JButton okButton = new JButton("OK");
        okButton.setBounds(40, 90, 90, 30);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(150, 90, 90, 30);
        add(cancelButton);

        okButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(quantityField.getText().trim());
                if (value <= 0) throw new NumberFormatException();
                
                quantity = value;
                int totalPrice = value * Integer.parseInt(menuItem.getMenuprice());
                String message = String.format("You have selected %d of %s. Total Price: %d", value, menuItem.getMenuItemName(), totalPrice);
                
                boolean isOrderAdded = OrderService.getInstance().addOrder(userId, menuItem.getMenuItemId(), value, totalPrice);
                
                if (isOrderAdded) {
					isSelected = true;
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Failed to place order. Please try again.", "Order Error", JOptionPane.ERROR_MESSAGE);
				}
             
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid positive integer.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            isSelected = false;
            dispose();
        });
    }

    public boolean isSelected() {
        return isSelected;
    }

    public int getQuantity() {
        return quantity;
    }
}

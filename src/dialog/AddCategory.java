package dialog;

import javax.swing.*;

import database.CategoryService;

import java.awt.*;

public class AddCategory extends JDialog {
    private JTextField categoryField;
    private String categoryName;
    private static boolean isCategoryAdded = false;

    public AddCategory(JFrame parent) {
        super(parent, "Add Category", true);
        setLayout(null);
        setSize(350, 150);
        setLocationRelativeTo(parent);

        JLabel label = new JLabel("Category Name:");
        label.setBounds(20, 20, 120, 25);
        add(label);

        categoryField = new JTextField();
        categoryField.setBounds(140, 20, 170, 25);
        add(categoryField);

        JButton okButton = new JButton("OK");
        okButton.setBounds(60, 70, 90, 30);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 70, 90, 30);
        add(cancelButton);

        okButton.addActionListener(e -> {
            categoryName = categoryField.getText().trim();
            
            if (categoryName.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Category name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				
				boolean isAdded = CategoryService.getInstance().InsertCategory(categoryName);
				
				if (isAdded) {
					isCategoryAdded = true;
					JOptionPane.showMessageDialog(this, "Category added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Failed to add category. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					isCategoryAdded = false;
				}
			}
        });

        cancelButton.addActionListener(e -> {
            categoryName = null;
            dispose();
        });
    }
    
    public static boolean isCategoryAdded() {
		return isCategoryAdded;
	}
}

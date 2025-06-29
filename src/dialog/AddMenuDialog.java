package dialog;

import javax.swing.*;

import database.CategoryService;
import model.CategoryModel;
import model.MenuItemModel;
import database.MenuService;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddMenuDialog extends JDialog {
    private JTextField nameField;
    private JTextField priceField;
    private JComboBox<String> categoryCombo;
    private String menuName;
    private int price;
    private String selectedCategoryId;
    private Map<String, String> categoryMap = new HashMap<>();
    List<CategoryModel> categories = new java.util.ArrayList<>();
    private boolean isAdded = false;

    public AddMenuDialog(JFrame parent) {
        super(parent, "Add Menu Item", true);

        setLayout(null);
        setSize(400, 250);
        setLocationRelativeTo(parent);
        
        categories = CategoryService.getInstance().getAllCategories();
        
        for (CategoryModel cat : categories) {
            categoryMap.put(cat.getCategoryName(), cat.getCategoryId());
        }


        JLabel nameLabel = new JLabel("Menu Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 20, 220, 25);
        add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 60, 100, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(130, 60, 220, 25);
        add(priceField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 100, 100, 25);
        add(categoryLabel);

        categoryCombo = new JComboBox<>(categoryMap.keySet().toArray(new String[0]));
        categoryCombo.setBounds(130, 100, 220, 25);
        add(categoryCombo);

        JButton okButton = new JButton("OK");
        okButton.setBounds(80, 160, 90, 30);
        add(okButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(210, 160, 90, 30);
        add(cancelButton);

        okButton.addActionListener(e -> {
            menuName = nameField.getText().trim();
            try {
                price = Integer.parseInt(priceField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String selectedCategory = (String) categoryCombo.getSelectedItem();
            selectedCategoryId = categoryMap.get(selectedCategory);
            
          
           
            boolean isAdded = MenuService.getInstance().addMenuItem(menuName, price, selectedCategoryId);
            if (isAdded) {
				this.isAdded = true;
				JOptionPane.showMessageDialog(this, "Menu item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "Failed to add menu item.", "Error", JOptionPane.ERROR_MESSAGE);
			}
        });

        cancelButton.addActionListener(e -> {
            menuName = null;
            selectedCategoryId = null;
            dispose();
        });
    }

   public boolean isAdded() {
		return isAdded;
	}

}

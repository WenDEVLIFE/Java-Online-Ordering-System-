package dialog;

import javax.swing.*;
import database.CategoryService;
import database.MenuService;
import model.CategoryModel;
import model.MenuItemModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EditMenuDialog extends JDialog {
    private JTextField nameField;
    private JTextField priceField;
    private JComboBox<String> categoryCombo;
    private String menuName;
    private int price;
    private String selectedCategoryId;
    private Map<String, String> categoryMap = new HashMap<>();
    private boolean isUpdated = false;

    public EditMenuDialog(JFrame parent, MenuItemModel menuItem) {
        super(parent, "Edit Menu Item", true);

        setLayout(null);
        setSize(400, 250);
        setLocationRelativeTo(parent);

        // Load categories
        List<CategoryModel> categories = CategoryService.getInstance().getAllCategories();
        for (CategoryModel cat : categories) {
            categoryMap.put(cat.getCategoryName(), cat.getCategoryId());
        }

        JLabel nameLabel = new JLabel("Menu Name:");
        nameLabel.setBounds(20, 20, 100, 25);
        add(nameLabel);

        nameField = new JTextField(menuItem.getMenuItemName());
        nameField.setBounds(130, 20, 220, 25);
        add(nameField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(20, 60, 100, 25);
        add(priceLabel);

        priceField = new JTextField(String.valueOf(menuItem.getMenuprice()));
        priceField.setBounds(130, 60, 220, 25);
        add(priceField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(20, 100, 100, 25);
        add(categoryLabel);

        categoryCombo = new JComboBox<>(categoryMap.keySet().toArray(new String[0]));
        categoryCombo.setBounds(130, 100, 220, 25);
        add(categoryCombo);

        // Pre-select the current category
        for (Map.Entry<String, String> entry : categoryMap.entrySet()) {
            if (entry.getValue().equals(menuItem.getCategorName())) {
                categoryCombo.setSelectedItem(entry.getKey());
                break;
            }
        }

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

            boolean updated = MenuService.getInstance().updateMenuItem(
                menuItem.getMenuItemId(), menuName, price, selectedCategoryId
            );
            if (updated) {
                isUpdated = true;
                JOptionPane.showMessageDialog(this, "Menu item updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update menu item.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            dispose();
        });
    }

    public boolean isUpdated() {
        return isUpdated;
    }
}

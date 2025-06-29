package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.AccountService;
import database.CategoryService;
import database.MenuService;
import database.OrderService;
import database.RiderService;
import database.StatisticService;
import dialog.AddCategory;
import dialog.AddCategoryDialog;
import dialog.AddMenuDialog;
import dialog.AddRider;
import dialog.EditMenuDialog;
import dialog.ViewRiderDialog;
import model.CategoryModel;
import model.MenuItemModel;
import model.OrderModel;
import model.RiderModel;

import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class ResturantOwnerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField searchCategoryField;
	private JTable categoryTable;
	private JTable menuTable;
	private JTextField searchMenuField;
	private JTable orderTable;
	private JTextField searchOrderField;
	private JTable riderTable;
	private JTextField searchRiderField;
	private JLabel riderCount;
	private JLabel categoryCount;
	private JLabel menuCount;
	private JLabel pendingOrderCount;
	private JLabel totalOrderCount;
	private JLabel activeRiderCount;
	
	DefaultTableModel categoryTableModel, menuTableModel, orderTableModel, riderTableModel;
	List<CategoryModel> categoryList = new java.util.ArrayList<>();
	List<MenuItemModel> menuList = new java.util.ArrayList<>();
	List<OrderModel> orderList = new java.util.ArrayList<>();
	List<RiderModel> riderList = new java.util.ArrayList<>();
	private int userId;
	private JPasswordField newPasswordField;
	private JPasswordField oldPaswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( new com.formdev.flatlaf.FlatDarculaLaf());
					ResturantOwnerFrame frame = new ResturantOwnerFrame();
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
	public ResturantOwnerFrame() {
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 15));
		tabbedPane.setBounds(0, 0, 1184, 561);
		contentPane.add(tabbedPane);
		
		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Dashboard", null, dashboardPanel, null);
		tabbedPane.setForegroundAt(0, new Color(0, 0, 0));
		dashboardPanel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBounds(67, 41, 311, 187);
		dashboardPanel.add(panel_2);
		
		JLabel lblRiders_1 = new JLabel("Riders");
		lblRiders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRiders_1.setBounds(113, 40, 128, 45);
		panel_2.add(lblRiders_1);
		
		riderCount = new JLabel("0");
		riderCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		riderCount.setBounds(127, 101, 128, 45);
		panel_2.add(riderCount);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(426, 41, 311, 187);
		dashboardPanel.add(panel_1_2);
		
		JLabel lblCategories_1 = new JLabel("Categories");
		lblCategories_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCategories_1.setBounds(89, 39, 128, 45);
		panel_1_2.add(lblCategories_1);
		
		categoryCount = new JLabel("0");
		categoryCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		categoryCount.setBounds(127, 101, 128, 45);
		panel_1_2.add(categoryCount);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBounds(801, 41, 311, 187);
		dashboardPanel.add(panel_1_1_2);
		
		JLabel lblMenu_1 = new JLabel("Menu");
		lblMenu_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMenu_1.setBounds(116, 39, 128, 45);
		panel_1_1_2.add(lblMenu_1);
		
		menuCount = new JLabel("0");
		menuCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		menuCount.setBounds(127, 101, 128, 45);
		panel_1_1_2.add(menuCount);
		
		JPanel panel_1_1_1_2_1 = new JPanel();
		panel_1_1_1_2_1.setLayout(null);
		panel_1_1_1_2_1.setBounds(801, 274, 311, 187);
		dashboardPanel.add(panel_1_1_1_2_1);
		
		JLabel lblPendingOrders_1 = new JLabel("Pending Orders");
		lblPendingOrders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPendingOrders_1.setBounds(59, 45, 183, 45);
		panel_1_1_1_2_1.add(lblPendingOrders_1);
		
		pendingOrderCount = new JLabel("0");
		pendingOrderCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		pendingOrderCount.setBounds(127, 101, 128, 45);
		panel_1_1_1_2_1.add(pendingOrderCount);
		
		JPanel panel_1_1_1_3 = new JPanel();
		panel_1_1_1_3.setLayout(null);
		panel_1_1_1_3.setBounds(426, 274, 311, 187);
		dashboardPanel.add(panel_1_1_1_3);
		
		JLabel lblTotalOrders_1 = new JLabel("Total Orders");
		lblTotalOrders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTotalOrders_1.setBounds(72, 39, 128, 45);
		panel_1_1_1_3.add(lblTotalOrders_1);
		
		totalOrderCount = new JLabel("0");
		totalOrderCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		totalOrderCount.setBounds(127, 101, 128, 45);
		panel_1_1_1_3.add(totalOrderCount);
		
		JPanel panel_1_1_1_1_1 = new JPanel();
		panel_1_1_1_1_1.setLayout(null);
		panel_1_1_1_1_1.setBounds(67, 274, 311, 187);
		dashboardPanel.add(panel_1_1_1_1_1);
		
		JLabel lblActiveRiders_1 = new JLabel("Active Riders");
		lblActiveRiders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblActiveRiders_1.setBounds(72, 39, 128, 45);
		panel_1_1_1_1_1.add(lblActiveRiders_1);
		
		activeRiderCount = new JLabel("0");
		activeRiderCount.setFont(new Font("SansSerif", Font.BOLD, 20));
		activeRiderCount.setBounds(127, 101, 128, 45);
		panel_1_1_1_1_1.add(activeRiderCount);
		
		JButton btnLogout_1_4 = new JButton("Logout");
		btnLogout_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_4.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_4.setBounds(1079, 480, 90, 36);
		dashboardPanel.add(btnLogout_1_4);
		
		JPanel categorypane = new JPanel();
		categorypane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Category", null, categorypane, null);
		tabbedPane.setForegroundAt(1, new Color(0, 0, 0));
		categorypane.setLayout(null);
		
		searchCategoryField = new JTextField();
		searchCategoryField.setColumns(10);
		searchCategoryField.setBounds(447, 28, 665, 36);
		categorypane.add(searchCategoryField);
		
		categoryTable = new JTable();
		categoryTable.setBounds(68, 76, 1044, 380);
		categorypane.add(categoryTable);
		
		JLabel lblSearch_1 = new JLabel("Search");
		lblSearch_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1.setBounds(309, 20, 128, 45);
		categorypane.add(lblSearch_1);
		
		JButton btnAddCategory = new JButton("Add Category");
		btnAddCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddCategory addCategoryDialog = new AddCategory(ResturantOwnerFrame.this);
				addCategoryDialog.setVisible(true);
				if (AddCategory.isCategoryAdded()) {
					LoadCategoryTable();
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Category added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Failed to add category. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				 
			}
		});
		btnAddCategory.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddCategory.setBounds(226, 467, 262, 49);
		categorypane.add(btnAddCategory);
		
		JButton btnDeleteCategory = new JButton("Delete Category");
		btnDeleteCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int  selectedRow = categoryTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Please select a category to delete.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				CategoryModel selectedCategory = categoryList.get(selectedRow);
				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to delete the category: " + selectedCategory.getCategoryName() + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					boolean isDeleted = CategoryService.getInstance().deleteCategory(selectedCategory.getCategoryId());
					if (isDeleted) {
						LoadCategoryTable();
						JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Category deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Failed to delete category. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDeleteCategory.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDeleteCategory.setBounds(636, 467, 262, 49);
		categorypane.add(btnDeleteCategory);
		
		JButton btnLogout_1_1 = new JButton("Logout");
		btnLogout_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_1.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_1.setBounds(1079, 482, 90, 36);
		categorypane.add(btnLogout_1_1);
		
		JPanel menupane = new JPanel();
		menupane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Menu", null, menupane, null);
		tabbedPane.setForegroundAt(2, new Color(0, 0, 0));
		menupane.setLayout(null);
		
		menuTable = new JTable();
		menuTable.setBounds(60, 76, 1044, 380);
		menupane.add(menuTable);
		
		JButton btnAddMenu = new JButton("Add Menu");
		btnAddMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddMenuDialog addMenuDialog = new AddMenuDialog(ResturantOwnerFrame.this);
				addMenuDialog.setVisible(true);
				if (addMenuDialog.isAdded()) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Menu item added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					loadDashboard();
					LoadMenuTable();
				} else {
				
				}
			}
		});
		btnAddMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddMenu.setBounds(148, 467, 262, 49);
		menupane.add(btnAddMenu);
		
		searchMenuField = new JTextField();
		searchMenuField.setColumns(10);
		searchMenuField.setBounds(439, 28, 665, 36);
		menupane.add(searchMenuField);
		
		JLabel lblSearch_1_1 = new JLabel("Search");
		lblSearch_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_1.setBounds(301, 20, 128, 45);
		menupane.add(lblSearch_1_1);
		
		JButton btnEditMenu = new JButton("Edit Menu");
		btnEditMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = menuTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Please select a menu item to edit.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MenuItemModel selectedMenu = menuList.get(selectedRow);
				EditMenuDialog editMenuDialog = new EditMenuDialog(ResturantOwnerFrame.this, selectedMenu);
				editMenuDialog.setVisible(true);
				if (editMenuDialog.isUpdated()) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Menu item updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					loadDashboard();
					LoadMenuTable();
				} else {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Failed to update menu item. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnEditMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEditMenu.setBounds(439, 467, 262, 49);
		menupane.add(btnEditMenu);
		
		JButton btnDeleteMenu = new JButton("Delete Menu");
		btnDeleteMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = menuTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Please select a menu item to delete.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				MenuItemModel selectedMenu = menuList.get(selectedRow);
				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to delete the menu item: " + selectedMenu.getMenuItemName() + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					boolean isDeleted = MenuService.getInstance().deleteMenuItem(selectedMenu.getMenuItemId());
					if (isDeleted) {
						LoadMenuTable();
						JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Menu item deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Failed to delete menu item. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnDeleteMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDeleteMenu.setBounds(727, 467, 262, 49);
		menupane.add(btnDeleteMenu);
		
		JButton btnLogout_1_2 = new JButton("Logout");
		btnLogout_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_2.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_2.setBounds(1075, 482, 90, 36);
		menupane.add(btnLogout_1_2);
		
		JPanel orderpane = new JPanel();
		orderpane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Orders", null, orderpane, null);
		tabbedPane.setForegroundAt(3, new Color(0, 0, 0));
		orderpane.setLayout(null);
		
		orderTable = new JTable();
		orderTable.setBounds(71, 67, 1044, 380);
		orderpane.add(orderTable);
		
		searchOrderField = new JTextField();
		searchOrderField.setColumns(10);
		searchOrderField.setBounds(450, 19, 665, 36);
		orderpane.add(searchOrderField);
		
		JLabel lblSearch_1_1_1 = new JLabel("Search");
		lblSearch_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_1_1.setBounds(312, 11, 128, 45);
		orderpane.add(lblSearch_1_1_1);
		
		JButton btnSetAsIn = new JButton("Set as in delivery");
		btnSetAsIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = orderTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Please select an order to set as in delivery.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				OrderModel selectedOrder = orderList.get(selectedRow);
				String riderName = JOptionPane.showInputDialog(ResturantOwnerFrame.this, "Enter Rider Name for Order ID: " + selectedOrder.getOrderId());
				
				if (riderName != null && !riderName.trim().isEmpty()) {
					boolean isUpdated = OrderService.getInstance().setOrderInDelivery(selectedOrder.getOrderId());
					if (isUpdated) {
						LoadAllOrdersTable();
						JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Order set as in delivery successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Failed to set order as in delivery. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Rider name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSetAsIn.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSetAsIn.setBounds(450, 458, 262, 49);
		orderpane.add(btnSetAsIn);
		
		JButton btnLogout_1_3 = new JButton("Logout");
		btnLogout_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_3.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_3.setBounds(1079, 480, 90, 36);
		orderpane.add(btnLogout_1_3);
		
		JPanel riderpane = new JPanel();
		riderpane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Rider", null, riderpane, null);
		tabbedPane.setForegroundAt(4, new Color(0, 0, 0));
		riderpane.setLayout(null);
		
		riderTable = new JTable();
		riderTable.setBounds(60, 76, 1044, 380);
		riderpane.add(riderTable);
		
		searchRiderField = new JTextField();
		searchRiderField.setColumns(10);
		searchRiderField.setBounds(439, 28, 665, 36);
		riderpane.add(searchRiderField);
		
		JLabel lblSearch_1_2 = new JLabel("Search");
		lblSearch_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_2.setBounds(301, 20, 128, 45);
		riderpane.add(lblSearch_1_2);
		
		JButton btnAddRider = new JButton("Add Rider");
		btnAddRider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddRider addRiderDialog = new AddRider(ResturantOwnerFrame.this);
				addRiderDialog.setVisible(true);
				if (AddRider.isRiderAdded()) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Rider added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
					loadDashboard();
					LoadRiderTable();
				} 
			}
		});
		btnAddRider.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddRider.setBounds(164, 467, 262, 49);
		riderpane.add(btnAddRider);
		
		JButton btnUpdateRider = new JButton("View Rider");
		btnUpdateRider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = riderTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Please select a rider to view.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				RiderModel selectedRider = riderList.get(selectedRow);
				String riderId = selectedRider.getRiderId();
				int riderIdInt = Integer.parseInt(riderId);
				ViewRiderDialog viewRiderDialog = new ViewRiderDialog(ResturantOwnerFrame.this, riderIdInt);
				viewRiderDialog.setVisible(true);
			}
		});
		btnUpdateRider.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnUpdateRider.setBounds(646, 467, 262, 49);
		riderpane.add(btnUpdateRider);
		
		JButton btnLogout_1 = new JButton("Logout");
		btnLogout_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(ResturantOwnerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1.setBounds(1079, 482, 90, 36);
		riderpane.add(btnLogout_1);

		
		String [] categoryColumnNames = {"Category ID", "Category Name"};
		String [] menuColumnNames = {"Menu ID", "Menu Name", "Price", "CategoryName"};
		String [] orderColumnNames = {"Order ID", "Customer Name", "Menu Item", "Menu Price", "Total Amount", "Quantity", "Order Status", "Rider Name"};
		String [] riderColumnNames = {"Rider ID", "Rider Name", "Phone Number", "Status"};
		categoryTableModel = new DefaultTableModel(categoryColumnNames, 0);
		categoryTable.setModel(categoryTableModel);
		
		TableRowSorter<DefaultTableModel> categorySorter = new TableRowSorter<>(categoryTableModel);
		categoryTable.setRowSorter(categorySorter);
		
		searchCategoryField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				String searchText = searchCategoryField.getText().toLowerCase();
				if (searchText.trim().isEmpty()) {
					LoadCategoryTable(); 
					
				} else {
					categorySorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
				}
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}

			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}
		});
		
		menuTableModel = new DefaultTableModel(menuColumnNames, 0);
		menuTable.setModel(menuTableModel);
		
		TableRowSorter<DefaultTableModel> menuSorter = new TableRowSorter<>(menuTableModel);
		menuTable.setRowSorter(menuSorter);
		
		searchMenuField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				String searchText = searchMenuField.getText().toLowerCase();
				if (searchText.trim().isEmpty()) {
					LoadMenuTable(); 
					
				} else {
					menuSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
				}
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}

			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}
		});
		
		orderTableModel = new DefaultTableModel(orderColumnNames, 0);
		orderTable.setModel(orderTableModel);
		
		
		TableRowSorter<DefaultTableModel> orderSorter = new TableRowSorter<>(orderTableModel);
		orderTable.setRowSorter(orderSorter);
		
		searchOrderField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				String searchText = searchOrderField.getText().toLowerCase();
				if (searchText.trim().isEmpty()) {
					LoadAllOrdersTable(); 
					
				} else {
					orderSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
				}
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}

			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}
		});
		
		
		riderTableModel = new DefaultTableModel(riderColumnNames, 0);
		riderTable.setModel(riderTableModel);
		
		TableRowSorter<DefaultTableModel> riderSorter = new TableRowSorter<>(riderTableModel);
		riderTable.setRowSorter(riderSorter);
		
		searchRiderField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			@Override
			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				String searchText = searchRiderField.getText().toLowerCase();
				if (searchText.trim().isEmpty()) {
					LoadRiderTable(); 
					
				} else {
					riderSorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
				}
			}

			@Override
			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}

			@Override
			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				insertUpdate(e);
			}
		});
		
		JPanel updatePanel = new JPanel();
		updatePanel.setLayout(null);
		updatePanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Change Password", null, updatePanel, null);
		
		JLabel lblUpdatePassword = new JLabel("Update Password");
		lblUpdatePassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblUpdatePassword.setBounds(82, 22, 217, 45);
		updatePanel.add(lblUpdatePassword);
		
		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblOldPassword.setBounds(134, 129, 150, 45);
		updatePanel.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewPassword.setBounds(134, 214, 150, 45);
		updatePanel.add(lblNewPassword);
		
		newPasswordField = new JPasswordField();
		newPasswordField.setBounds(322, 216, 389, 43);
		updatePanel.add(newPasswordField);
		
		oldPaswordField = new JPasswordField();
		oldPaswordField.setBounds(322, 129, 389, 43);
		updatePanel.add(oldPaswordField);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPassword = new String(oldPaswordField.getPassword()).trim();
				String newPassword = new String(newPasswordField.getPassword()).trim();
				
				if (oldPassword.isEmpty() || newPassword.isEmpty()) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Old Password and New Password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				AccountService accountService = AccountService.getInstance();
				boolean isPasswordChanged = accountService.updatePassword(userId, oldPassword, newPassword);
				
				if (isPasswordChanged) {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					oldPaswordField.setText("");
					newPasswordField.setText("");
				} else {
					JOptionPane.showMessageDialog(ResturantOwnerFrame.this, "Failed to update password. Please check your old password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnChangePassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnChangePassword.setBounds(367, 346, 262, 49);
		updatePanel.add(btnChangePassword);
		
		JButton btnLogout_3 = new JButton("Logout");
		btnLogout_3.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_3.setBounds(1079, 485, 90, 36);
		updatePanel.add(btnLogout_3);
		LoadCategoryTable();
		 loadDashboard();
		 LoadRiderTable();
		 LoadMenuTable();
	}

	public void setUserId(int userId) {
		// TODO Auto-generated method stub
		
		this.userId = userId;
	
	}
	
	public void LoadCategoryTable() {
		categoryList.clear();
		
		categoryTableModel.setRowCount(0);
		
		categoryList = CategoryService.getInstance().getAllCategories();
		
		for (CategoryModel category : categoryList) {
			Object[] row = {category.getCategoryId(), category.getCategoryName()};
			categoryTableModel.addRow(row);
		}
	}
	
	public void loadDashboard() {

	   int orderCount = StatisticService.getInstance().getOrderCount();
	   int pendingOrderCountValue = StatisticService.getInstance().getPendingOrders();
	   int riderCountValue = StatisticService.getInstance().getTotalRiders();
	   int menuItemCount = StatisticService.getInstance().getMenuItemCount();
	   int categoryCountValue = StatisticService.getInstance().getCategoryCount();
	   int activeRiderCountValue = StatisticService.getInstance().getAvailaleRiders();
	   
	   totalOrderCount.setText(String.valueOf(orderCount));
	   pendingOrderCount.setText(String.valueOf(pendingOrderCountValue));
	   riderCount.setText(String.valueOf(riderCountValue));
	   menuCount.setText(String.valueOf(menuItemCount));
	   categoryCount.setText(String.valueOf(categoryCountValue));
	   activeRiderCount.setText(String.valueOf(activeRiderCountValue));
	   
		
	}
	
	public void LoadRiderTable() {
		riderList.clear();
		
		riderTableModel.setRowCount(0);
		
		riderList = RiderService.getInstance().getAllRiders();
		
		for (RiderModel rider : riderList) {
			Object[] row = {rider.getRiderId(), rider.getRiderName(), rider.getRiderPhoneNumber(), rider.getStatus()};
			riderTableModel.addRow(row);
		}
	}
		
	public void LoadAllOrdersTable() {
		
		 orderList.clear();
		 
		 orderTableModel.setRowCount(0);
		 
		 orderList = OrderService.getInstance().getAllOrders();
		 
		 for (OrderModel order : orderList) {
			 Object[] row = {order.getOrderId(), order.getCustomerName(), order.getMenuName(), "P" + order.getMenuPrice(), "P" + order.getTotalAmount(), order.getQuantity(), order.getOrderStatus(), order.getRiderName()};
			 orderTableModel.addRow(row);
		 }
	}

	 public void LoadMenuTable() {
		 menuList.clear();
		 
		 menuTableModel.setRowCount(0);
		 
		 menuList = MenuService.getInstance().getAllMenuItems();
		 
		 for (MenuItemModel menu : menuList) {
			 Object[] row = {menu.getMenuItemId(), menu.getMenuItemName(), "P" + menu.getMenuprice(), menu.getCategorName()};
			 menuTableModel.addRow(row);
		 }
		 
		
	 }

}

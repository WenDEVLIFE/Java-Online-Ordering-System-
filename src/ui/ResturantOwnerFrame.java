package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.CategoryService;
import dialog.AddCategory;
import dialog.AddCategoryDialog;
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
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class ResturantOwnerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable categoryTable;
	private JTable menuTable;
	private JTextField textField_1;
	private JTable orderTable;
	private JTextField textField_2;
	private JTable riderTable;
	private JTextField textField_3;
	DefaultTableModel categoryTableModel, menuTableModel, orderTableModel, riderTableModel;
	List<CategoryModel> categoryList = new java.util.ArrayList<>();
	List<MenuItemModel> menuList = new java.util.ArrayList<>();
	List<OrderModel> orderList = new java.util.ArrayList<>();
	List<RiderModel> riderList = new java.util.ArrayList<>();
	private int userId;

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
		
		JLabel lblNewLabel_1_2 = new JLabel("0");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(127, 101, 128, 45);
		panel_2.add(lblNewLabel_1_2);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(426, 41, 311, 187);
		dashboardPanel.add(panel_1_2);
		
		JLabel lblCategories_1 = new JLabel("Categories");
		lblCategories_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCategories_1.setBounds(89, 39, 128, 45);
		panel_1_2.add(lblCategories_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("0");
		lblNewLabel_1_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(127, 101, 128, 45);
		panel_1_2.add(lblNewLabel_1_1_2);
		
		JPanel panel_1_1_2 = new JPanel();
		panel_1_1_2.setLayout(null);
		panel_1_1_2.setBounds(801, 41, 311, 187);
		dashboardPanel.add(panel_1_1_2);
		
		JLabel lblMenu_1 = new JLabel("Menu");
		lblMenu_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMenu_1.setBounds(116, 39, 128, 45);
		panel_1_1_2.add(lblMenu_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("0");
		lblNewLabel_1_1_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_1_2.setBounds(127, 101, 128, 45);
		panel_1_1_2.add(lblNewLabel_1_1_1_2);
		
		JPanel panel_1_1_1_2_1 = new JPanel();
		panel_1_1_1_2_1.setLayout(null);
		panel_1_1_1_2_1.setBounds(801, 274, 311, 187);
		dashboardPanel.add(panel_1_1_1_2_1);
		
		JLabel lblPendingOrders_1 = new JLabel("Pending Orders");
		lblPendingOrders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPendingOrders_1.setBounds(59, 45, 183, 45);
		panel_1_1_1_2_1.add(lblPendingOrders_1);
		
		JLabel lblNewLabel_1_1_1_1_2_1 = new JLabel("0");
		lblNewLabel_1_1_1_1_2_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_1_1_2_1.setBounds(127, 101, 128, 45);
		panel_1_1_1_2_1.add(lblNewLabel_1_1_1_1_2_1);
		
		JPanel panel_1_1_1_3 = new JPanel();
		panel_1_1_1_3.setLayout(null);
		panel_1_1_1_3.setBounds(426, 274, 311, 187);
		dashboardPanel.add(panel_1_1_1_3);
		
		JLabel lblTotalOrders_1 = new JLabel("Total Orders");
		lblTotalOrders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblTotalOrders_1.setBounds(72, 39, 128, 45);
		panel_1_1_1_3.add(lblTotalOrders_1);
		
		JLabel lblNewLabel_1_1_1_1_3 = new JLabel("0");
		lblNewLabel_1_1_1_1_3.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_1_1_3.setBounds(127, 101, 128, 45);
		panel_1_1_1_3.add(lblNewLabel_1_1_1_1_3);
		
		JPanel panel_1_1_1_1_1 = new JPanel();
		panel_1_1_1_1_1.setLayout(null);
		panel_1_1_1_1_1.setBounds(67, 274, 311, 187);
		dashboardPanel.add(panel_1_1_1_1_1);
		
		JLabel lblActiveRiders_1 = new JLabel("Active Riders");
		lblActiveRiders_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblActiveRiders_1.setBounds(72, 39, 128, 45);
		panel_1_1_1_1_1.add(lblActiveRiders_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("0");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_1_1_1_1.setBounds(127, 101, 128, 45);
		panel_1_1_1_1_1.add(lblNewLabel_1_1_1_1_1_1);
		
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
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(447, 28, 665, 36);
		categorypane.add(textField);
		
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
		btnAddMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddMenu.setBounds(148, 467, 262, 49);
		menupane.add(btnAddMenu);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(439, 28, 665, 36);
		menupane.add(textField_1);
		
		JLabel lblSearch_1_1 = new JLabel("Search");
		lblSearch_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_1.setBounds(301, 20, 128, 45);
		menupane.add(lblSearch_1_1);
		
		JButton btnEditMenu = new JButton("Edit Menu");
		btnEditMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEditMenu.setBounds(439, 467, 262, 49);
		menupane.add(btnEditMenu);
		
		JButton btnDeleteMenu = new JButton("Delete Menu");
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
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(450, 19, 665, 36);
		orderpane.add(textField_2);
		
		JLabel lblSearch_1_1_1 = new JLabel("Search");
		lblSearch_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_1_1.setBounds(312, 11, 128, 45);
		orderpane.add(lblSearch_1_1_1);
		
		JButton btnSetAsDelivered = new JButton("Set as in progress");
		btnSetAsDelivered.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSetAsDelivered.setBounds(296, 458, 262, 49);
		orderpane.add(btnSetAsDelivered);
		
		JButton btnSetAsIn = new JButton("Set as in delivery");
		btnSetAsIn.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSetAsIn.setBounds(597, 458, 262, 49);
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
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(439, 28, 665, 36);
		riderpane.add(textField_3);
		
		JLabel lblSearch_1_2 = new JLabel("Search");
		lblSearch_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_2.setBounds(301, 20, 128, 45);
		riderpane.add(lblSearch_1_2);
		
		JButton btnAddRider = new JButton("Add Rider");
		btnAddRider.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddRider.setBounds(164, 467, 262, 49);
		riderpane.add(btnAddRider);
		
		JButton btnUpdateRider = new JButton("View Rider");
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
		categoryTableModel = new DefaultTableModel(categoryColumnNames, 0);
		categoryTable.setModel(categoryTableModel);
		LoadCategoryTable();
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

}

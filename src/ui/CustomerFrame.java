package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import database.MenuService;
import database.MyProfileService;
import dialog.ChangePasswordDialog;
import dialog.MyProfileDialog;
import model.MenuItemModel;

import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;

public class CustomerFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String fullName;
	private String email;
	private String address;
	private String contactNumber;
	int userId;
	private JTextField searchMenuField;
	private JTable orderTable;
	private JTextField textField_2;
	private JTable paymentHistoryTable;
	private JTextField textField;
	private JTable menuTable;
	private JLabel fullNameText;
	private JLabel emailText;
	private JLabel DeliveryAddress;
	private JLabel contactNumText;
	DefaultTableModel menuTableModel, orderTableModel, paymentHistoryTableModel;
	List<MenuItemModel> menuList = new java.util.ArrayList<>();
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( new com.formdev.flatlaf.FlatDarculaLaf());
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 15));
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(0, 0, 1184, 561);
		contentPane.add(tabbedPane);
		
		JPanel menupane = new JPanel();
		menupane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Menu", null, menupane, null);
		menupane.setLayout(null);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(444, 28, 665, 36);
		menupane.add(textField);
		
		menuTable = new JTable();
		menuTable.setBounds(65, 76, 1044, 380);
		menupane.add(menuTable);
		
		JLabel lblSearch_1_2 = new JLabel("Search");
		lblSearch_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_2.setBounds(306, 20, 128, 45);
		menupane.add(lblSearch_1_2);
		
		JButton btnSelectItem = new JButton("Select Item");
		btnSelectItem.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSelectItem.setBounds(405, 467, 262, 49);
		menupane.add(btnSelectItem);
		
		JButton btnLogout_1_4 = new JButton("Logout");
		btnLogout_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(CustomerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_4.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_4.setBounds(1079, 482, 90, 36);
		menupane.add(btnLogout_1_4);
		
		JPanel orderpane = new JPanel();
		orderpane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Order", null, orderpane, null);
		orderpane.setLayout(null);
		
		searchMenuField = new JTextField();
		searchMenuField.setColumns(10);
		searchMenuField.setBounds(438, 28, 665, 36);
		orderpane.add(searchMenuField);
		
		JLabel lblSearch_1 = new JLabel("Search");
		lblSearch_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1.setBounds(300, 20, 128, 45);
		orderpane.add(lblSearch_1);
		
		orderTable = new JTable();
		orderTable.setBounds(59, 76, 1044, 380);
		orderpane.add(orderTable);
		
		JButton btnConfirmOrder = new JButton("Confirm Order");
		btnConfirmOrder.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnConfirmOrder.setBounds(217, 467, 262, 49);
		orderpane.add(btnConfirmOrder);
		
		JButton btnSetAsReceive = new JButton("Set as receive order");
		btnSetAsReceive.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSetAsReceive.setBounds(724, 467, 262, 49);
		orderpane.add(btnSetAsReceive);
		
		JButton btnLogout_1_4_1 = new JButton("Logout");
		btnLogout_1_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(CustomerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_4_1.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_4_1.setBounds(1079, 482, 90, 36);
		orderpane.add(btnLogout_1_4_1);
		
		JPanel paymentpane = new JPanel();
		paymentpane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Payment History", null, paymentpane, null);
		paymentpane.setLayout(null);
		
		JLabel lblSearch_1_1 = new JLabel("Search");
		lblSearch_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch_1_1.setBounds(317, 24, 128, 45);
		paymentpane.add(lblSearch_1_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(455, 32, 665, 36);
		paymentpane.add(textField_2);
		
		paymentHistoryTable = new JTable();
		paymentHistoryTable.setBounds(76, 80, 1044, 380);
		paymentpane.add(paymentHistoryTable);
		
		JButton btnLogout_1_4_2 = new JButton("Logout");
		btnLogout_1_4_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(CustomerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_4_2.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_4_2.setBounds(1079, 480, 90, 36);
		paymentpane.add(btnLogout_1_4_2);
		
		JPanel profilepane = new JPanel();
		profilepane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("My Profile", null, profilepane, null);
		profilepane.setLayout(null);
		
		JLabel lblMyProfile = new JLabel("My Profile");
		lblMyProfile.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblMyProfile.setBounds(72, 34, 128, 45);
		profilepane.add(lblMyProfile);
		
		JButton btnAddToOrder = new JButton("Update Profile");
		btnAddToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Map <String, String> userProfile = new HashMap<>();
				userProfile.put("fullName", fullNameText.getText());
				userProfile.put("email", emailText.getText());
				userProfile.put("contact", contactNumText.getText());
				userProfile.put("address", DeliveryAddress.getText());
				userProfile.put("userId", String.valueOf(userId));
				
				
				MyProfileDialog myProfileDialog = new MyProfileDialog(CustomerFrame.this, userProfile);
				myProfileDialog.setVisible(true);
				if (myProfileDialog.isSaved()) {
					JOptionPane.showMessageDialog(CustomerFrame.this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					LoadProfile(); 
				}
			}
		});
		btnAddToOrder.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddToOrder.setBounds(197, 423, 262, 49);
		profilepane.add(btnAddToOrder);
		
		JLabel lblFullName = new JLabel("Full Name:");
		lblFullName.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblFullName.setBounds(100, 106, 128, 45);
		profilepane.add(lblFullName);
		
		fullNameText = new JLabel("John Doe");
		fullNameText.setFont(new Font("SansSerif", Font.BOLD, 20));
		fullNameText.setBounds(100, 180, 262, 45);
		profilepane.add(fullNameText);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblEmail.setBounds(100, 254, 128, 45);
		profilepane.add(lblEmail);
		
		emailText = new JLabel("test@gmail.com");
		emailText.setFont(new Font("SansSerif", Font.BOLD, 20));
		emailText.setBounds(100, 310, 262, 45);
		profilepane.add(emailText);
		
		JLabel lblDeliveryAddress = new JLabel("Delivery Address");
		lblDeliveryAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDeliveryAddress.setBounds(396, 106, 391, 45);
		profilepane.add(lblDeliveryAddress);
		
		DeliveryAddress = new JLabel("Davao City");
		DeliveryAddress.setFont(new Font("SansSerif", Font.BOLD, 20));
		DeliveryAddress.setBounds(396, 180, 262, 45);
		profilepane.add(DeliveryAddress);
		
		JLabel lblContactNumber_1 = new JLabel("Contact Number");
		lblContactNumber_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblContactNumber_1.setBounds(396, 254, 391, 45);
		profilepane.add(lblContactNumber_1);
		
		contactNumText = new JLabel("09912094870");
		contactNumText.setFont(new Font("SansSerif", Font.BOLD, 20));
		contactNumText.setBounds(396, 310, 262, 45);
		profilepane.add(contactNumText);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordDialog changePasswordDialog = new ChangePasswordDialog(CustomerFrame.this, userId);
				changePasswordDialog.setVisible(true);
				if (changePasswordDialog.isChanged()) {
					JOptionPane.showMessageDialog(CustomerFrame.this, "Password updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					LoadProfile(); // Reload profile to reflect changes
				}
			}
		});
		btnChangePassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnChangePassword.setBounds(596, 423, 262, 49);
		profilepane.add(btnChangePassword);
		
		JButton btnLogout_1_4_3 = new JButton("Logout");
		btnLogout_1_4_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(CustomerFrame.this, "Are you sure you want to logout?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame loginFrame = new LoginFrame();
					loginFrame.setVisible(true);
				}
			}
		});
		btnLogout_1_4_3.setFont(new Font("SansSerif", Font.BOLD, 10));
		btnLogout_1_4_3.setBounds(1079, 480, 90, 36);
		profilepane.add(btnLogout_1_4_3);
		
		
		String [] menuColumnNames = {"Menu ID", "Menu Name", "Price", "CategoryName"};
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

		LoadMenuTable();
		LoadProfile();
	}

	public void setUserId(int userId) {
		 this.userId = userId;
		
	}
	
	 public void LoadMenuTable() {
		 menuList.clear();
		 
		 menuTableModel.setRowCount(0);
		 
		 menuList = MenuService.getInstance().getAllMenuItems();
		 
		 for (MenuItemModel menu : menuList) {
			 Object[] row = {menu.getMenuItemId(), menu.getMenuItemName(), menu.getMenuprice(), menu.getCategorName()};
			 menuTableModel.addRow(row);
		 }
		 
		
	 }
	 
	 public void LoadProfile() {
		 
		 this.fullName =  MyProfileService.getInstance().getFullName(userId);
		 this.email = MyProfileService.getInstance().getEmail(userId);
		 this.address = MyProfileService.getInstance().getAddress(userId);
		 this.contactNumber = MyProfileService.getInstance().getContactNumber(userId);
		 
		 fullNameText.setText(fullName != null ? fullName : "N/A");
		 emailText.setText(email != null ? email : "N/A");
		 DeliveryAddress.setText(address != null ? address : "N/A");
		 contactNumText.setText(contactNumber != null ? contactNumber : "N/A");
		
		 
	 }


}

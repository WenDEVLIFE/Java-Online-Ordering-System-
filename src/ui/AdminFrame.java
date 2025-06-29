package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import database.AccountService;
import database.LogService;
import dialog.AddUser;
import dialog.EditUser;
import model.LogModel;
import model.UserModel;

import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable AccountTable;
	private JTextField textField;
	private JTable table_1;
	private JPasswordField newPasswordField;
	private JPasswordField oldPasswordField;
	DefaultTableModel accountTableModel, LogTableModel;
	List<UserModel> accountList = new ArrayList<>();
	List<LogModel> logList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel( new com.formdev.flatlaf.FlatDarculaLaf());
					AdminFrame frame = new AdminFrame();
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
	public AdminFrame() {
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 11));
		tabbedPane.setForeground(new Color(0, 0, 0));
		tabbedPane.setBackground(new Color(255, 255, 255));
		tabbedPane.setBounds(0, 0, 1184, 561);
		contentPane.add(tabbedPane);
		
		JPanel dashboardPanel = new JPanel();
		dashboardPanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Dashboard", null, dashboardPanel, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		dashboardPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(80, 86, 311, 187);
		dashboardPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Administrator");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(79, 45, 128, 45);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("0");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1.setBounds(127, 101, 128, 45);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(435, 86, 311, 187);
		dashboardPanel.add(panel_1);
		
		JLabel lblRestaurantAdmin = new JLabel("Restaurant Admin");
		lblRestaurantAdmin.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblRestaurantAdmin.setBounds(79, 45, 187, 45);
		panel_1.add(lblRestaurantAdmin);
		
		JLabel lblNewLabel_1_1 = new JLabel("0");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(127, 101, 128, 45);
		panel_1.add(lblNewLabel_1_1);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBounds(786, 86, 311, 187);
		dashboardPanel.add(panel_1_1);
		
		JLabel lblCustomerAccount = new JLabel("Customer Account");
		lblCustomerAccount.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblCustomerAccount.setBounds(79, 45, 187, 45);
		panel_1_1.add(lblCustomerAccount);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("0");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(127, 101, 128, 45);
		panel_1_1.add(lblNewLabel_1_1_1);
		
		JPanel panel_1_2 = new JPanel();
		panel_1_2.setLayout(null);
		panel_1_2.setBounds(435, 305, 311, 187);
		dashboardPanel.add(panel_1_2);
		
		JLabel lblLogs = new JLabel("Logs");
		lblLogs.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLogs.setBounds(114, 45, 88, 45);
		panel_1_2.add(lblLogs);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("0");
		lblNewLabel_1_1_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_1_1_2.setBounds(136, 101, 128, 45);
		panel_1_2.add(lblNewLabel_1_1_2);
		
		JPanel accoountpanel = new JPanel();
		accoountpanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Account Management", null, accoountpanel, null);
		accoountpanel.setLayout(null);
		
		AccountTable = new JTable();
		AccountTable.setBounds(66, 84, 1044, 380);
		accoountpanel.add(AccountTable);
		
		textField = new JTextField();
		textField.setBounds(445, 36, 665, 36);
		accoountpanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblSearch.setBounds(307, 28, 128, 45);
		accoountpanel.add(lblSearch);
		
		JButton btnAddAccount = new JButton("Add Account");
		btnAddAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddUser addUserDialog = new AddUser(AdminFrame.this);
				addUserDialog.setVisible(true);
				if (addUserDialog.isUserAdded()) {
					JOptionPane.showMessageDialog(AdminFrame.this, "User added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					refreshAccountTable();
					refreshLogTable();
				}
			}
		});
		btnAddAccount.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnAddAccount.setBounds(111, 475, 262, 49);
		accoountpanel.add(btnAddAccount);
		
		JButton btnEditAccount = new JButton("Edit Account");
		btnEditAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = AccountTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(AdminFrame.this, "Please select an account to edit.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				UserModel selectedUser = accountList.get(selectedRow);
				EditUser editUserDialog = new EditUser(AdminFrame.this, selectedUser);
				editUserDialog.setVisible(true);
				if (editUserDialog.isUserEdited()) {
					JOptionPane.showMessageDialog(AdminFrame.this, "User updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					refreshAccountTable();
					refreshLogTable();
				}
			}
		});
		btnEditAccount.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnEditAccount.setBounds(430, 475, 262, 49);
		accoountpanel.add(btnEditAccount);
		
		JButton btnDeleteAccount = new JButton("Delete Account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int selectedRow = AccountTable.getSelectedRow();
				
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(AdminFrame.this, "Please select an account to delete.", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				UserModel selectedUser = accountList.get(selectedRow);
				int confirm = JOptionPane.showConfirmDialog(AdminFrame.this, "Are you sure you want to delete the account of " + selectedUser.getUserName() + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION) {
					AccountService.getInstance().deleteUser(selectedUser.getUserId());
					JOptionPane.showMessageDialog(AdminFrame.this, "Account deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
					refreshAccountTable();
					refreshLogTable();
				}
				
			}
		});
		btnDeleteAccount.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnDeleteAccount.setBounds(734, 475, 262, 49);
		accoountpanel.add(btnDeleteAccount);
		
		JPanel logpanel = new JPanel();
		logpanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Logs", null, logpanel, null);
		logpanel.setLayout(null);
		
		table_1 = new JTable();
		table_1.setBounds(62, 86, 1044, 380);
		logpanel.add(table_1);
		
		JLabel lblLogs_1 = new JLabel("Logs");
		lblLogs_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblLogs_1.setBounds(62, 27, 128, 45);
		logpanel.add(lblLogs_1);
		
		JPanel updatePanel = new JPanel();
		updatePanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Update My Password", null, updatePanel, null);
		updatePanel.setLayout(null);
		
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
		
		oldPasswordField = new JPasswordField();
		oldPasswordField.setBounds(322, 129, 389, 43);
		updatePanel.add(oldPasswordField);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnChangePassword.setBounds(367, 346, 262, 49);
		updatePanel.add(btnChangePassword);
		
		String [] columnNames = {"Username", "Role"};
		String [] logColumnNames = {"Log ID", "Description", "Timestamp"};
		accountTableModel = new DefaultTableModel(columnNames, 0);
		LogTableModel = new DefaultTableModel(logColumnNames, 0);
		AccountTable.setModel(accountTableModel);
		table_1.setModel(LogTableModel);
		
		refreshAccountTable();
		refreshLogTable();
		

	}
	
	public void refreshAccountTable() {
		accountTableModel.setRowCount(0);

		accountList.clear();
		
		accountList = AccountService.getInstance().getAllUsers();
		
		for (UserModel user : accountList) {
			accountTableModel.addRow(new Object[] {
				user.getUserName(),
				user.getRole(),
			});
		}
	}
	
	
	public void refreshLogTable() {
		LogTableModel.setRowCount(0);
		
		logList.clear();
		
		logList = LogService.getInstance().getAllLogs();
		
		for (LogModel log : logList) {
			LogTableModel.addRow(new Object[] {
				log.getLogId(),
				log.getDescription(),
				log.getTimestamp(),
			});
		}
	}
}

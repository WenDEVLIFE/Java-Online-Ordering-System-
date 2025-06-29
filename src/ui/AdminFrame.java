package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class AdminFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

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
		tabbedPane.setBackgroundAt(0, new Color(255, 128, 64));
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
		
		JPanel logpanel = new JPanel();
		logpanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Logs", null, logpanel, null);
		
		JPanel updatePanel = new JPanel();
		updatePanel.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Update My Password", null, updatePanel, null);

	}
}

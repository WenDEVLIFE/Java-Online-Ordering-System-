package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.Font;

public class ResturantOwnerFrame extends JFrame {

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
		
		JPanel categorypane = new JPanel();
		categorypane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Category", null, categorypane, null);
		
		JPanel menupane = new JPanel();
		menupane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Menu", null, menupane, null);
		
		JPanel orderpane = new JPanel();
		orderpane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Orders", null, orderpane, null);
		
		JPanel riderpane = new JPanel();
		riderpane.setBackground(new Color(255, 128, 64));
		tabbedPane.addTab("Rider", null, riderpane, null);

	}

}

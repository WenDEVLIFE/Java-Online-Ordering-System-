package dialog;

import javax.swing.*;

import database.RiderService;

import java.awt.*;
import java.awt.event.*;

public class ReviewDialog extends JDialog {
    private int rating = 0;
    private String feedback = "";
    private int riderId;

    private JComboBox<Integer> ratingCombo;
    private JTextArea feedbackArea;

    public ReviewDialog(Frame parent, int userId, String driverNamed) {
        super(parent, "Review Driver", true);
        setLayout(new BorderLayout(10, 10));
        
        String driverName = RiderService.getInstance().getRiderNameById(driverNamed);
        String driverPhone = RiderService.getInstance().getRiderPhoneById(driverNamed);
        riderId = RiderService.getInstance().getRiderIdByName(driverName);
        

        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        infoPanel.add(new JLabel("Rider Name:"));
        infoPanel.add(new JLabel(driverName));
        infoPanel.add(new JLabel("Phone Number:"));
        infoPanel.add(new JLabel(driverPhone));
        add(infoPanel, BorderLayout.NORTH);

        JPanel ratePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ratePanel.add(new JLabel("Rate (1-5):"));
        ratingCombo = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        ratePanel.add(ratingCombo);
        add(ratePanel, BorderLayout.CENTER);

        JPanel feedbackPanel = new JPanel(new BorderLayout());
        feedbackPanel.add(new JLabel("Feedback:"), BorderLayout.NORTH);
        feedbackArea = new JTextArea(4, 20);
        feedbackPanel.add(new JScrollPane(feedbackArea), BorderLayout.CENTER);
        add(feedbackPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();
        JButton submitButton = new JButton("Submit");
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.PAGE_END);

        submitButton.addActionListener(e -> {
            rating = (Integer) ratingCombo.getSelectedItem();
            feedback = feedbackArea.getText().trim();
            setVisible(false);
        });

        pack();
        setLocationRelativeTo(parent);
    }

    public int getRating() {
        return rating;
    }

    public String getFeedback() {
        return feedback;
    }
    
    public int getRiderId() {
		return riderId;
	}

    
}

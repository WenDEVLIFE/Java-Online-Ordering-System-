package dialog;



import database.RiderService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewRiderReviewDialog extends JDialog {
    public ViewRiderReviewDialog(Frame parent, int riderId) {
        super(parent, "Rider Reviews", true);
        setLayout(new BorderLayout(10, 10));

        JPanel reviewPanel = new JPanel();
        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));

        List<String> reviews = RiderService.getInstance().getReviewsByRiderId(riderId);
        if (reviews.isEmpty()) {
            reviewPanel.add(new JLabel("No reviews found."));
        } else {
            for (String review : reviews) {
                JLabel reviewLabel = new JLabel(review);
                reviewLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 5));
                reviewPanel.add(reviewLabel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(reviewPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }
}

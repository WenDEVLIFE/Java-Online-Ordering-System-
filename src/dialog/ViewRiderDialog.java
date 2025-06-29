package dialog;

import model.RiderModel;
import database.RiderService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewRiderDialog extends JDialog {
    private JPanel ridersPanel;
    boolean isDeleteButtonClicked = false;

    public ViewRiderDialog(Frame parent, int riderId) {
        super(parent, "View Riders", true);
        setLayout(new BorderLayout(10, 10));

        ridersPanel = new JPanel();
        ridersPanel.setLayout(new BoxLayout(ridersPanel, BoxLayout.Y_AXIS));
        loadRiders(riderId);

        JScrollPane scrollPane = new JScrollPane(ridersPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        JButton deleteButton = new JButton("Delete");
        JButton viewReviewButton = new JButton("View Review");
        buttonPanel.add(deleteButton);
        buttonPanel.add(viewReviewButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions (implement as needed)
     
        deleteButton.addActionListener (e -> {
			int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this rider?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				isDeleteButtonClicked = true;
				RiderService.getInstance().deleteRider(riderId); // Assuming deleteRider method exists
				JOptionPane.showMessageDialog(this, "Rider deleted successfully.");
				dispose();
			}
			
			else {
				isDeleteButtonClicked = false;
			}
		});
        viewReviewButton.addActionListener(e -> {
            ViewRiderReviewDialog reviewDialog = new ViewRiderReviewDialog((Frame) getParent(), riderId);
            reviewDialog.setVisible(true);
        });

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    private void loadRiders(int riderId) {
        ridersPanel.removeAll();
        RiderModel rider = RiderService.getInstance().getRiderById(riderId);
        if (rider != null) {
            JLabel label = new JLabel("Name: " + rider.getRiderName() + " | Phone: " + rider.getRiderPhoneNumber());
            label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            ridersPanel.add(label);

          
        } else {
            JLabel label = new JLabel("Rider not found.");
            ridersPanel.add(label);
        }
        ridersPanel.revalidate();
        ridersPanel.repaint();
    }

    
    public boolean isDeleteButtonClicked() {
		return isDeleteButtonClicked;
	}

}

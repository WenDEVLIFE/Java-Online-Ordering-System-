package dialog;

import model.OrderModel;
import model.RiderModel;
import database.RiderService;

import javax.swing.*;
import java.awt.*;

public class PaymentDialog extends JDialog {
    private JComboBox<String> paymentMethodCombo;
    private JComboBox<RiderModel> riderCombo;
    private String selectedRiderId = null;
    private String selectedDriverName = null;

    public PaymentDialog(Frame parent, OrderModel order) {
        super(parent, "Select Payment and Rider", true);
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("Payment Method:"));
        paymentMethodCombo = new JComboBox<>(new String[]{"Cash", "Card"});
        add(paymentMethodCombo);

        add(new JLabel("Available Riders:"));
        riderCombo = new JComboBox<>();
        add(riderCombo);

        JButton okButton = new JButton("OK");
        add(okButton);

        paymentMethodCombo.addActionListener(e -> updateRiders());
        okButton.addActionListener(e -> {
            RiderModel rider = (RiderModel) riderCombo.getSelectedItem();
            if (rider != null) {
                selectedRiderId = rider.getRiderId();
                selectedDriverName = rider.getRiderName();
            }
            setVisible(false);
        });

        updateRiders();
        pack();
        setLocationRelativeTo(parent);
    }

    private void updateRiders() {
        riderCombo.removeAllItems();
        for (RiderModel rider : RiderService.getInstance().getAllRiders()) {
            riderCombo.addItem(rider);
        }
    }

    public String getSelectedRiderId() {
        return selectedRiderId;
    }

    public String getPaymentMethod() {
        return (String) paymentMethodCombo.getSelectedItem();
    }

    public String getSelectedDriverName() {
        return selectedDriverName;
    }
}

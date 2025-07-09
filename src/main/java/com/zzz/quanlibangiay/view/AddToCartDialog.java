package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddToCartDialog extends JDialog {
    private final int maxQty;
    private JTextField txtQuantity;
    private ButtonCustom btnConfirm, btnCancel;
    private int quantity = 0;

    public AddToCartDialog(JFrame parent, String productName, int maxQty) {
        super(parent, "Thêm vào giỏ hàng", true);
        this.maxQty = maxQty;
        init(productName);
    }

    private void init(String productName) {
        setSize(320, 180);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                quantity = 0;
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Sản phẩm:"), gbc);
        gbc.gridx = 1;
        add(new JLabel(productName), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Số lượng (1–" + maxQty + "):"), gbc);
        gbc.gridx = 1;
        txtQuantity = new JTextField("1");
        add(txtQuantity, gbc);

        btnConfirm = new ButtonCustom();
        btnConfirm.setText("Xác nhận");
        btnCancel = new ButtonCustom();
        btnCancel.setText("Hủy");
        JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        pnlBtn.add(btnConfirm);
        pnlBtn.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(pnlBtn, gbc);

        btnConfirm.addActionListener(e -> {
            String text = txtQuantity.getText().trim();
            int val;
            try {
                val = Integer.parseInt(text);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Số lượng phải là số nguyên", "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
                txtQuantity.requestFocus();
                return;
            }
            if (val < 1 || val > maxQty) {
                JOptionPane.showMessageDialog(this,
                        "Số lượng phải từ 1 đến " + maxQty,
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                txtQuantity.requestFocus();
                return;
            }
            quantity = val;
            dispose();
        });

        btnCancel.addActionListener(e -> {
            quantity = 0;
            dispose();
        });

        txtQuantity.requestFocusInWindow();
    }

    public int getQuantity() {
        return quantity;
    }

    public JTextField getTxtQuantity() {
        return txtQuantity;
    }
}

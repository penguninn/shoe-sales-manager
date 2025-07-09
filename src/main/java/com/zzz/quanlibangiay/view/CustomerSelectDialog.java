package com.zzz.quanlibangiay.view;
import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.entity.Customer;
import com.zzz.quanlibangiay.utils.ValidationUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerSelectDialog extends JDialog {
    private JTextField txtPhone;
    private JTextField txtName;
    private ButtonCustom btnCheck;
    private ButtonCustom btnSelect;
    private Customer selectedCustomer;

    public CustomerSelectDialog(JFrame parent) {
        super(parent, "Chọn khách hàng", true);
        init();
    }

    private void init() {
        setSize(400, 250);
        setLocationRelativeTo(getParent());
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblPhone = new JLabel("Số điện thoại:");
        txtPhone = new JTextField();
        btnCheck = new ButtonCustom();
        btnCheck.setText("Kiểm tra");

        JLabel lblName = new JLabel("Tên khách hàng:");
        txtName = new JTextField();
        txtName.setEditable(false);

        btnSelect = new ButtonCustom();
        btnSelect.setText("Chọn khách hàng");

        gbc.gridx = 0; gbc.gridy = 0;
        add(lblPhone, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        add(txtPhone, gbc);
        gbc.gridx = 2; gbc.gridy = 0;
        add(btnCheck, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(lblName, gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 2;
        add(txtName, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3; gbc.anchor = GridBagConstraints.CENTER;
        add(btnSelect, gbc);

        txtPhone.requestFocus();
    }

    public String getPhoneNumber() {
        return txtPhone.getText().trim();
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.selectedCustomer = customer;
            txtName.setText(customer.getName());
        } else {
            this.selectedCustomer = null;
            txtName.setText("");
        }
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void addCheckListener(ActionListener listener) {
        btnCheck.addActionListener(listener);
    }

    public void addSelectListener(ActionListener listener) {
        btnSelect.addActionListener(listener);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import com.zzz.quanlibangiay.entity.Customer;
import com.zzz.quanlibangiay.utils.ValidationUtils;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerView extends javax.swing.JPanel {


    private PanelBorder leftPanelSection;
    private PanelBorder rightPanelSection;
    private JTextField txtSearch;
    private ButtonCustom btnSearch;
    private ButtonCustom btnAdd, btnEdit, btnDelete, btnClear;

    private JTextField txtId;
    private JTextField txtFullName;
    private JRadioButton rdoMale, rdoFemale;
    private JTextField txtPhoneNumber;
    private JTextArea txtAddress;
    private JTable tableCustomer;

    public CustomerView() {
        initComponents();
        init();
    }

    public void init() {
        leftPanelSection = new PanelBorder();
        rightPanelSection = new PanelBorder();

        this.setLayout(new MigLayout("insets 0", "[fill, grow]", "[pref!]10[fill, grow]"));

        this.add(rightPanelSection, "growx, wrap");

        this.add(leftPanelSection, "grow");

        initTopSection();
        initBottomSection();
    }

    private void initBottomSection() {
        leftPanelSection.setLayout(new BorderLayout(10, 10));
        leftPanelSection.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));

        JPanel searchPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtSearch = new JTextField();
        PromptSupport.setPrompt("Nhập số điện thoại...", txtSearch);
        txtSearch.setPreferredSize(new Dimension(250, 25));
        btnSearch = new ButtonCustom();
        btnSearch.setText("Tìm kiếm");
        searchPane.add(txtSearch);
        searchPane.add(btnSearch);
        leftPanelSection.add(searchPane, BorderLayout.NORTH);

        tableCustomer = new JTable();
        JScrollPane spCustomer = createCustomScrollPane(tableCustomer);
        TableCustom.apply(spCustomer, TableCustom.TableType.DEFAULT);

        leftPanelSection.add(spCustomer, BorderLayout.CENTER);
    }

    private void initTopSection() {
        rightPanelSection.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        rightPanelSection.setLayout(new MigLayout(
                "insets 10, wrap 6",
                "[]10[fill, grow] 20 []10[fill, grow] 20 []10[fill, grow]",
                "[]10[]"
        ));


        txtId = new JTextField();
        txtId.setEditable(false);

        txtFullName = new JTextField();
        rdoMale = new JRadioButton("Nam");
        rdoMale.setSelected(true);
        rdoFemale = new JRadioButton("Nữ");
        ButtonGroup g = new ButtonGroup();
        g.add(rdoMale);
        g.add(rdoFemale);
        txtPhoneNumber = new JTextField();
        txtAddress = new JTextArea();

        btnAdd = new ButtonCustom();
        btnAdd.setText("Thêm");
        btnEdit = new ButtonCustom();
        btnEdit.setText("Sửa");
        btnDelete = new ButtonCustom();
        btnDelete.setText("Xóa");
        btnClear = new ButtonCustom();
        btnClear.setText("Làm Mới");

        rightPanelSection.add(new JLabel("Mã khách hàng:"), "top");
        rightPanelSection.add(txtId, "top");
        rightPanelSection.add(new JLabel("Họ & Tên:"), "top");
        rightPanelSection.add(txtFullName, "top");
        rightPanelSection.add(new JLabel("Địa chỉ:"), "top");
        JScrollPane scrollAddress = new JScrollPane(txtAddress);
        scrollAddress.setVerticalScrollBar(new ScrollbarCustom());
        scrollAddress.setHorizontalScrollBar(new ScrollbarCustom());
        rightPanelSection.add(scrollAddress, "top, h 50!");


        rightPanelSection.add(new JLabel("Số ĐT:"), "top");
        rightPanelSection.add(txtPhoneNumber, "top");

        rightPanelSection.add(new JLabel("Giới tính:"), "top");
        JPanel pnlGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlGender.setOpaque(false);
        pnlGender.add(rdoMale);
        pnlGender.add(rdoFemale);
        rightPanelSection.add(pnlGender, "growx, top");

        rightPanelSection.add(btnAdd, "skip2, split 4, span 6, right, gapx 10");
        rightPanelSection.add(btnEdit, "gapx 10");
        rightPanelSection.add(btnDelete, "gapx 10");
        rightPanelSection.add(btnClear, "gapx 10");
    }

    private JScrollPane createCustomScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBar(new ScrollbarCustom());
        scroll.setHorizontalScrollBar(new ScrollbarCustom());
        return scroll;
    }

    public Customer getDataCustomerFromForm() {
        try {
            Customer customer = new Customer();

            String idText = txtId.getText().trim();
            if (!idText.isEmpty()) {
                try {
                    customer.setId(Integer.parseInt(idText));
                } catch (NumberFormatException e) {
                    showError("Mã khách hàng không hợp lệ!");
                    txtId.requestFocus();
                    return null;
                }
            }

            String name = txtFullName.getText().trim();
            if (name.isEmpty()) {
                showError("Tên khách hàng không được để trống!");
                txtFullName.requestFocus();
                return null;
            }
            customer.setName(name);

            customer.setGender(rdoMale.isSelected());

            String phone = txtPhoneNumber.getText().trim();
            if (phone.isEmpty()) {
                showError("Số điện thoại không được để trống!");
                txtPhoneNumber.requestFocus();
                return null;
            }
            if (!ValidationUtils.isValidPhone(phone)) {
                showError("Số điện thoại không hợp lệ!");
                txtPhoneNumber.requestFocus();
                return null;
            }
            customer.setPhoneNumber(phone);

            String address = txtAddress.getText().trim();
            if (address.isEmpty()) {
                showError("Địa chỉ không được để trống!");
                txtAddress.requestFocus();
                return null;
            }
            customer.setAddress(address);

            return customer;

        } catch (Exception ex) {
            showError("Lỗi khi lấy dữ liệu khách hàng: " + ex.getMessage());
            return null;
        }
    }

    public void setCustomerFormData(Customer c) {
        if (c == null) return;

        txtId.setText(String.valueOf(c.getId()));
        txtFullName.setText(c.getName());
        rdoMale.setSelected(c.isGender());
        rdoFemale.setSelected(!c.isGender());
        txtPhoneNumber.setText(c.getPhoneNumber());
        txtAddress.setText(c.getAddress());
    }

    public void clearCustomerForm() {
        txtId.setText("");
        txtFullName.setText("");
        rdoMale.setSelected(true);
        txtPhoneNumber.setText("");
        txtAddress.setText("");
        tableCustomer.clearSelection();
    }

    public void setCustomerTableData(Object[][] data) {
        String[] columnNames = new String[]{
                "Id", "Tên khách hàng", "Giới tính", "Số điện thoại", "Địa chỉ", "Ngày tham gia"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCustomer.setModel(model);
    }

    public String getPhoneNumber() {
        return txtSearch.getText().trim();
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void addTableSelectionListener(ListSelectionListener listener) {
        tableCustomer.getSelectionModel().addListSelectionListener(listener);
    }

    public void addSearchCustomerListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    public void addAddCustomerListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addEditCustomerListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addDeleteCustomerListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addClearCustomerListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }

    public JTable getTableCustomer() {
        return tableCustomer;
    }
}
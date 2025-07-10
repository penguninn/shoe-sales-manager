/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;


public class OrderView extends javax.swing.JPanel {

    private PanelBorder panelBorder;
    private JTable orderTable;
    private JTable productTable;

    private JTextField txtCustomerId;
    private JTextField txtCustomerName;
    private JTextField txtPhone;
    private JTextField txtGender;
    private JTextField txtAddress;
    private JTextField txtTotal;

    public OrderView() {
        initComponents();
        init();
    }

    public void init() {
        panelBorder = new PanelBorder();
        panelBorder.setLayout(new MigLayout("insets 10, wrap 1", "[grow, fill]", "[]10[fill, grow]10[fill, grow]"));
        this.setLayout(new BorderLayout());
        this.add(panelBorder, BorderLayout.CENTER);

        initTopSection();
        initBottomSection();
    }

    private void initTopSection() {
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBorder(BorderFactory.createTitledBorder("Hóa đơn tại quầy"));
        topPanel.setBackground(Color.WHITE);

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(searchPanel, BorderLayout.NORTH);


        orderTable = new JTable();
        JScrollPane scrollPane = createCustomScrollPane(orderTable);
        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);
        topPanel.add(scrollPane, BorderLayout.CENTER);

        panelBorder.add(topPanel, "growx");
    }

    private void initBottomSection() {
        JPanel bottomPanel = new JPanel(new MigLayout("insets 0, gap 10", "[70%, grow]10[30%, grow]", "[fill]"));
        bottomPanel.setBackground(Color.WHITE);

        JPanel productPanel = new JPanel(new BorderLayout());
        productPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sản phẩm của hóa đơn"));
        productPanel.setBackground(Color.WHITE);

        productTable = new JTable();
        JScrollPane productScroll = createCustomScrollPane(productTable);
        TableCustom.apply(productScroll, TableCustom.TableType.DEFAULT);
        productPanel.add(productScroll, BorderLayout.CENTER);

        JPanel customerPanel = new JPanel(new MigLayout("wrap 2", "[right]10[fill, grow]", "[]10[]10[]10[]10[]10[]"));
        customerPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        customerPanel.setBackground(Color.WHITE);

        txtCustomerId = new JTextField();
        txtCustomerName = new JTextField();
        txtPhone = new JTextField();
        txtGender = new JTextField();
        txtAddress = new JTextField();
        txtTotal = new JTextField();

        customerPanel.add(new JLabel("Mã khách hàng :"));
        customerPanel.add(txtCustomerId);
        txtCustomerId.setEditable(false);
        customerPanel.add(new JLabel("Tên khách hàng :"));
        customerPanel.add(txtCustomerName);
        txtCustomerName.setEditable(false);
        customerPanel.add(new JLabel("Số điện thoại :"));
        customerPanel.add(txtPhone);
        txtPhone.setEditable(false);
        customerPanel.add(new JLabel("Giới tính :"));
        customerPanel.add(txtGender);
        txtGender.setEditable(false);
        customerPanel.add(new JLabel("Địa Chỉ :"));
        customerPanel.add(txtAddress);
        txtAddress.setEditable(false);

        bottomPanel.add(productPanel, "grow");
        bottomPanel.add(customerPanel, "grow");

        panelBorder.add(bottomPanel, "grow");
    }

    private JScrollPane createCustomScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBar(new ScrollbarCustom());
        scroll.setHorizontalScrollBar(new ScrollbarCustom());
        return scroll;
    }

    public void setInvoiceTableData(Object[][] data) {
        String[] columns = {"Id", "Khách Hàng", "Nhân viên", "Tổng Giá Trị", "Phương thức thanh toán", "Trạng thái", "Ngày tạo"};
        orderTable.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void setProductTableData(Object[][] data) {
        String[] productColumns = {"Id", "Tên sản phẩm", "Loại sản phẩm", "Thương hiệu", "Size", "Màu sắc", "Chất liệu", "Số lượng", "Giá bán", "Tổng Tiền"};
        productTable.setModel(new DefaultTableModel(data, productColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void setCustomerInfo(String id, String name, String phone, String gender, String address) {
        txtCustomerId.setText(id);
        txtCustomerName.setText(name);
        txtPhone.setText(phone);
        txtGender.setText(gender);
        txtAddress.setText(address);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    // Table Management Listeners
    public void addInvoiceTableSelectionListener(ListSelectionListener listener) {
        orderTable.getSelectionModel().addListSelectionListener(listener);
    }

    // Table Management Methods
    public JTable getOrderTable() {
        return orderTable;
    }
}
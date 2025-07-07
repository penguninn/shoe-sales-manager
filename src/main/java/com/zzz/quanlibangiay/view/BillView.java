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
import java.awt.*;
import java.awt.event.ActionListener;


public class BillView extends javax.swing.JPanel {

    private PanelBorder panelBorder;
    private JTable invoiceTable;
    private JTable productTable;
    private TextField txtSearch;
    private ButtonCustom btnRefresh;

    private TextField txtCustomerId;
    private TextField txtCustomerName;
    private TextField txtPhone;
    private TextField txtGender;
    private TextField txtAddress;
    private TextField txtTotal;

    public BillView() {
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
        txtSearch = new TextField();
        txtSearch.setPreferredSize(new Dimension(300, 30));

        btnRefresh = new ButtonCustom();
        btnRefresh.setText("Làm Mới");

        searchPanel.add(txtSearch);
        searchPanel.add(btnRefresh);

        topPanel.add(searchPanel, BorderLayout.NORTH);

        String[] columns = {"STT", "Mã Hóa Đơn", "Tên Khách Hàng", "Số Điện thoại", "Tổng Giá Trị", "Trạng Thái", "Ngày Tạo", "Nhân Viên"};
        Object[][] data = {
            {1, "HD004", "Trần Minh Đức", "0945678901", 1100000.0, "Đã thanh toán", "2024-07-04 00:00:00", "Phạm Thị Dung"},
            {2, "HD005", "Nguyễn Thị Em", "0956789012", 1800000.0, "Đã thanh toán", "2024-07-05 00:00:00", "Hoàng Văn Em"},
            {3, "HD007", "Đặng Thị Giang", "0978901234", 2250000.0, "Đã thanh toán", "2024-07-07 00:00:00", "Đặng Văn Quang"},
            {4, "HD009", "Mai Thị Lan", "0990123456", 2700000.0, "Đã thanh toán", "2024-07-09 00:00:00", "Trương Văn Khoa"}
        };
        invoiceTable = new JTable(data, columns);
        JScrollPane scrollPane = createCustomScrollPane(invoiceTable);
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

        String[] productColumns = {"STT", "Mã SPCT", "Tên SP", "Thương hiệu", "Size", "Màu sắc", "Số lượng", "Giá bán", "Tổng Tiền"};
        Object[][] productData = {};

        productTable = new JTable(productData, productColumns);
        JScrollPane productScroll = createCustomScrollPane(productTable);
        TableCustom.apply(productScroll, TableCustom.TableType.DEFAULT);
        productPanel.add(productScroll, BorderLayout.CENTER);

        JPanel customerPanel = new JPanel(new MigLayout("wrap 2", "[right]10[fill, grow]"));
        customerPanel.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        customerPanel.setBackground(Color.WHITE);

        txtCustomerId = new TextField();
        txtCustomerName = new TextField();
        txtPhone = new TextField();
        txtGender = new TextField();
        txtAddress = new TextField();
        txtTotal = new TextField();

        customerPanel.add(new JLabel("Mã khách hàng :"));
        customerPanel.add(txtCustomerId);
        customerPanel.add(new JLabel("Tên khách hàng :"));
        customerPanel.add(txtCustomerName);
        customerPanel.add(new JLabel("Số điện thoại :"));
        customerPanel.add(txtPhone);
        customerPanel.add(new JLabel("Giới tính :"));
        customerPanel.add(txtGender);
        customerPanel.add(new JLabel("Địa Chỉ :"));
        customerPanel.add(txtAddress);
        customerPanel.add(new JLabel("Tổng HĐ :"));
        customerPanel.add(txtTotal);

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


    // Bill Management Listeners
    public void addRefreshBillListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

}
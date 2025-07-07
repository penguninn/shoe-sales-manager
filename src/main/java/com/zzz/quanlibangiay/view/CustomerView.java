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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerView extends javax.swing.JPanel {


    private PanelBorder leftPanelSection;
    private PanelBorder rightPanelSection;
    private TextField txtSearch;
    private ButtonCustom btnSearch;
    private ButtonCustom btnAdd, btnEdit, btnDelete, btnClear;

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

        initRightSection();
        initLeftSection();
    }

    private void initLeftSection() {
        leftPanelSection.setLayout(new BorderLayout(10, 10));
        leftPanelSection.setBorder(BorderFactory.createTitledBorder("Danh sách khách hàng"));

        JPanel searchPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtSearch = new TextField();
        txtSearch.setPreferredSize(new Dimension(250, 25));
        btnSearch = new ButtonCustom();
        btnSearch.setText("Tìm kiếm");
        searchPane.add(txtSearch);
        searchPane.add(btnSearch);
        leftPanelSection.add(searchPane, BorderLayout.NORTH);

        String[] cols = {"ID", "Tài khoản", "Họ tên", "Vai trò"};
        DefaultTableModel mdlCustomer = new DefaultTableModel(cols, 0);
        JTable tableCustomer = new JTable(mdlCustomer);
        JScrollPane spCustomer = createCustomScrollPane(tableCustomer);
        TableCustom.apply(spCustomer, TableCustom.TableType.DEFAULT);

        leftPanelSection.add(spCustomer, BorderLayout.CENTER);
    }

    private void initRightSection() {
        rightPanelSection.setBorder(BorderFactory.createTitledBorder("Thông tin khách hàng"));
        rightPanelSection.setLayout(new MigLayout(
                "insets 10, wrap 6",
                "[]10[fill, grow] 20 []10[fill, grow] 20 []10[fill, grow]",
                "[]10[]"
        ));

        TextField txtFullName = new TextField();
        JRadioButton rdoMale = new JRadioButton("Nam");
        rdoMale.setSelected(true);
        JRadioButton rdoFemale = new JRadioButton("Nữ");
        ButtonGroup g = new ButtonGroup();
        g.add(rdoMale);
        g.add(rdoFemale);
        TextField txtPhoneNumber = new TextField();
        TextField txtJoinDate = new TextField();
        JTextArea txtAddress = new JTextArea();

        btnAdd = new ButtonCustom();
        btnAdd.setText("Thêm");
        btnEdit = new ButtonCustom();
        btnEdit.setText("Sửa");
        btnDelete = new ButtonCustom();
        btnDelete.setText("Xóa");
        btnClear = new ButtonCustom();
        btnClear.setText("Làm Mới");

        rightPanelSection.add(new JLabel("Họ & Tên:"), "top");
        rightPanelSection.add(txtFullName, "top");
        rightPanelSection.add(new JLabel("Giới tính:"), "top");
        JPanel pnlGender = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        pnlGender.setOpaque(false);
        pnlGender.add(rdoMale);
        pnlGender.add(rdoFemale);
        rightPanelSection.add(pnlGender, "growx, top");

        rightPanelSection.add(new JLabel("Địa chỉ:"), "top");
        JScrollPane scrollAddress = new JScrollPane(txtAddress);
        scrollAddress.setVerticalScrollBar(new ScrollbarCustom());
        scrollAddress.setHorizontalScrollBar(new ScrollbarCustom());
        rightPanelSection.add(scrollAddress, "top, h 50!");

        rightPanelSection.add(new JLabel("Số ĐT:"), "top");
        rightPanelSection.add(txtPhoneNumber, "top");

        rightPanelSection.add(new JLabel("Ngày tham gia:"), "top");
        rightPanelSection.add(txtJoinDate, "top");

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

    // Search Listeners
    public void addSearchCustomerListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    // Customer Management Listeners
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
}
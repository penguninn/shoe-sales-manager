/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.menu.tabbedpane_custom.MaterialTabbed;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author coole
 */
public class Dashboard extends javax.swing.JPanel {

    private JPanel panel;
    private JLayeredPane layerPane;
    private PanelBorder panelHeader;
    private JPanel panelTable;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel revenue;
    private JPanel product;
    private JPanel staff;
    private JPanel customer;
    private JTable revenueTable;
    private JTable productTable;
    private JTable staffTable;
    private JTable customeTable;
    private JLabel labelHeader;
    private PanelBorder panelBorder;
    private MaterialTabbed tabbedPane;

    public Dashboard() {
        initComponents();
        init();
        setTableData();
    }

    public void init() {
        panel = new JPanel();
        layerPane = new JLayeredPane();
        panelBorder = new PanelBorder();
        panelHeader = new PanelBorder();
        panelTable = new JPanel();
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        tabbedPane = new MaterialTabbed();
        revenue = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        product = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        staff = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        customer = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));

        Color bg = Color.white;
        revenue.setBackground(bg);
        product.setBackground(bg);
        staff.setBackground(bg);
        customer.setBackground(bg);

        revenueTable = new JTable();
        productTable = new JTable();
        staffTable = new JTable();
        customeTable = new JTable();

        JScrollPane scrollPaneRevenue = createCustomScrollPane(revenueTable);
        JScrollPane scrollPaneProduct = createCustomScrollPane(productTable);
        JScrollPane scrollPaneStaff = createCustomScrollPane(staffTable);
        JScrollPane scrollPaneCustomer = createCustomScrollPane(customeTable);
        
        TableCustom.apply(scrollPaneRevenue, TableCustom.TableType.DEFAULT);
        TableCustom.apply(scrollPaneProduct, TableCustom.TableType.DEFAULT);
        TableCustom.apply(scrollPaneStaff, TableCustom.TableType.DEFAULT);
        TableCustom.apply(scrollPaneCustomer, TableCustom.TableType.DEFAULT);

        revenue.add(scrollPaneRevenue, "grow");
        tabbedPane.addTab("Doanh Thu", revenue);

        product.add(scrollPaneProduct, "grow");
        tabbedPane.addTab("Sản Phẩm", product);

        staff.add(scrollPaneStaff, "grow");
        tabbedPane.addTab("Nhân Viên", staff);

        customer.add(scrollPaneCustomer, "grow");
        tabbedPane.addTab("Khách Hàng", customer);

        panelTable.setLayout(new MigLayout("fill"));
        panelTable.add(tabbedPane, "grow");

        labelHeader = new JLabel("Tổng Hợp Thống Kê");
        labelHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
        labelHeader.setForeground(Color.BLACK);

        panel.setBackground(new Color(234, 234, 234));
        panelTable.setBackground(bg);
        cardPanel.setBackground(bg);
        panelBorder.setBackground(bg);
        panelHeader.setBackground(bg);

        cardPanel.setLayout(cardLayout);
        cardPanel.add(panelTable, "Table");

        panelHeader.setLayout(new MigLayout("fill", "20[]80[]50[grow]", "10[]"));
        panelHeader.add(labelHeader, "align left");

        panelBorder.setLayout(new MigLayout("fill", "0[fill]0", "0[]8[grow]5"));
        panelBorder.add(panelHeader, "h 40!, wrap");
        panelBorder.add(cardPanel, "grow");

        PanelBorder statCard1 = new PanelBorder(Color.decode("#373B44"), Color.decode("#4286f4"));
        statCard1.setLayout(new GridLayout(3, 1, 0, 2));
        statCard1.add(createStatRow("Tổng Loại Giày", "12"));
        statCard1.add(createStatRow("Tổng Số Giày", "345"));
        statCard1.add(createStatRow("Tổng Đã Bán", "278"));

        PanelBorder statCard2 = new PanelBorder(Color.decode("#373B44"), Color.decode("#4286f4"));
        statCard2.setLayout(new GridLayout(3, 1, 0, 2));
        statCard2.add(createStatRow("Doanh Thu Tháng", "123,000,000đ"));
        statCard2.add(createStatRow("Chi Phí", "45,000,000đ"));
        statCard2.add(createStatRow("Lợi Nhuận", "78,000,000đ"));

        layerPane.setLayout(new GridLayout(1, 2, 10, 0));
        layerPane.add(statCard1);
        layerPane.add(statCard2);

        panel.setLayout(new MigLayout("fill", "5[fill]5", "5[]8[grow]5"));
        panel.add(layerPane, "h 120!, wrap");
        panel.add(panelBorder, "grow");

        this.setLayout(new MigLayout("fill", "0[fill]0", "0[grow]0"));
        this.add(panel, "grow");
    }

    private JScrollPane createCustomScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBar(new ScrollbarCustom());
        scroll.setHorizontalScrollBar(new ScrollbarCustom());
        return scroll;
    }

    private JPanel createStatRow(String labelText, String valueText) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setForeground(Color.WHITE);

        JLabel value = new JLabel(valueText, SwingConstants.RIGHT);
        value.setFont(new Font("SansSerif", Font.BOLD, 15));
        value.setForeground(Color.WHITE);

        row.add(label, BorderLayout.WEST);
        row.add(value, BorderLayout.EAST);

        return row;
    }

    private void setTableData() {
        // Dữ liệu mẫu cho bảng Doanh thu
        revenueTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Tháng 1", "50,000,000đ"},
                    {"Tháng 2", "60,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 1", "50,000,000đ"},
                    {"Tháng 2", "60,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 1", "50,000,000đ"},
                    {"Tháng 2", "60,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 1", "50,000,000đ"},
                    {"Tháng 2", "60,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                    {"Tháng 3", "70,000,000đ"},
                },
                new String[]{"Tháng", "Doanh Thu"}
        ));

        // Dữ liệu mẫu cho bảng Sản phẩm
        productTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Nike Air", "Giày Thể Thao", "2,500,000đ"},
                    {"Adidas Boost", "Giày Chạy", "2,200,000đ"},
                    {"Puma X", "Giày Casual", "1,800,000đ"}
                },
                new String[]{"Tên Sản Phẩm", "Loại", "Giá"}
        ));

        // Dữ liệu mẫu cho bảng Nhân viên
        staffTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Nguyễn Văn A", "Quản lý", "12,000,000đ"},
                    {"Trần Thị B", "Bán hàng", "9,000,000đ"},
                    {"Lê Văn C", "Kho", "8,500,000đ"}
                },
                new String[]{"Tên", "Chức vụ", "Lương"}
        ));

        // Dữ liệu mẫu cho bảng Khách hàng
        customeTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"Phạm Hồng D", "0123456789", "Hà Nội"},
                    {"Võ Minh E", "0987654321", "Đà Nẵng"},
                    {"Trịnh Xuân F", "0909090909", "TP.HCM"}
                },
                new String[]{"Tên KH", "SĐT", "Địa chỉ"}
        ));
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
}

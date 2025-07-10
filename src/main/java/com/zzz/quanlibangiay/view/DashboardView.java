/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.tabbedpane_custom.MaterialTabbed;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class DashboardView extends javax.swing.JPanel {

    private JPanel panel;
    private JLayeredPane layerPane;
    private PanelBorder panelHeader;
    private JPanel panelTable;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel product;
    private JPanel staff;
    private JPanel customer;
    private JTable productTable;
    private JTable staffTable;
    private JTable customeTable;
    private JLabel labelHeader;
    private PanelBorder panelBorder;
    private MaterialTabbed tabbedPane;

    private JLabel totalTypesLabel;
    private JLabel totalShoesLabel;
    private JLabel totalSoldLabel;
    private JLabel totalRevenueLabel;
    private JLabel totalCostLabel;
    private JLabel totalProfitLabel;

    public DashboardView() {
        initComponents();
        init();
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
        product = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        staff = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));
        customer = new JPanel(new MigLayout("fill", "[grow]", "[grow]"));

        Color bg = Color.white;
        product.setBackground(bg);
        staff.setBackground(bg);
        customer.setBackground(bg);

        productTable = new JTable();
        staffTable = new JTable();
        customeTable = new JTable();

        JScrollPane scrollPaneProduct = createCustomScrollPane(productTable);
        JScrollPane scrollPaneStaff = createCustomScrollPane(staffTable);
        JScrollPane scrollPaneCustomer = createCustomScrollPane(customeTable);

        TableCustom.apply(scrollPaneProduct, TableCustom.TableType.DEFAULT);
        TableCustom.apply(scrollPaneStaff, TableCustom.TableType.DEFAULT);
        TableCustom.apply(scrollPaneCustomer, TableCustom.TableType.DEFAULT);

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

        totalTypesLabel = new JLabel("0");
        totalShoesLabel = new JLabel("0");
        totalSoldLabel = new JLabel("0");
        totalRevenueLabel = new JLabel("0đ");
        totalCostLabel = new JLabel("0đ");
        totalProfitLabel = new JLabel("0đ");

        PanelBorder statCard1 = new PanelBorder(Color.decode("#373B44"), Color.decode("#4286f4"));
        statCard1.setLayout(new GridLayout(3, 1, 0, 2));
        statCard1.add(createStatRow("Tổng Loại Giày", totalTypesLabel));
        statCard1.add(createStatRow("Tổng Số Giày", totalShoesLabel));
        statCard1.add(createStatRow("Tổng Đã Bán", totalSoldLabel));

        PanelBorder statCard2 = new PanelBorder(Color.decode("#373B44"), Color.decode("#4286f4"));
        statCard2.setLayout(new GridLayout(3, 1, 0, 2));
        statCard2.add(createStatRow("Tổng Doanh Thu", totalRevenueLabel));
        statCard2.add(createStatRow("Tổng Chi Phí", totalCostLabel));
        statCard2.add(createStatRow("Tổng Lợi Nhuận", totalProfitLabel));

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

    private JPanel createStatRow(String labelText, JLabel valueLabel) {
        JPanel row = new JPanel(new BorderLayout());
        row.setOpaque(false);
        row.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("SansSerif", Font.BOLD, 15));
        label.setForeground(Color.WHITE);

        valueLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
        valueLabel.setForeground(Color.WHITE);
        valueLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        row.add(label, BorderLayout.WEST);
        row.add(valueLabel, BorderLayout.EAST);

        return row;
    }

    public void updateStatCards(String totalTypes, String totalShoes, String totalSold,
                               String totalRevenue, String totalCost, String totalProfit) {
        SwingUtilities.invokeLater(() -> {
            totalTypesLabel.setText(totalTypes);
            totalShoesLabel.setText(totalShoes);
            totalSoldLabel.setText(totalSold);
            totalRevenueLabel.setText(totalRevenue);
            totalCostLabel.setText(totalCost);
            totalProfitLabel.setText(totalProfit);
        });
    }

    public void updateTotalTypes(String value) {
        SwingUtilities.invokeLater(() -> totalTypesLabel.setText(value));
    }

    public void updateTotalShoes(String value) {
        SwingUtilities.invokeLater(() -> totalShoesLabel.setText(value));
    }

    public void updateTotalSold(String value) {
        SwingUtilities.invokeLater(() -> totalSoldLabel.setText(value));
    }

    public void updateTotalRevenue(String value) {
        SwingUtilities.invokeLater(() -> totalRevenueLabel.setText(value));
    }

    public void updateTotalCost(String value) {
        SwingUtilities.invokeLater(() -> totalCostLabel.setText(value));
    }

    public void updateTotalProfit(String value) {
        SwingUtilities.invokeLater(() -> totalProfitLabel.setText(value));
    }

    public void updateStatCardsWithNumbers(int totalTypes, int totalShoes, int totalSold,
                                          double totalRevenue, double totalCost, double totalProfit) {
        SwingUtilities.invokeLater(() -> {
            totalTypesLabel.setText(String.valueOf(totalTypes));
            totalShoesLabel.setText(String.valueOf(totalShoes));
            totalSoldLabel.setText(String.valueOf(totalSold));
            totalRevenueLabel.setText(formatCurrency(totalRevenue));
            totalCostLabel.setText(formatCurrency(totalCost));
            totalProfitLabel.setText(formatCurrency(totalProfit));
        });
    }

    public void resetStatCards() {
        SwingUtilities.invokeLater(() -> {
            totalTypesLabel.setText("0");
            totalShoesLabel.setText("0");
            totalSoldLabel.setText("0");
            totalRevenueLabel.setText("0đ");
            totalCostLabel.setText("0đ");
            totalProfitLabel.setText("0đ");
        });
    }

    private String formatCurrency(double amount) {
        return String.format("%,.0fđ", amount);
    }

    public String getCurrentTotalTypes() {
        return totalTypesLabel.getText();
    }

    public String getCurrentTotalShoes() {
        return totalShoesLabel.getText();
    }

    public String getCurrentTotalSold() {
        return totalSoldLabel.getText();
    }

    public String getCurrentTotalRevenue() {
        return totalRevenueLabel.getText();
    }

    public String getCurrentTotalCost() {
        return totalCostLabel.getText();
    }

    public String getCurrentTotalProfit() {
        return totalProfitLabel.getText();
    }

    public void setProductTableData(Object[][] data) {
        String[] columns = {"Tên Sản Phẩm", "Loại", "Thương hiệu", "Tồn kho", "Đã bán", "Giá bán", "Giá nhập", "Lợi nhuận"};
        productTable.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void setStaffTableData(Object[][] data) {
        String[] columns = {"Tên", "Chức vụ", "Tổng đơn hàng xử lý", "Tổng doanh thu đóng góp", "Trạng thái"};
        staffTable.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    public void setCustomerTableData(Object[][] data) {
        String[] columns = {"Tên KH", "SĐT", "Địa chỉ", "Tổng đơn hàng", "Tổng chi tiêu"};
        customeTable.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
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
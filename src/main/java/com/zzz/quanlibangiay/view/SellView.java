package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.combobox_custom.ComboBoxSuggestion;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class SellView extends javax.swing.JPanel {

    private PanelBorder panelPendingBill;
    private PanelBorder panelCart;
    private PanelBorder panelProduct;
    private PanelBorder panelBillInfo;
    private PanelBorder panelBottom;

    private ButtonCustom btnCheckPhone;
    private ButtonCustom btnCreateBill;
    private ButtonCustom btnPayment;
    private ButtonCustom btnCancel;
    private ButtonCustom btnRefresh;

    public SellView() {
        initComponents();
        init();
    }

    public void init() {
        panelPendingBill = new PanelBorder();
        panelCart = new PanelBorder();
        panelProduct = new PanelBorder();
        panelBillInfo = new PanelBorder();
        panelBottom = new PanelBorder();

        initPendingBillSection();
        initCartSection();
        initProductSection();
        initBillInfoSection();
        initBottomSection();

        this.setLayout(new MigLayout(
                "insets 0, wrap 1",
                "[grow]",
                "[70%, grow][30%, grow]"
        ));

        JPanel panelTop = new JPanel(new MigLayout(
                "insets 0",
                "[70%,grow][30%,grow]",
                "[grow]"
        ));

        JPanel panelLeft = new JPanel(new MigLayout(
                "insets 0, wrap 1",
                "[fill, grow]",
                "[grow][grow]"
        ));
        JPanel panelRight = new JPanel(new MigLayout(
                "insets 0",
                "[fill, grow]",
                "[grow]"
        ));

        panelLeft.add(panelPendingBill, "grow, height 100%");
        panelLeft.add(panelCart, "grow, height 100%");
        panelRight.add(panelBillInfo, "grow, height 100%");

        panelTop.add(panelLeft, "grow, height 100%");
        panelTop.add(panelRight, "grow, height 100%");

        this.add(panelTop, "grow");
        this.add(panelBottom, "grow");
    }

    public void initBottomSection() {
        panelBottom.setLayout(new MigLayout(
                "insets 0",
                "[70%,grow][30%,grow]",
                "[grow]"
        ));
        panelBottom.add(panelProduct, "grow, height 100%");

        JPanel panelBottomRight = new JPanel(new MigLayout("insets 10, fill", "[grow]", "[grow]"));
        panelBottomRight.setBorder(BorderFactory.createTitledBorder("Hình ảnh sản phẩm"));
        panelBottomRight.setBackground(Color.WHITE);

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.LIGHT_GRAY);
        imagePanel.setPreferredSize(new Dimension(200, 240));
        imagePanel.setMinimumSize(new Dimension(150, 180));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        imagePanel.setOpaque(true);

        JLabel lblImage = new JLabel("Chọn ảnh", SwingConstants.CENTER);
        lblImage.setFont(new Font("Arial", Font.BOLD, 14));
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(lblImage, BorderLayout.CENTER);

        panelBottomRight.add(imagePanel, "grow");
        panelBottom.add(panelBottomRight, "grow, height 100%");
    }

    public void initPendingBillSection() {
        panelPendingBill.setLayout(new BorderLayout(10, 10));
        panelPendingBill.setBorder(BorderFactory.createTitledBorder("Hóa đơn chờ"));

        String[] columns = {"ID", "Thời gian", "Khách hàng", "Tổng tiền", "Trạng thái"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable tablePendingBills = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tablePendingBills);
        scrollPane.setVerticalScrollBar(new ScrollbarCustom());
        scrollPane.setHorizontalScrollBar(new ScrollbarCustom());
        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        panelPendingBill.add(scrollPane, BorderLayout.CENTER);
    }

    public void initCartSection() {
        panelCart.setLayout(new BorderLayout(10, 10));
        panelCart.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));

        String[] columns = {"Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable tableCart = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableCart);
        scrollPane.setVerticalScrollBar(new ScrollbarCustom());
        scrollPane.setHorizontalScrollBar(new ScrollbarCustom());

        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        panelCart.add(scrollPane, BorderLayout.CENTER);
    }

    public void initProductSection() {
        panelProduct.setLayout(new BorderLayout(10, 10));
        panelProduct.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));

        String[] columns = {"Tên", "Thương hiệu", "Loại", "Màu", "Size", "Số lượng", "Giá bán"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable tableProduct = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(tableProduct);
        scrollPane.setVerticalScrollBar(new ScrollbarCustom());
        scrollPane.setHorizontalScrollBar(new ScrollbarCustom());

        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        panelProduct.add(scrollPane, BorderLayout.CENTER);
    }

    public void initBillInfoSection() {
        panelBillInfo.setLayout(new MigLayout("insets 10, wrap 1", "[fill, grow]", "[][grow][]"));
        panelBillInfo.setBorder(BorderFactory.createTitledBorder("Thông tin hóa đơn"));

        JPanel customerPanel = new JPanel(new MigLayout("insets 10, wrap 2", "[right]10[fill, grow]", "[]10[]"));
        customerPanel.setBorder(BorderFactory.createTitledBorder("Khách hàng"));
        customerPanel.setOpaque(false);

        ComboBoxSuggestion<String> cbCustomerType = new ComboBoxSuggestion<>();
        cbCustomerType.setModel(new DefaultComboBoxModel<>(new String[]{"Khách lẻ", "Khách thành viên"}));
        customerPanel.add(new JLabel("Loại khách hàng:"));
        customerPanel.add(cbCustomerType);

        TextField txtPhoneNumber = new TextField();
        txtPhoneNumber.setPreferredSize(new Dimension(150, 25));
        btnCheckPhone = new ButtonCustom();
        btnCheckPhone.setText("Kiểm tra");

        customerPanel.add(new JLabel("Số điện thoại:"));
        JPanel phonePanel = new JPanel(new MigLayout("insets 0", "[fill, grow]5[]", "[]"));
        phonePanel.add(txtPhoneNumber, "growx");
        phonePanel.add(btnCheckPhone);
        customerPanel.add(phonePanel);

        JPanel billPanel = new JPanel(new MigLayout("insets 10, wrap 2", "[right]10[fill, grow]", "[]10[]10[grow]"));
        billPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        billPanel.setOpaque(false);

        TextField txtBillCode = new TextField();
        txtBillCode.setEditable(false);
        TextField txtTotalAmount = new TextField();
        txtTotalAmount.setEditable(false);
        JTextArea txtNote = new JTextArea();
        JScrollPane scrollNote = new JScrollPane(txtNote);
        scrollNote.setVerticalScrollBar(new ScrollbarCustom());
        scrollNote.setHorizontalScrollBar(new ScrollbarCustom());

        billPanel.add(new JLabel("Mã hóa đơn:"));
        billPanel.add(txtBillCode);
        billPanel.add(new JLabel("Tổng tiền:"));
        billPanel.add(txtTotalAmount);
        billPanel.add(new JLabel("Ghi chú:"));
        billPanel.add(scrollNote, "h 80!");

        JPanel buttonPanel = new JPanel(new MigLayout("insets 10, wrap 1", "[fill, grow]", "[]5[]"));
        buttonPanel.setOpaque(false);

        btnCreateBill = new ButtonCustom();
        btnCreateBill.setText("Tạo hóa đơn");
        buttonPanel.add(btnCreateBill, "growx");

        JPanel actionButtonPanel = new JPanel(new MigLayout("insets 0", "[fill, grow][fill, grow][fill, grow]", "[]"));
        actionButtonPanel.setOpaque(false);

        btnPayment = new ButtonCustom();
        btnPayment.setText("Thanh toán");
        btnCancel = new ButtonCustom();
        btnCancel.setText("Hủy");
        btnRefresh = new ButtonCustom();
        btnRefresh.setText("Làm mới");

        actionButtonPanel.add(btnPayment, "growx");
        actionButtonPanel.add(btnCancel, "growx");
        actionButtonPanel.add(btnRefresh, "growx");

        buttonPanel.add(actionButtonPanel, "growx");

        panelBillInfo.add(customerPanel, "growx");
        panelBillInfo.add(billPanel, "grow");
        panelBillInfo.add(buttonPanel, "growx");
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


    // Customer check listeners
    public void addCheckPhoneListener(ActionListener listener) {
        btnCheckPhone.addActionListener(listener);
    }

    // Bill management listeners
    public void addCreateBillListener(ActionListener listener) {
        btnCreateBill.addActionListener(listener);
    }

    public void addPaymentListener(ActionListener listener) {
        btnPayment.addActionListener(listener);
    }

    public void addCancelBillListener(ActionListener listener) {
        btnCancel.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }
}
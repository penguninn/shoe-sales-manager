package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.combobox_custom.ComboBoxSuggestion;
import com.zzz.quanlibangiay.component.panel_custom.ImagePanel;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import com.zzz.quanlibangiay.entity.Customer;
import com.zzz.quanlibangiay.entity.Order;
import com.zzz.quanlibangiay.enums.PaymentMethod;
import com.zzz.quanlibangiay.utils.CurrencyUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;

public class SellView extends javax.swing.JPanel {

    private PanelBorder panelPendingBill;
    private PanelBorder panelCart;
    private PanelBorder panelProduct;
    private PanelBorder panelBillInfo;
    private PanelBorder panelBottom;

    private ButtonCustom btnUpdateCustomer;
    private ButtonCustom btnCreateBill;
    private ButtonCustom btnPayment;
    private ButtonCustom btnCancel;
    private ButtonCustom btnRefresh;

    private JTable tablePendingBills;
    private JTable tableCart;
    private JTable tableProduct;

    private JTextField txtTotalAmount;
    private JTextField txtBillCode;
    private JTextArea txtNote;
    private JTextField txtPhoneNumber;
    private JTextField txtCustomerName;
    private ComboBoxSuggestion<String> cbPaymentMethod;
    private ImagePanel imagePanel;

    private ButtonCustom btnEditCart;
    private ButtonCustom btnDeleteCart;
    private ButtonCustom btnDeleteAllCart;

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

        // Cập nhật layout chính với tỷ lệ 60% cho phần trên, 40% cho phần dưới
        this.setLayout(new MigLayout(
                "insets 0, wrap 1",
                "[grow]",
                "[60%, grow][40%, grow]"
        ));

        JPanel panelTop = new JPanel(new MigLayout(
                "insets 0",
                "[70%,grow][30%,grow]",
                "[grow]"
        ));

        // Cập nhật layout trái với tỷ lệ 30% cho panelPendingBill và 30% cho panelCart
        JPanel panelLeft = new JPanel(new MigLayout(
                "insets 0, wrap 1",
                "[fill, grow]",
                "[30%, grow][30%, grow]"
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
        this.add(panelBottom, "grow"); // panelBottom sẽ chiếm 40% chiều cao
    }

    public void initBottomSection() {
        panelBottom.setLayout(new MigLayout(
                "insets 0",
                "[80%,grow][20%,grow]",
                "[grow]"
        ));
        panelBottom.add(panelProduct, "grow, height 100%");

        JPanel panelBottomRight = new JPanel(new MigLayout("insets 10, fill", "[grow]", "[grow]"));
        panelBottomRight.setBorder(BorderFactory.createTitledBorder("Hình ảnh sản phẩm"));
        panelBottomRight.setBackground(Color.WHITE);

        imagePanel = new ImagePanel(300, 240);
        imagePanel.setBackground(Color.LIGHT_GRAY);
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        panelBottomRight.add(imagePanel, "grow");
        panelBottom.add(panelBottomRight, "grow, height 100%");
    }

    public void initPendingBillSection() {
        panelPendingBill.setLayout(new BorderLayout(10, 10));
        panelPendingBill.setBorder(BorderFactory.createTitledBorder("Hóa đơn chờ"));

        tablePendingBills = new JTable();

        JScrollPane scrollPane = new JScrollPane(tablePendingBills);
        scrollPane.setVerticalScrollBar(new ScrollbarCustom());
        scrollPane.setHorizontalScrollBar(new ScrollbarCustom());
        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        panelPendingBill.add(scrollPane, BorderLayout.CENTER);
    }

    public void initCartSection() {
        panelCart.setLayout(new BorderLayout(10, 10));
        panelCart.setBorder(BorderFactory.createTitledBorder("Giỏ hàng"));

        tableCart = new JTable();

        JScrollPane scrollPane = new JScrollPane(tableCart);
        scrollPane.setVerticalScrollBar(new ScrollbarCustom());
        scrollPane.setHorizontalScrollBar(new ScrollbarCustom());

        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        JPanel buttonPanel = new JPanel(new MigLayout("insets 10, wrap 1", "[fill, grow]", "[]5[]5[]"));
        buttonPanel.setOpaque(false);

        btnEditCart = new ButtonCustom();
        btnEditCart.setText("Sửa");

        btnDeleteCart = new ButtonCustom();
        btnDeleteCart.setText("Xóa");

        btnDeleteAllCart = new ButtonCustom();
        btnDeleteAllCart.setText("Xóa tất cả");

        buttonPanel.add(btnEditCart, "growx");
        buttonPanel.add(btnDeleteCart, "growx");
        buttonPanel.add(btnDeleteAllCart, "growx");

        panelCart.add(scrollPane, BorderLayout.CENTER);
        panelCart.add(buttonPanel, BorderLayout.EAST);
    }

    public void initProductSection() {
        panelProduct.setLayout(new BorderLayout(10, 10));
        panelProduct.setBorder(BorderFactory.createTitledBorder("Sản phẩm"));

        tableProduct = new JTable();

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

        txtCustomerName = new JTextField();
        txtCustomerName.setEditable(false);

        txtPhoneNumber = new JTextField();
        txtPhoneNumber.setEditable(false);
        txtPhoneNumber.setPreferredSize(new Dimension(150, 25));
        btnUpdateCustomer = new ButtonCustom();
        btnUpdateCustomer.setText("Chọn khách hàng");

        customerPanel.add(new JLabel("Tên khách hàng:"));
        customerPanel.add(txtCustomerName);

        customerPanel.add(new JLabel("Số điện thoại:"));
        JPanel phonePanel = new JPanel(new MigLayout("insets 0", "[fill, grow]5[]", "[]"));
        phonePanel.add(txtPhoneNumber, "growx");
        phonePanel.add(btnUpdateCustomer);
        customerPanel.add(phonePanel);

        JPanel billPanel = new JPanel(new MigLayout("insets 10, wrap", "[right]10[fill, grow]", "[]10[]10[]10[grow]"));
        billPanel.setBorder(BorderFactory.createTitledBorder("Chi tiết hóa đơn"));
        billPanel.setOpaque(false);

        txtBillCode = new JTextField();
        txtBillCode.setEditable(false);
        txtTotalAmount = new JTextField();
        txtTotalAmount.setEditable(false);
        cbPaymentMethod = new ComboBoxSuggestion<>();
        cbPaymentMethod.setModel(new DefaultComboBoxModel<>(new String[]{PaymentMethod.paymentMethodToDisplay(PaymentMethod.CASH),
                PaymentMethod.paymentMethodToDisplay(PaymentMethod.BANKING)}));
        txtNote = new JTextArea();
        JScrollPane scrollNote = new JScrollPane(txtNote);
        scrollNote.setVerticalScrollBar(new ScrollbarCustom());
        scrollNote.setHorizontalScrollBar(new ScrollbarCustom());

        billPanel.add(new JLabel("Mã hóa đơn:"));
        billPanel.add(txtBillCode);
        billPanel.add(new JLabel("Tổng tiền:"));
        billPanel.add(txtTotalAmount);
        billPanel.add(new JLabel("Phương thức TT:"));
        billPanel.add(cbPaymentMethod);
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

    public void setPendingBills(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, new String[]{
                "Id hóa đơn", "Khách hàng", "Nhân viên", "Tổng tiền", "Ngày tạo"
        }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablePendingBills.setModel(model);
    }

    public void setCartItems(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, new String[]{
                "Id sản phẩm", "Tên sản phẩm", "Giá", "Số lượng", "Thành tiền"
        }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableCart.setModel(model);
    }

    public void setProducts(Object[][] data) {
        DefaultTableModel model = new DefaultTableModel(data, new String[]{
                "Id sản phẩm", "Tên sản phẩm", "Loại sản phẩm", "Thương hiệu", "Màu sắc", "Chất liệu", "Size", "Giá", "Tồn kho"
        }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableProduct.setModel(model);
    }

    public void setBillInfo(Order order, Customer customer) {
        if (order != null) {
            txtPhoneNumber.setText(customer.getPhoneNumber());
            txtCustomerName.setText(customer.getName());
            cbPaymentMethod.setSelectedItem(PaymentMethod.paymentMethodToDisplay(order.getPaymentMethod()));
            txtNote.setText(order.getDescription());
            txtBillCode.setText(String.valueOf(order.getId()));
            txtTotalAmount.setText(CurrencyUtils.formatCurrency(order.getTotalAmount()));
        } else {
            clearBillInfo();
        }

    }

    public void setCustomerInfo(Customer customer) {
        if (customer != null) {
            txtCustomerName.setText(customer.getName());
            txtPhoneNumber.setText(customer.getPhoneNumber());
        } else {
            txtCustomerName.setText("");
            txtPhoneNumber.setText("");
        }
    }

    public void clearCustomerInfo() {
        txtCustomerName.setText("");
        txtPhoneNumber.setText("");
    }

    public void clearBillInfo() {
        cbPaymentMethod.setSelectedIndex(0);
        txtPhoneNumber.setText("");
        txtPhoneNumber.requestFocus();
        txtNote.setText("");
    }

    public void setProductImage(String imagePath) {
        if (imagePath != null && !imagePath.isBlank()) {
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                imagePanel.setImage(imageFile);
            } else {
                imagePanel.clearImage();
            }
        } else {
            imagePanel.clearImage();
        }
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


    public void addPendingBillSelectionListener(ListSelectionListener listener) {
        tablePendingBills.getSelectionModel().addListSelectionListener(listener);
    }

    public void addCartSelectionListener(ListSelectionListener listener) {
        tableCart.getSelectionModel().addListSelectionListener(listener);
    }

    public void addProductMouseListener(MouseListener listener) {
        tableProduct.addMouseListener(listener);
    }

    public void addUpdateCustomerListener(ActionListener listener) {
        btnUpdateCustomer.addActionListener(listener);
    }

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

    public void addEditCartListener(ActionListener listener) {
        btnEditCart.addActionListener(listener);
    }

    public void addDeleteCartListener(ActionListener listener) {
        btnDeleteCart.addActionListener(listener);
    }

    public void addDeleteAllCartListener(ActionListener listener) {
        btnDeleteAllCart.addActionListener(listener);
    }

    public String getPhoneNumber() {
        return txtPhoneNumber.getText().trim();
    }

    public JTable getPendingBillTable() {
        return tablePendingBills;
    }

    public JTable getCartTable() {
        return tableCart;
    }

    public JTable getProductTable() {
        return tableProduct;
    }

    public PaymentMethod getPaymentMethod() {
        return PaymentMethod.displayToPaymentMethod(cbPaymentMethod.getSelectedItem().toString());
    }

    public String getNote() {
        return txtNote.getText().trim();
    }
}
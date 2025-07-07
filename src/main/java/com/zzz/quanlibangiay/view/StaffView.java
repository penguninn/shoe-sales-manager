package com.zzz.quanlibangiay.view;

import com.toedter.calendar.JDateChooser;
import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.combobox_custom.ComboBoxSuggestion;
import com.zzz.quanlibangiay.component.menu.tabbedpane_custom.MaterialTabbed;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author coole
 */
public class StaffView extends javax.swing.JPanel {

    /**
     * Creates new form Staff
     */
    private PanelBorder leftPanelSection;
    private PanelBorder rightPanelSection;

    private TextField txtSearch;
    private ButtonCustom btnSearch;
    private MaterialTabbed tabbed;
    private JTable tableUsers;
    private JTable tableStaff;

    private TextField txtUserName;
    private TextField txtPassword;
    private TextField txtFullName;
    private ComboBoxSuggestion<String> cbRole;
    private TextField txtName;
    private JRadioButton rdoMale;
    private JRadioButton rdoFemale;
    private TextField txtPhoneNumber;
    private TextField txtAddress;
    private ComboBoxSuggestion<String> cbStatus;
    private ButtonCustom btnAdd, btnEdit, btnDelete, btnClear;
    private JDateChooser birthDateChooser;

    public StaffView() {
        initComponents();
        init();
    }

    public void init() {
        leftPanelSection = new PanelBorder();
        rightPanelSection = new PanelBorder();
        this.setLayout(new MigLayout("insets 0, gap 10", "[70%, grow]5[30%, grow]", "[grow]"));
        this.add(leftPanelSection, "grow");
        this.add(rightPanelSection, "grow");

        initLeftSection();
        initRightSection();
    }

    private void initLeftSection() {
        leftPanelSection.setLayout(new BorderLayout(10, 10));
        leftPanelSection.setBorder(BorderFactory.createTitledBorder("Quản lý nhân viên"));

        JPanel searchPane = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        txtSearch = new TextField();
        txtSearch.setPreferredSize(new Dimension(250, 25));
        btnSearch = new ButtonCustom();
        btnSearch.setText("Tìm kiếm");
        searchPane.add(txtSearch);
        searchPane.add(btnSearch);
        leftPanelSection.add(searchPane, BorderLayout.NORTH);

        tabbed = new MaterialTabbed();
        String[] cols = {"ID", "Tài khoản", "Họ tên", "Vai trò"};
        DefaultTableModel mdlUsers = new DefaultTableModel(cols, 0);
        tableUsers = new JTable(mdlUsers);
        JScrollPane spUsers = createCustomScrollPane(tableUsers);
        TableCustom.apply(spUsers, TableCustom.TableType.DEFAULT);

        String[] cols2 = {"ID", "Tên", "SĐT", "Trạng thái"};
        DefaultTableModel mdlStaff = new DefaultTableModel(cols2, 0);
        tableStaff = new JTable(mdlStaff);
        JScrollPane spStaff = createCustomScrollPane(tableStaff);
        TableCustom.apply(spStaff, TableCustom.TableType.DEFAULT);

        tabbed.add(spUsers, "Đang làm");
        tabbed.add(spStaff, "Đã nghỉ");
        leftPanelSection.add(tabbed, BorderLayout.CENTER);
    }

    private void initRightSection() {
        rightPanelSection.setLayout(new MigLayout("insets 10, wrap 2", "[right]10[fill, grow]", "[]10[]10[]10[]10[]10[]10[30!]10[]"));
        rightPanelSection.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết"));

        txtUserName = new TextField();
        txtPassword = new TextField();
        txtFullName = new TextField();
        cbRole = new ComboBoxSuggestion<>();
        cbRole.setModel(new DefaultComboBoxModel<>(new String[]{"ADMIN", "USER"}));
        txtName = new TextField();
        rdoMale = new JRadioButton("Nam");
        rdoFemale = new JRadioButton("Nữ");
        ButtonGroup grpGender = new ButtonGroup();
        rdoMale.setSelected(true);
        grpGender.add(rdoMale);
        grpGender.add(rdoFemale);
        txtPhoneNumber = new TextField();
        txtAddress = new TextField();
        cbStatus = new ComboBoxSuggestion<>();
        cbStatus.setModel(new DefaultComboBoxModel<>(new String[]{"ACTIVE", "INACTIVE"}));
        birthDateChooser = new JDateChooser();

        rightPanelSection.add(new JLabel("Username:"));
        rightPanelSection.add(txtUserName);
        rightPanelSection.add(new JLabel("Password:"));
        rightPanelSection.add(txtPassword);
        rightPanelSection.add(new JLabel("Full Name:"));
        rightPanelSection.add(txtFullName);
        rightPanelSection.add(new JLabel("Role:"));
        rightPanelSection.add(cbRole);
        rightPanelSection.add(new JLabel("Tên gọi:"));
        rightPanelSection.add(txtName);
        rightPanelSection.add(new JLabel("Giới tính:"));
        rightPanelSection.add(rdoMale, "split 2");
        rightPanelSection.add(rdoFemale);
        rightPanelSection.add(new JLabel("SĐT:"));
        rightPanelSection.add(txtPhoneNumber);
        rightPanelSection.add(new JLabel("Địa chỉ:"));
        rightPanelSection.add(txtAddress);
        rightPanelSection.add(new JLabel("Status:"));
        rightPanelSection.add(cbStatus);
        rightPanelSection.add(new JLabel("Birth Date:"));
        birthDateChooser.setPreferredSize(new Dimension(100, 25));
        rightPanelSection.add(birthDateChooser);

        btnAdd = new ButtonCustom();
        btnAdd.setText("Thêm");
        btnEdit = new ButtonCustom();
        btnEdit.setText("Sửa");
        btnDelete = new ButtonCustom();
        btnDelete.setText("Xóa");
        btnClear = new ButtonCustom();
        btnClear.setText("Làm Mới");

        rightPanelSection.add(btnAdd, "span, split 4, center");
        rightPanelSection.add(btnEdit);
        rightPanelSection.add(btnDelete);
        rightPanelSection.add(btnClear);
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
    public void addSearchStaffListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    // Staff Management Listeners
    public void addAddStaffListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addEditStaffListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }

    public void addDeleteStaffListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addClearStaffListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
}
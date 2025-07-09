package com.zzz.quanlibangiay.view;

import com.toedter.calendar.JDateChooser;
import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.combobox_custom.ComboBoxSuggestion;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.tabbedpane_custom.MaterialTabbed;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.enums.UserRole;
import com.zzz.quanlibangiay.enums.UserStatus;
import com.zzz.quanlibangiay.utils.ValidationUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;

public class StaffView extends javax.swing.JPanel {


    private PanelBorder leftPanelSection;
    private PanelBorder rightPanelSection;

    private JTextField txtSearch;
    private ButtonCustom btnSearch;
    private MaterialTabbed tabbed;
    private JTable tableWorking;
    private JTable tableRetired;

    private JTextField txtId;
    private JTextField txtUserName;
    private JTextField txtPassword;
    private JTextField txtFullName;
    private ComboBoxSuggestion<String> cbRole;
    private JTextField txtName;
    private JRadioButton rdoMale;
    private JRadioButton rdoFemale;
    private JTextField txtPhoneNumber;
    private JTextField txtAddress;
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
        txtSearch = new JTextField();
        txtSearch.setPreferredSize(new Dimension(250, 25));
        btnSearch = new ButtonCustom();
        btnSearch.setText("Tìm kiếm");
        searchPane.add(txtSearch);
        searchPane.add(btnSearch);
        leftPanelSection.add(searchPane, BorderLayout.NORTH);

        tabbed = new MaterialTabbed();
        tableWorking = new JTable();
        JScrollPane spWorking = createCustomScrollPane(tableWorking);
        TableCustom.apply(spWorking, TableCustom.TableType.DEFAULT);

        tableRetired = new JTable();
        JScrollPane spRetired = createCustomScrollPane(tableRetired);
        TableCustom.apply(spRetired, TableCustom.TableType.DEFAULT);

        tabbed.add(spWorking, UserStatus.statusToDisplay(UserStatus.WORKING));
        tabbed.add(spRetired, UserStatus.statusToDisplay(UserStatus.RETIRED));
        leftPanelSection.add(tabbed, BorderLayout.CENTER);
    }

    private void initRightSection() {
        rightPanelSection.setLayout(new MigLayout("insets 10, wrap 2", "[right]10[fill, grow]", "[]10[]10[]10[]10[]10[]10[30!]10[]"));
        rightPanelSection.setBorder(BorderFactory.createTitledBorder("Thông tin chi tiết"));

        txtId = new JTextField();
        txtUserName = new JTextField();
        txtPassword = new JTextField();
        txtFullName = new JTextField();
        cbRole = new ComboBoxSuggestion<>();
        cbRole.setModel(new DefaultComboBoxModel<>(new String[]{UserRole.roleToDisplay(UserRole.STAFF),
                UserRole.roleToDisplay(UserRole.ADMIN)}));
        txtName = new JTextField();
        rdoMale = new JRadioButton("Nam");
        rdoFemale = new JRadioButton("Nữ");
        ButtonGroup grpGender = new ButtonGroup();
        rdoMale.setSelected(true);
        grpGender.add(rdoMale);
        grpGender.add(rdoFemale);
        txtPhoneNumber = new JTextField();
        txtAddress = new JTextField();
        cbStatus = new ComboBoxSuggestion<>();
        cbStatus.setModel(new DefaultComboBoxModel<>(new String[]{UserStatus.statusToDisplay(UserStatus.WORKING),
                UserStatus.statusToDisplay(UserStatus.RETIRED)}));
        birthDateChooser = new JDateChooser();

        rightPanelSection.add(new JLabel("ID:"));
        txtId.setEditable(false);
        rightPanelSection.add(txtId);
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

    public User getDataStaffFromForm() {
        try {
            User user = new User();

            String idText = txtId.getText().trim();
            if (!idText.isEmpty()) {
                try {
                    int id = Integer.parseInt(idText);
                    user.setId(id);
                } catch (NumberFormatException e) {
                    showError("ID không hợp lệ!");
                    txtId.requestFocus();
                    return null;
                }
            }

            String username = txtUserName.getText().trim();
            if (username.isEmpty()) {
                showError("Username không được để trống!");
                txtUserName.requestFocus();
                return null;
            }
            user.setUserName(username);

            String password = txtPassword.getText().trim();
            if (password.isEmpty()) {
                showError("Password không được để trống!");
                txtPassword.requestFocus();
                return null;
            }
            user.setPassword(password);

            String fullName = txtFullName.getText().trim();
            if (fullName.isEmpty()) {
                showError("Họ tên không được để trống!");
                txtFullName.requestFocus();
                return null;
            }
            user.setFullName(fullName);

            user.setRole(UserRole.displayToRole(cbRole.getSelectedItem().toString()));

            String name = txtName.getText().trim();
            user.setName(name);

            user.setGender(rdoMale.isSelected());

            String phone = txtPhoneNumber.getText().trim();
            if (phone.isEmpty()) {
                showError("SĐT không được để trống!");
                txtPhoneNumber.requestFocus();
                return null;
            }
            if (!ValidationUtils.isValidPhone(phone)) {
                showError("SĐT không hợp lệ!");
                txtPhoneNumber.requestFocus();
                return null;
            }
            user.setPhoneNumber(phone);

            String address = txtAddress.getText().trim();
            if (address.isEmpty()) {
                showError("Địa chỉ không được để trống!");
                txtAddress.requestFocus();
                return null;
            }
            user.setAddress(address);

            user.setStatus(UserStatus.displayToStatus(cbStatus.getSelectedItem().toString()));

            if (birthDateChooser.getDate() != null) {
                user.setBirthDate(birthDateChooser.getDate());
            }

            return user;
        } catch (Exception ex) {
            showError("Lỗi khi lấy dữ liệu nhân viên: " + ex.getMessage());
            return null;
        }
    }

    public void setStaffFormData(User user) {
        if (user == null) return;

        txtId.setText(String.valueOf(user.getId()));
        txtUserName.setText(user.getUserName());
        txtPassword.setText(user.getPassword());
        txtFullName.setText(user.getFullName());
        cbRole.setSelectedItem(UserRole.roleToDisplay(user.getRole()));
        cbStatus.setSelectedItem(UserStatus.statusToDisplay(user.getStatus()));
        txtName.setText(user.getName());
        rdoMale.setSelected(user.isGender());
        rdoFemale.setSelected(!user.isGender());
        txtPhoneNumber.setText(user.getPhoneNumber());
        txtAddress.setText(user.getAddress());

        if (user.getBirthDate() != null) {
            birthDateChooser.setDate(user.getBirthDate());
        } else {
            birthDateChooser.setDate(null);
        }
    }

    public void clearStaffForm() {
        txtId.setText("");
        txtUserName.setText("");
        txtPassword.setText("");
        txtFullName.setText("");
        cbRole.setSelectedIndex(0);
        txtName.setText("");
        rdoMale.setSelected(true);
        txtPhoneNumber.setText("");
        txtAddress.setText("");
        cbStatus.setSelectedIndex(0);
        birthDateChooser.setDate(null);
        tableWorking.clearSelection();
        tableRetired.clearSelection();
    }

    public void setUserTableData(Object[][] data, boolean isWorking) {
        String[] columnNames = new String[]{
                "ID", "Username", "Password", "Tên gọi", "Họ tên", "Vai trò", "Giới tính", "SĐT", "Địa chỉ", "Trạng thái", "Ngày sinh"
        };

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        if (isWorking) {
            tableWorking.setModel(model);
        } else {
            tableRetired.setModel(model);
        }
    }

    public String getSearchKeyword() {
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    // Table Listeners
    public void addUserTableWorkingSelectionListener(ListSelectionListener listener) {
        tableWorking.getSelectionModel().addListSelectionListener(listener);
    }

    public void addUserTableRetiredSelectionListener(ListSelectionListener listener) {
        tableRetired.getSelectionModel().addListSelectionListener(listener);
    }

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

    public JTable getTableWorking() {
        return tableWorking;
    }

    public JTable getTableRetired() {
        return tableRetired;
    }

    public MaterialTabbed getTabbed() {
        return tabbed;
    }
}
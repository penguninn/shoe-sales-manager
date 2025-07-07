package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.StaffView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class StaffController {
    
    private StaffView staffView;
    private User currentUser;
    
    public StaffController(StaffView view, User user) {
        this.staffView = view;
        this.currentUser = user;
        initListeners();
        initData();
    }
    
    private void initListeners() {
        staffView.addSearchStaffListener(new SearchStaffListener());
        
        staffView.addAddStaffListener(new AddStaffListener());
        staffView.addEditStaffListener(new EditStaffListener());
        staffView.addDeleteStaffListener(new DeleteStaffListener());
        staffView.addClearStaffListener(new ClearStaffListener());
    }
    
    private void initData() {
        loadStaffList();
        loadRoleData();
        setupFormValidation();
        System.out.println("StaffController initialized for user: " + currentUser.getUserName());
    }
    
    private void loadStaffList() {
        // Implement loading staff list
        System.out.println("Loading staff list...");
        // TODO: Load staff from database and update table
    }
    
    private void loadRoleData() {
        // Load roles for combo box
        System.out.println("Loading role data...");
        // TODO: Load roles and populate combo box
    }
    
    private void setupFormValidation() {
        // Setup form validation rules
        System.out.println("Setting up form validation...");
        // TODO: Setup validation for staff form fields
    }
    
    private void searchStaff() {
        // Get search criteria from view - simplified based on actual StaffView
        String searchText = getSearchText();
        
        System.out.println("Searching staff with criteria:");
        System.out.println("- Search Text: " + searchText);
        
        // TODO: Implement search logic
        loadStaffList(); // Reload with filters
    }
    
    private String getSearchText() {
        // Method to get search text from StaffView
        // TODO: Implement getter method in StaffView
        return "";
    }
    
    private void addStaff() {
        if (!validateStaffForm()) {
            return;
        }
        
        String userName = getUserName();
        String password = getPassword();
        String fullName = getFullName();
        String role = getSelectedRole();
        String name = getName();
        String phone = getPhone();
        String address = getAddress();
        String status = getSelectedStatus();
        
        System.out.println("Adding new staff:");
        System.out.println("- UserName: " + userName);
        System.out.println("- FullName: " + fullName);
        System.out.println("- Phone: " + phone);
        System.out.println("- Role: " + role);
        
        // TODO: Implement add staff logic
        showMessage("Thêm nhân viên thành công!");
        clearForm();
        loadStaffList();
    }
    
    private void editStaff() {
        int selectedRow = getSelectedStaffRow();
        if (selectedRow < 0) {
            showMessage("Vui lòng chọn nhân viên để sửa!");
            return;
        }
        
        // Validate form data
        if (!validateStaffForm()) {
            return;
        }
        
        String staffId = getSelectedStaffId();
        String userName = getUserName();
        String fullName = getFullName();
        String phone = getPhone();
        String role = getSelectedRole();
        
        System.out.println("Editing staff ID: " + staffId);
        System.out.println("- UserName: " + userName);
        System.out.println("- FullName: " + fullName);
        System.out.println("- Phone: " + phone);
        System.out.println("- Role: " + role);
        
        // TODO: Implement edit staff logic
        showMessage("Cập nhật nhân viên thành công!");
        clearForm();
        loadStaffList();
    }
    
    private void deleteStaff() {
        int selectedRow = getSelectedStaffRow();
        if (selectedRow < 0) {
            showMessage("Vui lòng chọn nhân viên để xóa!");
            return;
        }
        
        String staffId = getSelectedStaffId();
        String staffName = getSelectedStaffName();
        
        int confirm = showConfirmDialog(
            "Bạn có chắc chắn muốn xóa nhân viên: " + staffName + "?", 
            "Xác nhận xóa"
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Deleting staff ID: " + staffId);
            // TODO: Implement delete staff logic
            showMessage("Xóa nhân viên thành công!");
            loadStaffList();
        }
    }
    
    private void clearForm() {
        clearStaffForm();
        System.out.println("Staff form cleared");
    }
    
    private boolean validateStaffForm() {
        String userName = getUserName();
        String fullName = getFullName();
        String phone = getPhone();
        
        if (userName == null || userName.trim().isEmpty()) {
            showMessage("Vui lòng nhập username!");
            return false;
        }
        
        if (fullName == null || fullName.trim().isEmpty()) {
            showMessage("Vui lòng nhập họ tên!");
            return false;
        }
        
        if (phone == null || phone.trim().isEmpty()) {
            showMessage("Vui lòng nhập số điện thoại!");
            return false;
        }
        
        return true;
    }
    
    // Utility methods - sẽ cần implement trong StaffView
    private String getUserName() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getPassword() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getFullName() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getSelectedRole() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getName() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getPhone() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getAddress() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getSelectedStatus() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private int getSelectedStaffRow() {
        // TODO: Implement getter in StaffView
        return -1;
    }
    
    private String getSelectedStaffId() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private String getSelectedStaffName() {
        // TODO: Implement getter in StaffView
        return "";
    }
    
    private void clearStaffForm() {
        // TODO: Implement in StaffView
    }
    
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }
    
    // ==================== INNER CLASS LISTENERS ====================
    
    class SearchStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchStaff();
        }
    }
    
    class AddStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addStaff();
        }
    }
    
    class EditStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editStaff();
        }
    }
    
    class DeleteStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteStaff();
        }
    }
    
    class ClearStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearForm();
        }
    }
}
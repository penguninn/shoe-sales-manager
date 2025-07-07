package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.CustomerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class CustomerController {
    
    private CustomerView customerView;
    private User currentUser;
    
    public CustomerController(CustomerView view, User user) {
        this.customerView = view;
        this.currentUser = user;
        initListeners();
        initData();
    }
    
    private void initListeners() {
        customerView.addSearchCustomerListener(new SearchCustomerListener());
        
        customerView.addAddCustomerListener(new AddCustomerListener());
        customerView.addEditCustomerListener(new EditCustomerListener());
        customerView.addDeleteCustomerListener(new DeleteCustomerListener());
        customerView.addClearCustomerListener(new ClearCustomerListener());
    }
    
    private void initData() {
        loadCustomerList();
        setupFormValidation();
        System.out.println("CustomerController initialized for user: " + currentUser.getUserName());
    }
    
    private void loadCustomerList() {
        System.out.println("Loading customer list...");
    }
    
    private void setupFormValidation() {
        System.out.println("Setting up customer form validation...");
    }
    
    private void searchCustomer() {
        String searchText = getSearchText();
        
        System.out.println("Searching customers with criteria:");
        System.out.println("- Search Text: " + searchText);
        
        loadCustomerList();
    }
    
    private String getSearchText() {
        return "";
    }
    
    private void addCustomer() {
        if (!validateCustomerForm()) {
            return;
        }
        
        String fullName = getFullName();
        String phone = getPhone();
        String address = getAddress();
        String gender = getSelectedGender();
        String joinDate = getJoinDate();
        
        System.out.println("Adding new customer:");
        System.out.println("- FullName: " + fullName);
        System.out.println("- Phone: " + phone);
        System.out.println("- Address: " + address);
        System.out.println("- Gender: " + gender);
        
        showMessage("Thêm khách hàng thành công!");
        clearForm();
        loadCustomerList();
    }
    
    private void editCustomer() {
        int selectedRow = getSelectedCustomerRow();
        if (selectedRow < 0) {
            showMessage("Vui lòng chọn khách hàng để sửa!");
            return;
        }
        
        if (!validateCustomerForm()) {
            return;
        }
        
        String customerId = getSelectedCustomerId();
        String fullName = getFullName();
        String phone = getPhone();
        String address = getAddress();
        String gender = getSelectedGender();
        
        System.out.println("Editing customer ID: " + customerId);
        System.out.println("- FullName: " + fullName);
        System.out.println("- Phone: " + phone);
        System.out.println("- Address: " + address);
        
        showMessage("Cập nhật khách hàng thành công!");
        clearForm();
        loadCustomerList();
    }
    
    private void deleteCustomer() {
        int selectedRow = getSelectedCustomerRow();
        if (selectedRow < 0) {
            showMessage("Vui lòng chọn khách hàng để xóa!");
            return;
        }
        
        String customerId = getSelectedCustomerId();
        String customerName = getSelectedCustomerName();
        
        int confirm = showConfirmDialog(
            "Bạn có chắc chắn muốn xóa khách hàng: " + customerName + "?", 
            "Xác nhận xóa"
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            System.out.println("Deleting customer ID: " + customerId);
            // TODO: Implement delete customer logic
            showMessage("Xóa khách hàng thành công!");
            loadCustomerList();
        }
    }
    
    private void clearForm() {
        clearCustomerForm();
        System.out.println("Customer form cleared");
    }
    
    private boolean validateCustomerForm() {
        String fullName = getFullName();
        String phone = getPhone();
        
        if (fullName == null || fullName.trim().isEmpty()) {
            showMessage("Vui lòng nhập họ tên khách hàng!");
            return false;
        }
        
        if (phone == null || phone.trim().isEmpty()) {
            showMessage("Vui lòng nhập số điện thoại!");
            return false;
        }
        
        return true;
    }
    
    private String getFullName() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private String getPhone() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private String getAddress() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private String getSelectedGender() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private String getJoinDate() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private int getSelectedCustomerRow() {
        // TODO: Implement getter in CustomerView
        return -1;
    }
    
    private String getSelectedCustomerId() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private String getSelectedCustomerName() {
        // TODO: Implement getter in CustomerView
        return "";
    }
    
    private void clearCustomerForm() {
        // TODO: Implement in CustomerView
    }
    
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
    
    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }
    

    class SearchCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchCustomer();
        }
    }
    
    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            addCustomer();
        }
    }
    
    class EditCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            editCustomer();
        }
    }
    
    class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            deleteCustomer();
        }
    }
    
    class ClearCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            clearForm();
        }
    }
}
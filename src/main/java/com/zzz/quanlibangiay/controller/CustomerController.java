package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.ManageCustomer;
import com.zzz.quanlibangiay.entity.Customer;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.utils.DateUtils;
import com.zzz.quanlibangiay.utils.ValidationUtils;
import com.zzz.quanlibangiay.view.CustomerView;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerController {

    private CustomerView customerView;
    private ManageCustomer manageCustomer;
    private User user;

    public CustomerController(CustomerView view, User user, ManageCustomer manageCustomer) {
        this.customerView = view;
        this.user = user;
        this.manageCustomer = manageCustomer;
        initListeners();
        initData();
    }

    private void initListeners() {

        customerView.addTableSelectionListener(new CustomerTableSelectionListener());
        customerView.addSearchCustomerListener(new SearchCustomerListener());

        customerView.addAddCustomerListener(new AddCustomerListener());
        customerView.addEditCustomerListener(new EditCustomerListener());
        customerView.addDeleteCustomerListener(new DeleteCustomerListener());
        customerView.addClearCustomerListener(new ClearCustomerListener());
    }

    public void initData() {
        loadCustomerTableData();
    }

    private void loadCustomerTableData() {
        try {
            List<Customer> customers = manageCustomer.getAllCustomers();
            Object[][] customerData = toTableData(customers);
            customerView.setCustomerTableData(customerData);
        } catch (Exception e) {
            e.printStackTrace();
            customerView.showError("Không thể tải danh sách khách hàng!");
        }
    }

    private Object[][] toTableData(List<Customer> customers) {
        Object[][] data = new Object[customers.size()][6];
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            data[i][0] = c.getId();
            data[i][1] = c.getName();
            data[i][2] = c.isGender() ? "Nam" : "Nữ";
            data[i][3] = c.getPhoneNumber();
            data[i][4] = c.getAddress();
            data[i][5] = c.getCreatedDate() != null ? DateUtils.formatDateToShort(c.getCreatedDate()) : "";
        }
        return data;
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private int showConfirmDialog(String message, String title) {
        return JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION);
    }

    class CustomerTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(javax.swing.event.ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = customerView.getTableCustomer().getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = customerView.getTableCustomer().convertRowIndexToModel(selectedRow);
                    Object value = customerView.getTableCustomer().getModel().getValueAt(modelRow, 0);
                    if (value != null) {
                        int id = Integer.parseInt(value.toString());
                        Customer customer = manageCustomer.getCustomerById(id);
                        if (customer != null) {
                            customerView.setCustomerFormData(customer);
                        }
                    }
                }
            }
        }
    }

    class SearchCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String phoneNumber = customerView.getPhoneNumber();
            if (phoneNumber == null || phoneNumber.trim().isEmpty() ) {
                customerView.showError("Vui lòng nhập số điện thoại để tìm kiếm!");
                return;
            }
            if(!ValidationUtils.isValidPhone(phoneNumber)) {
                customerView.showError("Số điện thoại không hợp lệ!");
                return;
            }
            List<Customer> customers = manageCustomer.searchCustomerByPhone(phoneNumber);
            Object[][] customerData = toTableData(customers);
            customerView.setCustomerTableData(customerData);
        }
    }

    class AddCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getDataCustomerFromForm();
            if(customer == null) {
                return;
            }

            if (manageCustomer.isPhoneExists(customer.getPhoneNumber(), 0)) {
                customerView.showError("Số điện thoại đã tồn tại!");
                return;
            }

            boolean ok = manageCustomer.addCustomer(customer);
            if (ok) {
                customerView.showSuccess("Thêm khách hàng thành công!");
            } else {
                customerView.showError("Thêm khách hàng thất bại!");
            }
            loadCustomerTableData();
            customerView.clearCustomerForm();
        }
    }

    class EditCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = customerView.getDataCustomerFromForm();
            if (customer == null) {
                return;
            }

            if (manageCustomer.isPhoneExists(customer.getPhoneNumber(), customer.getId())) {
                customerView.showError("Số điện thoại đã tồn tại!");
                return;
            }

            boolean ok = manageCustomer.updateCustomer(customer);
            if (ok) {
                customerView.showSuccess("Cập nhật khách hàng thành công!");
            } else {
                customerView.showError("Cập nhật khách hàng thất bại!");
            }
            loadCustomerTableData();
            customerView.clearCustomerForm();
        }
    }

    class DeleteCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa khách hàng này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;
            boolean ok = manageCustomer.deleteCustomer(customerView.getDataCustomerFromForm().getId());
            if (ok) {
                customerView.showSuccess("Xóa khách hàng thành công!");
            } else {
                customerView.showError("Xóa khách hàng thất bại!");
            }
            loadCustomerTableData();
            customerView.clearCustomerForm();
        }
    }

    class ClearCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            customerView.clearCustomerForm();
        }
    }
}
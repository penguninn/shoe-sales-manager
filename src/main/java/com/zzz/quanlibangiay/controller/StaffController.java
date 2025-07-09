package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.ManageUser;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.enums.UserRole;
import com.zzz.quanlibangiay.enums.UserStatus;
import com.zzz.quanlibangiay.utils.DateUtils;
import com.zzz.quanlibangiay.view.StaffView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class StaffController {

    private StaffView staffView;
    private ManageUser manageUser;
    private User currentUser;

    public StaffController(StaffView view, User user, ManageUser manageUser) {
        this.staffView = view;
        this.currentUser = user;
        this.manageUser = manageUser;
        initListeners();
        initData();
    }

    private void initListeners() {
        staffView.addUserTableWorkingSelectionListener(new StaffTableSelectionListener());
        staffView.addUserTableRetiredSelectionListener(new StaffTableSelectionListener());
        staffView.addSearchStaffListener(new SearchStaffListener());
        staffView.addAddStaffListener(new AddStaffListener());
        staffView.addEditStaffListener(new EditStaffListener());
        staffView.addDeleteStaffListener(new DeleteStaffListener());
        staffView.addClearStaffListener(new ClearStaffListener());
    }

    public void initData() {
        loadStaffTableData();
    }

    private void loadStaffTableData() {
        try {
            List<User> allUsers = manageUser.getAllUsers();
            Object[][] workingData = toTableData(allUsers.stream().filter(s -> UserStatus.WORKING.equals(s.getStatus())).toList());
            Object[][] retiredData = toTableData(allUsers.stream().filter(s -> UserStatus.RETIRED.equals(s.getStatus())).toList());
            staffView.setUserTableData(workingData, true);
            staffView.setUserTableData(retiredData, false);
        } catch (Exception e) {
            e.printStackTrace();
            staffView.showError("Không thể tải danh sách nhân viên!");
        }
    }

    private Object[][] toTableData(List<User> users) {
        Object[][] data = new Object[users.size()][11];
        for (int i = 0; i < users.size(); i++) {
            User s = users.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getUserName();
            data[i][2] = s.getPassword();
            data[i][3] = s.getName();
            data[i][4] = s.getFullName();
            data[i][5] = UserRole.roleToDisplay(s.getRole());
            data[i][6] = s.isGender() ? "Nam" : "Nữ";
            data[i][7] = s.getPhoneNumber();
            data[i][8] = s.getAddress();
            data[i][9] = UserStatus.statusToDisplay(s.getStatus());
            data[i][10] = s.getBirthDate() != null
                    ? DateUtils.formatDateToShort(s.getBirthDate())
                    : "";
        }
        return data;
    }

    class StaffTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                JTable selectedTable = staffView.getTabbed().getSelectedIndex() == 0
                        ? staffView.getTableWorking()
                        : staffView.getTableRetired();

                int selectedRow = selectedTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = selectedTable.convertRowIndexToModel(selectedRow);
                    Object value = selectedTable.getModel().getValueAt(modelRow, 0);
                    if (value != null) {
                        int id = Integer.parseInt(value.toString());
                        User user = manageUser.getUserById(id);
                        if (user != null) {
                            staffView.setStaffFormData(user);
                        }
                    }
                }
            }
        }
    }

    class SearchStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = staffView.getSearchKeyword();
            List<User> matched = manageUser.searchUserByFullname(keyword);
            Object[][] active = toTableData(matched.stream().filter(s -> UserStatus.WORKING.equals(s.getStatus())).toList());
            Object[][] inactive = toTableData(matched.stream().filter(s -> UserStatus.RETIRED.equals(s.getStatus())).toList());
            staffView.setUserTableData(active, true);
            staffView.setUserTableData(inactive, false);
        }
    }

    class AddStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = staffView.getDataStaffFromForm();
            if (user == null) return;

            if (manageUser.isUsernameExists(user.getUserName(), -1)) {
                staffView.showError("Username đã tồn tại!");
                return;
            }
            if (manageUser.isPhoneExists(user.getPhoneNumber(), -1)) {
                staffView.showError("SĐT đã tồn tại!");
                return;
            }

            boolean ok = manageUser.addUser(user);
            if (ok) {
                staffView.showSuccess("Thêm nhân viên thành công!");
            } else {
                staffView.showError("Thêm nhân viên thất bại!");
            }
            loadStaffTableData();
            staffView.clearStaffForm();
        }
    }

    class EditStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = staffView.getDataStaffFromForm();
            if (user == null) return;

            if (manageUser.isUsernameExists(user.getUserName(), user.getId())) {
                staffView.showError("Username đã tồn tại!");
                return;
            }
            if (manageUser.isPhoneExists(user.getPhoneNumber(), user.getId())) {
                staffView.showError("SĐT đã tồn tại!");
                return;
            }

            boolean ok = manageUser.updateUser(user);
            if (ok) {
                staffView.showSuccess("Cập nhật nhân viên thành công!");
            } else {
                staffView.showError("Cập nhật nhân viên thất bại!");
            }
            loadStaffTableData();
            staffView.clearStaffForm();
        }
    }

    class DeleteStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            User user = staffView.getDataStaffFromForm();

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa nhân viên này không?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageUser.deleteUser(user.getId());
            if (ok) {
                staffView.showSuccess("Xóa nhân viên thành công!");
            } else {
                staffView.showError("Xóa nhân viên thất bại!");
            }
            loadStaffTableData();
            staffView.clearStaffForm();
        }
    }

    class ClearStaffListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            staffView.clearStaffForm();
        }
    }
}
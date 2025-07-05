/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay;

import com.zzz.quanlibangiay.action.ManageUser;
import com.zzz.quanlibangiay.entity.User;

/**
 *
 * @author coole
 */
public class Test  {
    public static void main(String[] args) {
        ManageUser dao = new ManageUser();

        User user = new User("admin", "admin");

        boolean success = dao.addUser(user);

        if (success) {
            System.out.println("✅ Lưu Customer thành công vào XML.");
        } else {
            System.out.println("❌ Lưu thất bại (có thể do ID trùng).");
        }

        // In ra tất cả các bản ghi để xác nhận
        for (User u : dao.getAllUsers()) {
            System.out.println(u.getId() + " - " + u.getUserName()+ " - " + u.getPassword());
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.ManageUser;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.LoginView;
import com.zzz.quanlibangiay.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    
    private LoginView loginView;
    private ManageUser manageUser;

    public LoginController(LoginView view, ManageUser manageUser) {
        this.loginView = view;
        this.manageUser = manageUser;
        loginView.addLoginListener(new LoginListener());
    }
    
    public void init() {
        manageUser = new ManageUser();
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    class LoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if(user == null) {
                return;
            }
           if (manageUser.authenticate(user.getUserName(), user.getPassword())) {
               User u = manageUser.getUserByUserName(user.getUserName());
               MainView mainView = new MainView(u);
               MainController controller = new MainController(mainView, u);
               controller.showMainView();
               loginView.dispose();
           } else {
               loginView.showMessage("Sai tên đăng nhập hoặc mật khẩu.");
           }
        }
    }
}
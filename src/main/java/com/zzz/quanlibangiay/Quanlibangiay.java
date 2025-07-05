/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.zzz.quanlibangiay;

import com.zzz.quanlibangiay.action.ManageUser;
import com.zzz.quanlibangiay.controller.LoginController;
import com.zzz.quanlibangiay.view.LoginView;

/**
 *
 * @author coole
 */
public class Quanlibangiay {

    public static void main(String[] args) {
        LoginView view = new LoginView();
        ManageUser manageUser = new ManageUser();
        LoginController controller = new LoginController(view, manageUser);
        controller.showLoginView(); 
    }
}

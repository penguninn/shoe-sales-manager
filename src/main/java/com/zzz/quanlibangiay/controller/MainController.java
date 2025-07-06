/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.view.MainView;

/**
 *
 * @author coole
 */
public class MainController {
    
    private MainView mainView;
    
    public MainController(MainView view) {
        this.mainView = view;
    }
    
    public void showMainView() {
        mainView.setVisible(true);
    }
    
}

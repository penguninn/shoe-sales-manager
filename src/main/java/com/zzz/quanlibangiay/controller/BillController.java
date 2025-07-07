package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.BillView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BillController {
    
    private BillView billView;
    private User currentUser;
    
    public BillController(BillView view, User user) {
        this.billView = view;
        this.currentUser = user;
        initListeners();
        initData();
    }
    
    private void initListeners() {
        billView.addRefreshBillListener(new RefreshBillListener());
    }
    
    private void initData() {
        loadBillList();
        System.out.println("BillController initialized for user: " + currentUser.getUserName());
    }
    
    private void loadBillList() {
        System.out.println("Loading bill list...");
    }
    
    private void refreshBillList() {
        System.out.println("Refreshing bill list...");
        loadBillList();
    }
    

    class RefreshBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshBillList();
        }
    }
}
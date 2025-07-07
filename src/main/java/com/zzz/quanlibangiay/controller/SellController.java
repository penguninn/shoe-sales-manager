package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.SellView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SellController {
    
    private SellView sellView;
    private User currentUser;
    
    public SellController(SellView view, User user) {
        this.sellView = view;
        this.currentUser = user;
        initListeners();
        initData();
    }
    
    private void initListeners() {
        sellView.addCreateBillListener(new CreateBillListener());
        sellView.addCheckPhoneListener(new CheckPhoneListener());
        sellView.addPaymentListener(new PaymentListener());
        sellView.addCancelBillListener(new CancelBillListener());
        sellView.addRefreshListener(new RefreshListener());
    }
    
    private void initData() {
        loadPendingBills();
        loadProducts();
        System.out.println("SellController initialized for user: " + currentUser.getUserName());
    }
    
    private void loadPendingBills() {
        System.out.println("Loading pending bills...");
    }
    
    private void loadProducts() {
        System.out.println("Loading products...");
    }
    
    // ==================== INNER CLASS LISTENERS ====================
    
    class CreateBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Create bill clicked");
        }
    }
    
    class CheckPhoneListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Check phone clicked");
        }
    }
    
    class PaymentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Payment clicked");
        }
    }
    
    class CancelBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel bill clicked");
        }
    }
    
    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Refresh clicked");
        }
    }
}
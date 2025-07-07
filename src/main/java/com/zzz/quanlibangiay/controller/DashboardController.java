package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.DashboardView;

public class DashboardController {
    
    private DashboardView dashboardView;
    private User currentUser;
    
    public DashboardController(DashboardView view, User user) {
        this.dashboardView = view;
        this.currentUser = user;
        initData();
    }
    
    private void initData() {loadStatistics();
        loadRecentActivities();
        System.out.println("DashboardController initialized for user: " + currentUser.getUserName());
    }
    
    private void loadStatistics() {
        System.out.println("Loading dashboard statistics...");
    }
    
    private void loadRecentActivities() {
        System.out.println("Loading recent activities...");
    }
}
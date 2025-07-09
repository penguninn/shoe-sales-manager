package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.ManageCustomer;
import com.zzz.quanlibangiay.action.ManageOrder;
import com.zzz.quanlibangiay.action.ManageOrderItem;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.DashboardView;

public class DashboardController {

    private DashboardView dashboardView;
    private User currentUser;
    private ManageCustomer manageCustomer;
    private ManageOrder manageOrder;
    private ManageOrderItem manageOrderItem;

    public DashboardController(
            DashboardView view, User currentUser,
            ManageCustomer manageCustomer, ManageOrder manageOrder,
            ManageOrderItem manageOrderItem) {
        this.dashboardView = view;
        this.currentUser = currentUser;
        this.manageCustomer = manageCustomer;
        this.manageOrder = manageOrder;
        this.manageOrderItem = manageOrderItem;
        initData();
    }

    public void initData() {
        loadStatistics();
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
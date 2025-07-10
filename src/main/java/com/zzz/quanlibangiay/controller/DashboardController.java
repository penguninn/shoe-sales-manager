package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.*;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.utils.CurrencyUtils;
import com.zzz.quanlibangiay.utils.DateUtils;
import com.zzz.quanlibangiay.view.DashboardView;

public class DashboardController {

    private DashboardView dashboardView;
    private User currentUser;
    private ManageCustomer manageCustomer;
    private ManageOrder manageOrder;
    private ManageOrderItem manageOrderItem;
    private ManageBrand manageBrand;
    private ManageType manageType;
    private ManageShoe manageShoe;
    private ManageUser manageUser;

    public DashboardController(
            DashboardView view, User currentUser,
            ManageCustomer manageCustomer, ManageOrder manageOrder,
            ManageOrderItem manageOrderItem, ManageShoe manageShoe,
            ManageUser manageUser, ManageBrand manageBrand, ManageType manageType) {
        this.dashboardView = view;
        this.currentUser = currentUser;
        this.manageCustomer = manageCustomer;
        this.manageOrder = manageOrder;
        this.manageOrderItem = manageOrderItem;
        this.manageShoe = manageShoe;
        this.manageUser = manageUser;
        this.manageBrand = manageBrand;
        this.manageType = manageType;
        initData();
    }

    public void initData() {
        loadStatistics();
        loadDashboardData();
    }

    private void loadStatistics() {
        try {
            int totalTypes = calculateTotalTypes();
            int totalShoes = calculateTotalShoes();
            int totalSold = calculateTotalSold();
            double totalRevenue = calculateTotalRevenue();
            double totalCost = calculateTotalCost();
            double totalProfit = calculateTotalProfit();

            dashboardView.updateStatCardsWithNumbers(
                    totalTypes, totalShoes, totalSold,
                    totalRevenue, totalCost, totalProfit
            );

            System.out.println("Statistics loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading statistics: " + e.getMessage());
            e.printStackTrace();
            dashboardView.resetStatCards();
        }
    }

    private void loadDashboardData() {
        try {
            Object[][] productData = getProductStatisticsData();
            dashboardView.setProductTableData(productData);

            Object[][] staffData = getStaffStatisticsData();
            dashboardView.setStaffTableData(staffData);

            Object[][] customerData = getCustomerStatisticsData();
            dashboardView.setCustomerTableData(customerData);

            System.out.println("Dashboard data loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading dashboard data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private int calculateTotalTypes() {
        return manageType.getAllShoeTypes().size();
    }

    private int calculateTotalShoes() {
        return manageShoe.getAllShoes().stream()
                .mapToInt(shoe -> shoe.getQuantity())
                .sum();
    }

    private int calculateTotalSold() {
        return manageOrderItem.countSoldProducts();
    }

    private double calculateTotalRevenue() {
        return manageOrderItem.getTotalRevenue();
    }

    private double calculateTotalCost() {
        return manageOrderItem.getTotalCost();
    }

    private double calculateTotalProfit() {
        return calculateTotalRevenue() - calculateTotalCost();
    }

    private Object[][] getProductStatisticsData() {
        return manageShoe.getAllShoes().stream()
                .map(shoe -> {
                    int soldQuantity = manageOrderItem.getSoldQuantityByProductId(shoe.getId());

                    double profit = (shoe.getPrice() - shoe.getImportPrice()) * soldQuantity;

                    String typeName = manageType.getShoeTypeById(shoe.getTypeId()).getName();
                    String brandName = manageBrand.getBrandById(shoe.getBrandId()).getName();

                    return new Object[]{
                            shoe.getName(),
                            typeName,
                            brandName,
                            shoe.getQuantity(),
                            soldQuantity,
                            CurrencyUtils.formatCurrency(shoe.getPrice()),
                            CurrencyUtils.formatCurrency(shoe.getImportPrice()),
                            CurrencyUtils.formatCurrency(profit)
                    };
                })
                .toArray(Object[][]::new);
    }

    private Object[][] getStaffStatisticsData() {
        return manageUser.getAllUsers().stream()
                .map(user -> {
                    int totalOrders = manageOrderItem.getOrderCountByStaffId(user.getId());

                    double totalRevenue = manageOrderItem.getTotalRevenueByStaffId(user.getId());

                    String statusDisplay = user.getStatus() != null ?
                            (user.getStatus().toString().equals("WORKING") ? "Đang làm việc" : "Đã nghỉ") :
                            "Không xác định";

                    String roleDisplay = user.getRole() != null ?
                            (user.getRole().toString().equals("ADMIN") ? "Quản lý" : "Nhân viên") :
                            "Không xác định";

                    return new Object[]{
                            user.getFullName(),
                            roleDisplay,
                            totalOrders,
                            CurrencyUtils.formatCurrency(totalRevenue),
                            statusDisplay,
                            DateUtils.formatDateToShort(user.getCreatedDate())
                    };
                })
                .toArray(Object[][]::new);
    }

    private Object[][] getCustomerStatisticsData() {
        return manageCustomer.getAllCustomers().stream()
                .map(customer -> {
                    int totalOrders = manageOrder.getOrderCountByCustomerId(customer.getId());

                    double totalSpent = manageOrder.getTotalSpentByCustomerId(customer.getId());

                    return new Object[]{
                            customer.getName(),
                            customer.getPhoneNumber(),
                            customer.getAddress(),
                            totalOrders,
                            CurrencyUtils.formatCurrency(totalSpent)
                    };
                })
                .toArray(Object[][]::new);
    }

    public void refreshData() {
        loadStatistics();
        loadDashboardData();
    }

    public void refreshStatistics() {
        loadStatistics();
    }

    public void refreshTables() {
        loadDashboardData();
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
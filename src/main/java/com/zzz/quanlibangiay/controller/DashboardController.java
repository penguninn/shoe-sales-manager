package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.*;
import com.zzz.quanlibangiay.entity.User;
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
            // Tính toán các thống kê
            int totalTypes = calculateTotalTypes();
            int totalShoes = calculateTotalShoes();
            int totalSold = calculateTotalSold();
            double totalRevenue = calculateTotalRevenue();
            double totalCost = calculateTotalCost();
            double totalProfit = calculateTotalProfit();

            // Cập nhật StatCard
            dashboardView.updateStatCardsWithNumbers(
                totalTypes, totalShoes, totalSold, 
                totalRevenue, totalCost, totalProfit
            );

            System.out.println("Statistics loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading statistics: " + e.getMessage());
            e.printStackTrace();
            // Reset về giá trị mặc định nếu có lỗi
            dashboardView.resetStatCards();
        }
    }

    private void loadDashboardData() {
        try {
            // Load dữ liệu sản phẩm với thống kê đầy đủ
            Object[][] productData = getProductStatisticsData();
            dashboardView.setProductTableData(productData);

            // Load dữ liệu nhân viên với thống kê
            Object[][] staffData = getStaffStatisticsData();
            dashboardView.setStaffTableData(staffData);

            // Load dữ liệu khách hàng với thống kê
            Object[][] customerData = getCustomerStatisticsData();
            dashboardView.setCustomerTableData(customerData);

            System.out.println("Dashboard data loaded successfully");
        } catch (Exception e) {
            System.err.println("Error loading dashboard data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Các phương thức tính toán thống kê
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

    // Phương thức lấy dữ liệu thống kê sản phẩm
    private Object[][] getProductStatisticsData() {
        return manageShoe.getAllShoes().stream()
                .map(shoe -> {
                    // Tính số lượng đã bán
                    int soldQuantity = manageOrderItem.getSoldQuantityByProductId(shoe.getId());
                    
                    // Tính lợi nhuận
                    double profit = (shoe.getPrice() - shoe.getImportPrice()) * soldQuantity;
                    
                    // Lấy thông tin loại và thương hiệu
                    String typeName = manageType.getShoeTypeById(shoe.getTypeId()).getName();
                    String brandName = manageBrand.getBrandById(shoe.getBrandId()).getName();
                    
                    return new Object[]{
                        shoe.getName(),                      // Tên Sản Phẩm
                        typeName,                            // Loại
                        brandName,                           // Thương hiệu
                        shoe.getQuantity(),                  // Tồn kho
                        soldQuantity,                        // Đã bán
                        formatCurrency(shoe.getPrice()),     // Giá bán
                        formatCurrency(shoe.getImportPrice()), // Giá nhập
                        formatCurrency(profit)               // Lợi nhuận
                    };
                })
                .toArray(Object[][]::new);
    }

    // Phương thức lấy dữ liệu thống kê nhân viên
    private Object[][] getStaffStatisticsData() {
        return manageUser.getAllUsers().stream()
                .map(user -> {
                    // Tính tổng đơn hàng xử lý
                    int totalOrders = manageOrderItem.getOrderCountByStaffId(user.getId());
                    
                    // Tính tổng doanh thu đóng góp
                    double totalRevenue = manageOrderItem.getTotalRevenueByStaffId(user.getId());
                    
                    // Trạng thái làm việc
                    String statusDisplay = user.getStatus() != null ? 
                        (user.getStatus().toString().equals("WORKING") ? "Đang làm việc" : "Đã nghỉ") : 
                        "Không xác định";
                    
                    // Chức vụ
                    String roleDisplay = user.getRole() != null ? 
                        (user.getRole().toString().equals("ADMIN") ? "Quản lý" : "Nhân viên") : 
                        "Không xác định";
                    
                    return new Object[]{
                        user.getFullName(),                   // Tên
                        roleDisplay,                          // Chức vụ
                        totalOrders,                          // Tổng đơn hàng xử lý
                        formatCurrency(totalRevenue),         // Tổng doanh thu đóng góp
                        statusDisplay,                        // Trạng thái
                        formatDate(user.getCreatedDate())     // Ngày vào làm
                    };
                })
                .toArray(Object[][]::new);
    }

    // Phương thức lấy dữ liệu thống kê khách hàng
    private Object[][] getCustomerStatisticsData() {
        return manageCustomer.getAllCustomers().stream()
                .map(customer -> {
                    // Tính tổng đơn hàng
                    int totalOrders = manageOrder.getOrderCountByCustomerId(customer.getId());
                    
                    // Tính tổng chi tiêu
                    double totalSpent = manageOrder.getTotalSpentByCustomerId(customer.getId());
                    
                    return new Object[]{
                        customer.getName(),                   // Tên KH
                        customer.getPhoneNumber(),            // SĐT
                        customer.getAddress(),                // Địa chỉ
                        totalOrders,                          // Tổng đơn hàng
                        formatCurrency(totalSpent)            // Tổng chi tiêu
                    };
                })
                .toArray(Object[][]::new);
    }

    // Phương thức làm mới dữ liệu
    public void refreshData() {
        loadStatistics();
        loadDashboardData();
    }

    // Phương thức làm mới chỉ thống kê
    public void refreshStatistics() {
        loadStatistics();
    }

    // Phương thức làm mới chỉ bảng
    public void refreshTables() {
        loadDashboardData();
    }

    // Phương thức format tiền tệ
    private String formatCurrency(double amount) {
        if (amount == 0) return "0đ";
        return String.format("%,.0fđ", amount);
    }

    // Phương thức format ngày
    private String formatDate(java.util.Date date) {
        if (date == null) return "N/A";
        return new java.text.SimpleDateFormat("dd/MM/yyyy").format(date);
    }

    // Getter cho currentUser
    public User getCurrentUser() {
        return currentUser;
    }

    // Phương thức lấy thông tin thống kê chi tiết
    public void printDetailedStatistics() {
        System.out.println("=== THỐNG KÊ CHI TIẾT ===");
        System.out.println("Tổng loại giày: " + calculateTotalTypes());
        System.out.println("Tổng số giày: " + calculateTotalShoes());
        System.out.println("Tổng đã bán: " + calculateTotalSold());
        System.out.println("Tổng doanh thu: " + formatCurrency(calculateTotalRevenue()));
        System.out.println("Tổng chi phí: " + formatCurrency(calculateTotalCost()));
        System.out.println("Tổng lợi nhuận: " + formatCurrency(calculateTotalProfit()));
        System.out.println("=========================");
    }
}
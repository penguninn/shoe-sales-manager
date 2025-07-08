package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.LoginView;
import com.zzz.quanlibangiay.view.MainView;

import java.util.HashMap;
import java.util.Map;


public class MainController {
    
    private MainView mainView;
    private User currentUser;
    
    private Map<String, Object> controllerCache;
    
    private ProductController productController;
    private SellController sellController;
    private BillController billController;
    private StaffController staffController;
    private CustomerController customerController;
    private DashboardController dashboardController;
    
    public MainController(MainView view, User user) {
        this.mainView = view;
        this.currentUser = user;
        this.controllerCache = new HashMap<>();
        
        initMenuListener();
    }

    private void initMenuListener() {
        mainView.setMenuSelectionListener(this::handleMenuSelection);
    }


    public void handleMenuSelection(int index) {
        switch (index) {
            case 0 -> initializeDashboard();
            case 1 -> initializeProduct();
            case 2 -> initializeSell();
            case 3 -> initializeBill();
            case 4 -> initializeStaff();
            case 5 -> initializeCustomer();
            case 9 -> handleLogout();
            default -> System.out.println("Unknown menu index: " + index);
        }
    }

    private void initializeDashboard() {
        if (dashboardController == null) {
            System.out.println("Initializing Dashboard Controller...");
            dashboardController = new DashboardController(mainView.getDashboardView(), currentUser);
            controllerCache.put("dashboard", dashboardController);
        }
        System.out.println("Dashboard Controller activated");
    }

    private void initializeProduct() {
        if (productController == null) {
            System.out.println("Initializing Product Controller...");
            productController = new ProductController(mainView.getProductView());
            controllerCache.put("product", productController);
        }
        System.out.println("Product Controller activated");
    }
    

    private void initializeSell() {
        if (sellController == null) {
            System.out.println("Initializing Sell Controller...");
            sellController = new SellController(mainView.getSellView(), currentUser);
            controllerCache.put("sell", sellController);
        }
        System.out.println("Sell Controller activated");
    }
    

    private void initializeBill() {
        if (billController == null) {
            System.out.println("Initializing Bill Controller...");
            billController = new BillController(mainView.getBillView(), currentUser);
            controllerCache.put("bill", billController);
        }
        System.out.println("Bill Controller activated");
    }


    private void initializeStaff() {
        if (staffController == null) {
            System.out.println("Initializing Staff Controller...");
            staffController = new StaffController(mainView.getStaffView(), currentUser);
            controllerCache.put("staff", staffController);
        }
        System.out.println("Staff Controller activated");
    }

    private void initializeCustomer() {
        if (customerController == null) {
            System.out.println("Initializing Customer Controller...");
            customerController = new CustomerController(mainView.getCustomerView(), currentUser);
            controllerCache.put("customer", customerController);
        }
        System.out.println("Customer Controller activated");
    }

    private void handleLogout() {
        cleanupControllers();
        
        mainView.dispose();
        System.out.println("Logging out and cleaning up controllers");
        new LoginController(new LoginView()).showLoginView();
    }
    

    private void cleanupControllers() {
        controllerCache.clear();
        productController = null;
        sellController = null;
        billController = null;
        staffController = null;
        customerController = null;
        dashboardController = null;
        System.out.println("All controllers cleaned up");
    }
    

    public Object getController(String controllerName) {
        return controllerCache.get(controllerName);
    }

    public boolean isControllerInitialized(String controllerName) {
        return controllerCache.containsKey(controllerName);
    }

    public void showMainView() {
        mainView.setVisible(true);
    }
}
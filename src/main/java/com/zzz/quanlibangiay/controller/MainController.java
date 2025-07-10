package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.ManageBrand;
import com.zzz.quanlibangiay.action.ManageColor;
import com.zzz.quanlibangiay.action.ManageCustomer;
import com.zzz.quanlibangiay.action.ManageMaterial;
import com.zzz.quanlibangiay.action.ManageOrder;
import com.zzz.quanlibangiay.action.ManageOrderItem;
import com.zzz.quanlibangiay.action.ManageShoe;
import com.zzz.quanlibangiay.action.ManageSize;
import com.zzz.quanlibangiay.action.ManageType;
import com.zzz.quanlibangiay.action.ManageUser;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.view.LoginView;
import com.zzz.quanlibangiay.view.MainView;

import java.util.HashMap;
import java.util.Map;

public class MainController {

    private final MainView mainView;
    private final User currentUser;

    private final ManageCustomer manageCustomer = new ManageCustomer();
    private final ManageOrder manageOrder = new ManageOrder();
    private final ManageShoe manageShoe = new ManageShoe();
    private final ManageOrderItem manageOrderItem = new ManageOrderItem(manageOrder, manageShoe);
    private final ManageType manageType = new ManageType();
    private final ManageBrand manageBrand = new ManageBrand();
    private final ManageSize manageSize = new ManageSize();
    private final ManageColor manageColor = new ManageColor();
    private final ManageMaterial manageMaterial = new ManageMaterial();
    private final ManageUser manageUser = new ManageUser();

    private final Map<String, Object> controllerCache = new HashMap<>();

    private ProductController productController;
    private SellController sellController;
    private OrderController orderController;
    private StaffController staffController;
    private CustomerController customerController;
    private DashboardController dashboardController;

    public MainController(MainView view, User user) {
        this.mainView = view;
        this.currentUser = user;
        initMenuListener();
    }

    private void initMenuListener() {
        mainView.setMenuSelectionListener(this::handleMenuSelection);
    }

    private void handleMenuSelection(int index) {
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
            dashboardController = new DashboardController(
                    mainView.getDashboardView(), currentUser,
                    manageCustomer, manageOrder, manageOrderItem,
                    manageShoe, manageUser, manageBrand, manageType
            );
            controllerCache.put("dashboard", dashboardController);
        }
        dashboardController.initData();
        System.out.println("Dashboard Controller activated");
    }

    private void initializeProduct() {
        if (productController == null) {
            productController = new ProductController(
                    mainView.getProductView(),
                    manageShoe, manageType, manageBrand,
                    manageColor, manageMaterial, manageSize, manageOrderItem
            );
            controllerCache.put("product", productController);
        }
        productController.initializeData();
        System.out.println("Product Controller activated");
    }

    private void initializeSell() {
        if (sellController == null) {
            sellController = new SellController(
                    mainView.getSellView(), currentUser,
                    manageCustomer, manageOrder, manageShoe,
                    manageOrderItem, manageType, manageBrand,
                    manageSize, manageColor, manageMaterial, manageUser
            );
            controllerCache.put("sell", sellController);
        }
        sellController.initData();
        System.out.println("Sell Controller activated");
    }

    private void initializeCustomer() {
        if (customerController == null) {
            customerController = new CustomerController(
                    mainView.getCustomerView(), currentUser, manageCustomer
            );
            controllerCache.put("customer", customerController);
        }
        customerController.initData();
        System.out.println("Customer Controller activated");
    }

    private void initializeStaff() {
        if (staffController == null) {
            staffController = new StaffController(
                    mainView.getStaffView(), currentUser, manageUser
        );
        controllerCache.put("staff", staffController);
    }
    staffController.initData();
    System.out.println("Staff Controller activated");
    }

    private void initializeBill() {
        if (orderController == null) {
            orderController = new OrderController(
                    mainView.getBillView(), currentUser,
                    manageOrder, manageCustomer, manageUser,
                    manageShoe, manageType, manageOrderItem,
                    manageBrand, manageSize, manageColor,
                    manageMaterial
            );
            controllerCache.put("bill", orderController);
        }
        orderController.initData();
        System.out.println("Bill Controller activated");
    }

    private void handleLogout() {
        cleanupControllers();
        mainView.dispose();
        System.out.println("Logging out and cleaning up controllers");
        new LoginController(new LoginView(), new ManageUser()).showLoginView();
    }

    private void cleanupControllers() {
        controllerCache.clear();
        productController = null;
        sellController = null;
        orderController = null;
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
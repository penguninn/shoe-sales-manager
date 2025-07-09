package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.menu.EventMenuSelected;
import com.zzz.quanlibangiay.component.menu.SidebarMenu;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.enums.UserRole;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;


public class MainView extends javax.swing.JFrame {

    private MigLayout mlayout;
    private CardLayout cardLayout;
    private SidebarMenu sidebarMenu;
    private JPanel cardPanel;
    
    private DashboardView dashboardView;
    private ProductView productView;
    private SellView sellView;
    private OrderView orderView;
    private StaffView staffView;
    private CustomerView customerView;
    
    private User user;
    
    private Consumer<Integer> menuSelectionListener;

    public MainView(User user) {
        initComponents();
        this.setTitle("Quản lý bán giày");
        this.user = user;
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.pack();
        Dimension defaultSize = this.getSize();
        this.setMinimumSize(defaultSize);
        layerPane.removeAll();
        init();
        setContentPane(layerPane);
        revalidate();
        repaint();
    }

    public void init() {
        mlayout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        layerPane.setLayout(mlayout);

        sidebarMenu = user.getRole().equals(UserRole.ADMIN)
                ? new SidebarMenu(-1)
                : new SidebarMenu(4);

        sidebarMenu.initMoving(MainView.this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        initializeAllViews();

        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
        ));
        
        addViewsToCardPanel();

        layerPane.add(sidebarMenu, "w 230!, spany 2");
        layerPane.add(cardPanel, "w 100%, h 100%");

        setupMenuListener();
        
        showForm(0);
    }
    

    private void initializeAllViews() {
        dashboardView = new DashboardView();
        productView = new ProductView();
        sellView = new SellView();
        orderView = new OrderView();
        staffView = new StaffView();
        customerView = new CustomerView();
    }

    private void addViewsToCardPanel() {
        cardPanel.add(dashboardView, "Dashboard");
        cardPanel.add(productView, "Form_Product");
        cardPanel.add(sellView, "Form_Sell");
        cardPanel.add(orderView, "Form_Bill");
        cardPanel.add(staffView, "Form_Staff");
        cardPanel.add(customerView, "Form_Customer");
    }
    

    private void setupMenuListener() {
        sidebarMenu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selectedIndex(int index) {
                System.out.println("Menu selected: " + index);
                
                if (menuSelectionListener != null) {
                    menuSelectionListener.accept(index);
                }
                showForm(index);
            }
        });
    }

    public void setMenuSelectionListener(Consumer<Integer> listener) {
        this.menuSelectionListener = listener;
    }

    public void showForm(int index) {
        switch (index) {
            case 0 ->
                cardLayout.show(cardPanel, "Dashboard");
            case 1 ->
                cardLayout.show(cardPanel, "Form_Product");
            case 2 ->
                cardLayout.show(cardPanel, "Form_Sell");
            case 3 ->
                cardLayout.show(cardPanel, "Form_Bill");
            case 4 ->
                cardLayout.show(cardPanel, "Form_Staff");
            case 5 ->
                cardLayout.show(cardPanel, "Form_Customer");
            default ->
                System.out.println("Not found form with index: " + index);
        }
    }

    public DashboardView getDashboardView() {
        return dashboardView;
    }
    
    public ProductView getProductView() {
        return productView;
    }
    
    public SellView getSellView() {
        return sellView;
    }
    
    public OrderView getBillView() {
        return orderView;
    }
    
    public StaffView getStaffView() {
        return staffView;
    }
    
    public CustomerView getCustomerView() {
        return customerView;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerPane = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        layerPane.setPreferredSize(new java.awt.Dimension(1800, 600));

        javax.swing.GroupLayout layerPaneLayout = new javax.swing.GroupLayout(layerPane);
        layerPane.setLayout(layerPaneLayout);
        layerPaneLayout.setHorizontalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1800, Short.MAX_VALUE)
        );
        layerPaneLayout.setVerticalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPane, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layerPane;
    // End of variables declaration//GEN-END:variables
}
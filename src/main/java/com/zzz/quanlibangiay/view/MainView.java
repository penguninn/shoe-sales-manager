/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.zzz.quanlibangiay.view;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.zzz.quanlibangiay.component.menu.EventMenuSelected;
import com.zzz.quanlibangiay.component.menu.SidebarMenu;
import com.zzz.quanlibangiay.controller.LoginController;
import com.zzz.quanlibangiay.entity.User;
import com.zzz.quanlibangiay.enums.UserRole;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author coole
 */
public class MainView extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private MigLayout mlayout;
    private CardLayout cardLayout;
    private SidebarMenu sidebarMenu;
    private JPanel cardPanel;
    private Dashboard dashboard;
    private Product product;
    private Sell sell;
    private Bill bill;
    private Staff staff;
    private Customer customer;
    private User user;

    public MainView(User user) {
        initComponents();
        this.user = user;
        this.setLocationRelativeTo(null);
        layerPane.removeAll();
        init();
        setContentPane(layerPane);
        revalidate();
        repaint();
    }

    public void init() {
        setResizable(false);
        mlayout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        layerPane.setLayout(mlayout);

        sidebarMenu = user.getRole().equals(UserRole.Admin)
                ? new SidebarMenu(-1)
                : new SidebarMenu(4);

        sidebarMenu.initMoving(MainView.this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        dashboard = new Dashboard();
        product = new Product();
        sell = new Sell();
        bill = new Bill();
        staff = new Staff();
        customer = new Customer();

        cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE),
                BorderFactory.createEmptyBorder(6, 6, 6, 6)
            )
        );
        cardPanel.add(dashboard, "Dashboard");
        cardPanel.add(product, "Form_Product");
        cardPanel.add(sell, "Form_Sell");
        cardPanel.add(bill, "Form_Bill");
        cardPanel.add(staff, "Form_Staff");
        cardPanel.add(customer, "Form_Customer");

        layerPane.add(sidebarMenu, "w 230!, spany 2");
        layerPane.add(cardPanel, "w 100%, h 100%");

        sidebarMenu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selectedIndex(int index) {
                System.out.println(index);
                showForm(index);
            }
        });
        showForm(0);
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
            case 9 -> {
                this.dispose();
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                controller.showLoginView();
            }
            default ->
                System.out.println("Not found form with index: " + index);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerPane = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        layerPane.setPreferredSize(new java.awt.Dimension(1400, 700));

        javax.swing.GroupLayout layerPaneLayout = new javax.swing.GroupLayout(layerPane);
        layerPane.setLayout(layerPaneLayout);
        layerPaneLayout.setHorizontalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1400, Short.MAX_VALUE)
        );
        layerPaneLayout.setVerticalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(layerPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainView(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layerPane;
    // End of variables declaration//GEN-END:variables
}

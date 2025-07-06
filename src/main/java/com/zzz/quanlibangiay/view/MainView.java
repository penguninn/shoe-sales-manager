/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.zzz.quanlibangiay.view;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.zzz.quanlibangiay.component.menu.EventMenuSelected;
import com.zzz.quanlibangiay.component.menu.SidebarMenu;
import com.zzz.quanlibangiay.controller.LoginController;
import java.awt.CardLayout;
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
    private JPanel cardPanel;
    private SidebarMenu sidebarMenu;

    public MainView() {
        initComponents();
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
        sidebarMenu = new SidebarMenu(4);
//        if (!nv.getChucVu().equals("ql")) {
//            menu = new Menu(5);
//        } else {
//            menu = new Menu(-1);
//        }

//        header = new Header(this, nv.getHoTen());
//        Form_Products = new Form_Products(this);
//        Form_Sell = new Form_Sell(nv);
//        Form_Bill = new Form_Bill();
//        Form_Staffs = new Form_Staffs();
//        Form_Customer = new Form_Customer();
//        Form_Profile = new Form_Profile(nv);
        sidebarMenu.initMoving(MainView.this);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
//        cardPanel.add(Form_Products, "Form_Products");
//        cardPanel.add(Form_Sell, "Form_Sell");
//        cardPanel.add(Form_Bill, "Form_Bill");
//        cardPanel.add(Form_Staffs, "Form_Staffs");
//        cardPanel.add(Form_Customer, "Form_Customer");
//        cardPanel.add(Form_Profile, "Form_Profile");

        layerPane.add(sidebarMenu, "w 230!, spany 2");
        layerPane.add(cardPanel, "w 100%, h 100%");

        sidebarMenu.addEventMenuSelected(new EventMenuSelected() {
            @Override
            public void selectedIndex(int index) {
                System.out.println(index);
                showForm(index);
            }
        });
    }

    public void showForm(int index) {
        switch (index) {
            case 0:
                cardLayout.show(cardPanel, "Dashboard");
                break;
            case 1:
                cardLayout.show(cardPanel, "Form_Product");
                break;
            case 2:
                cardLayout.show(cardPanel, "Form_Sell");
                break;
            case 3:
                cardLayout.show(cardPanel, "Form_Bill");
                break;
            case 4:
                cardLayout.show(cardPanel, "Form_Staff");
                break;
            case 5:
                cardLayout.show(cardPanel, "Form_Customer");
                break;
            case 9:
                cardLayout.show(cardPanel, "Form_Profile");
                break;
            case 10:
                this.dispose();
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                controller.showLoginView();
                break;
            default:
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layerPane = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layerPaneLayout = new javax.swing.GroupLayout(layerPane);
        layerPane.setLayout(layerPaneLayout);
        layerPaneLayout.setHorizontalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1258, Short.MAX_VALUE)
        );
        layerPaneLayout.setVerticalGroup(
            layerPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 714, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layerPane)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(() -> {
            new MainView().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layerPane;
    // End of variables declaration//GEN-END:variables
}

package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.combobox_custom.ComboBoxSuggestion;
import com.zzz.quanlibangiay.component.menu.tabbedpane_custom.MaterialTabbed;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author coole
 */
public class Product extends javax.swing.JPanel {

    /**
     * Creates new form Product
     */
    private MaterialTabbed materialTabbed;
    private PanelBorder searchPanelBorder;
    private PanelBorder productPanelBorder;
    private PanelBorder attributePanelBorder;
    private JPanel searchPanel;
    private JPanel productPanel;
    private JPanel attributePanel;

    public Product() {
        initComponents();
        init();
    }

    public void init() {
        materialTabbed = new MaterialTabbed();

        searchPanelBorder = new PanelBorder();
        productPanelBorder = new PanelBorder();
        attributePanelBorder = new PanelBorder();

        searchPanel = new JPanel(new MigLayout("fill", "0[fill]0", "5[fill]5"));
        productPanel = new JPanel(new MigLayout("fill", "0[fill]0", "5[fill]5"));
        attributePanel = new JPanel(new MigLayout("fill", "0[fill]0", "5[fill]5"));

        initSearchPanel();
        initProductPanel();
        initAttributePanel();

        searchPanel.add(searchPanelBorder, "grow");
        productPanel.add(productPanelBorder, "grow");
        attributePanel.add(attributePanelBorder, "grow");

        materialTabbed.add(searchPanel, "Tìm kiếm sản phẩm");
        materialTabbed.add(productPanel, "Sản phẩm");
        materialTabbed.add(attributePanel, "Thuộc tính sản phẩm");

        this.setBackground(Color.decode("#f2f2f2"));
        this.setLayout(new MigLayout("fill", "0[fill]0", "0[fill]0"));
        this.add(materialTabbed, "grow");
    }

    private void initSearchPanel() {
        searchPanelBorder.setLayout(new BorderLayout(0, 10));
        searchPanelBorder.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel filterPanel = new JPanel(new MigLayout(
                "insets 10, wrap 4",
                "[right]10[fill, grow]20[]10[fill, grow]",
                "[]10[]10[]10[]10[]10[]"
        ));
        filterPanel.setBackground(Color.white);

        filterPanel.add(new JLabel("Tên"), "right");
        TextField txtName = new TextField();
        filterPanel.add(txtName, "growx");

        filterPanel.add(new JLabel("Thương hiệu"), "right");
        ComboBoxSuggestion<String> cbBrand = new ComboBoxSuggestion<>();
        cbBrand.setModel(new DefaultComboBoxModel<>(new String[]{"Adidas", "Nike", "Puma"}));
        filterPanel.add(cbBrand, "growx");

        filterPanel.add(new JLabel("Loại"), "right");
        ComboBoxSuggestion<String> cbType = new ComboBoxSuggestion<>();
        cbType.setModel(new DefaultComboBoxModel<>(new String[]{"Thể thao", "Sneaker", "Chạy bộ"}));
        filterPanel.add(cbType, "growx");

        filterPanel.add(new JLabel("Màu sắc"), "right");
        ComboBoxSuggestion<String> cbColor = new ComboBoxSuggestion<>();
        cbColor.setModel(new DefaultComboBoxModel<>(new String[]{"Đen", "Trắng", "Xanh navy", "Đỏ"}));
        filterPanel.add(cbColor, "growx");

        filterPanel.add(new JLabel("Chất liệu"), "right");
        ComboBoxSuggestion<String> cbMaterial = new ComboBoxSuggestion<>();
        cbMaterial.setModel(new DefaultComboBoxModel<>(new String[]{"Cotton", "Polyester", "Nylon", "Da"}));
        filterPanel.add(cbMaterial, "growx");

        filterPanel.add(new JLabel("Size"), "right");
        ComboBoxSuggestion<String> cbSize = new ComboBoxSuggestion<>();
        cbSize.setModel(new DefaultComboBoxModel<>(new String[]{"36", "37", "38", "39", "40", "41", "42"}));
        filterPanel.add(cbSize, "growx");

        filterPanel.add(new JLabel("Giá (từ–đến)"), "right");
        TextField txtPriceFrom = new TextField();
        txtPriceFrom.setPreferredSize(new Dimension(80, 30));
        TextField txtPriceTo = new TextField();
        txtPriceTo.setPreferredSize(new Dimension(80, 30));
        JPanel pricePanel = new JPanel(new MigLayout("insets 0, wrap 2", "[fill, grow]5[fill, grow]"));
        pricePanel.add(txtPriceFrom, "growx");
        pricePanel.add(txtPriceTo, "growx");
        filterPanel.add(pricePanel, "span 3, growx");

        ButtonCustom btnSearch = new ButtonCustom();
        ButtonCustom btnReset = new ButtonCustom();

        btnReset.setText("Reset");
        btnSearch.setText("Tìm kiếm");
        filterPanel.add(btnSearch, "span 4, split 2, right");
        filterPanel.add(btnReset, "");

        searchPanelBorder.add(filterPanel, BorderLayout.NORTH);

        String[] cols = {"Tên", "Thương hiệu", "Loại", "Màu", "Chất liệu", "Size", "Số lượng", "Giá"};
        Object[][] data = {};
        JTable table = new JTable(data, cols);
        JScrollPane scroll = createCustomScrollPane(table);
        TableCustom.apply(scroll, TableCustom.TableType.DEFAULT);
        searchPanelBorder.add(scroll, BorderLayout.CENTER);
    }

    private void initProductPanel() {
        productPanelBorder.setLayout(new MigLayout("insets 10, wrap 1", "[fill]"));

        JPanel formPanel = new JPanel(new MigLayout(
                "wrap 5",
                "[right]10[fill, grow]20[right]10[fill, grow]20[200!]"));
        formPanel.setBackground(Color.WHITE);

        ComboBoxSuggestion<String> cbBrand = new ComboBoxSuggestion<>();
        cbBrand.setModel(new DefaultComboBoxModel<>(new String[]{"Adidas", "Nike", "Puma"}));

        ComboBoxSuggestion<String> cbType = new ComboBoxSuggestion<>();
        cbType.setModel(new DefaultComboBoxModel<>(new String[]{"Giày thể thao", "Giày sneaker", "Giày chạy bộ"}));

        ComboBoxSuggestion<String> cbColor = new ComboBoxSuggestion<>();
        cbColor.setModel(new DefaultComboBoxModel<>(new String[]{"Đen", "Xanh navy", "Xám", "Trắng", "Đỏ", "Xanh lá"}));

        ComboBoxSuggestion<String> cbMaterial = new ComboBoxSuggestion<>();
        cbMaterial.setModel(new DefaultComboBoxModel<>(new String[]{"Polyester", "Cotton", "Nylon", "Spandex", "Fleece"}));

        ComboBoxSuggestion<String> cbSize = new ComboBoxSuggestion<>();
        cbSize.setModel(new DefaultComboBoxModel<>(new String[]{"36", "37", "38", "39", "40", "41", "42", "43"}));

        TextField txtName = new TextField();
        TextField txtQuantity = new TextField();
        TextField txtPrice = new TextField();
        TextField txtImportPrice = new TextField();

        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.LIGHT_GRAY);
        imagePanel.setPreferredSize(new Dimension(200, 240));
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        formPanel.add(new JLabel("Tên Sản Phẩm"));
        formPanel.add(txtName);
        formPanel.add(new JLabel("Thương Hiệu"));
        formPanel.add(cbBrand);
        formPanel.add(imagePanel, "cell 4 0 1 6, growy");

        formPanel.add(new JLabel("Loại Sản Phẩm"));
        formPanel.add(cbType);
        formPanel.add(new JLabel("Chất Liệu"));
        formPanel.add(cbMaterial);

        formPanel.add(new JLabel("Màu Sắc"));
        formPanel.add(cbColor);
        formPanel.add(new JLabel("Size"));
        formPanel.add(cbSize);

        formPanel.add(new JLabel("Số Lượng"));
        formPanel.add(txtQuantity);
        formPanel.add(new JLabel("Giá Bán"));
        formPanel.add(txtPrice);

        formPanel.add(new JLabel("Giá Nhập"));
        formPanel.add(txtImportPrice);

        ButtonCustom btnAdd = new ButtonCustom();
        ButtonCustom btnEdit = new ButtonCustom();
        ButtonCustom btnDelete = new ButtonCustom();
        ButtonCustom btnClear = new ButtonCustom();

        btnAdd.setText("Thêm");
        btnEdit.setText("Sửa");
        btnDelete.setText("Xóa");
        btnClear.setText("Làm Mới");

        formPanel.add(btnAdd, "skip 2");
        formPanel.add(btnEdit);
        formPanel.add(btnDelete);
        formPanel.add(btnClear);

        productPanelBorder.add(formPanel, "growx");

        String[] columns = {
            "Tên SP", "Thương hiệu", "Loại", "Màu sắc", "Chất liệu",
            "Size", "Số lượng", "Giá bán", "Giá nhập", "Hình ảnh"
        };
        Object[][] data = {};

        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = createCustomScrollPane(table);
        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        productPanelBorder.add(scrollPane, "grow, push");
    }

    private void initAttributePanel() {
        attributePanelBorder.setLayout(new BorderLayout());

        MaterialTabbed tabbedPane = new MaterialTabbed();

        tabbedPane.addTab("Loại sản phẩm", createAttributeCrudPanel("Loại sản phẩm"));
        tabbedPane.addTab("Chất liệu", createAttributeCrudPanel("Chất liệu"));
        tabbedPane.addTab("Màu sắc", createAttributeCrudPanel("Màu sắc"));
        tabbedPane.addTab("Size", createAttributeCrudPanel("Size"));
        tabbedPane.addTab("Thương hiệu", createAttributeCrudPanel("Thương hiệu"));

        attributePanelBorder.add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createAttributeCrudPanel(String labelText) {
        JPanel panel = new JPanel(new MigLayout(
                "insets 10, wrap 3",
                "[fill, grow]10[fill, grow]10[fill, grow]",
                "[][][grow]")); 
        panel.setBackground(Color.WHITE);

        JLabel lblName = new JLabel(labelText);
        TextField txtName = new TextField();
        txtName.setPreferredSize(new Dimension(200, 30));

        ButtonCustom btnAdd = new ButtonCustom();
        ButtonCustom btnEdit = new ButtonCustom();
        ButtonCustom btnDelete = new ButtonCustom();
        ButtonCustom btnClear = new ButtonCustom();

        btnAdd.setText("Thêm");
        btnEdit.setText("Sửa");
        btnDelete.setText("Xóa");
        btnClear.setText("Làm Mới");

        panel.add(lblName);
        panel.add(btnAdd, "growx");
        panel.add(btnEdit, "growx");

        panel.add(txtName);
        panel.add(btnDelete, "growx");
        panel.add(btnClear, "growx");

        String[] columns = {"ID", labelText};
        Object[][] data = {};
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = createCustomScrollPane(table);
        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);

        panel.add(scrollPane, "span 3, grow, push");

        return panel;
    }
    
    private JScrollPane createCustomScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBar(new ScrollbarCustom());
        scroll.setHorizontalScrollBar(new ScrollbarCustom());
        return scroll;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

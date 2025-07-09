package com.zzz.quanlibangiay.view;

import com.zzz.quanlibangiay.component.button_custom.ButtonCustom;
import com.zzz.quanlibangiay.component.combobox_custom.ComboBoxSuggestion;
import com.zzz.quanlibangiay.component.panel_custom.ImagePanel;
import com.zzz.quanlibangiay.component.panel_custom.PanelBorder;
import com.zzz.quanlibangiay.component.scrollbar_custom.ScrollbarCustom;
import com.zzz.quanlibangiay.component.tabbedpane_custom.MaterialTabbed;
import com.zzz.quanlibangiay.component.table_custom.TableCustom;
import com.zzz.quanlibangiay.entity.*;
import com.zzz.quanlibangiay.utils.CurrencyUtils;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class ProductView extends javax.swing.JPanel {

    private Font vietnameseFont;
    private String selectedImageName;

    private MaterialTabbed materialTabbed;
    private PanelBorder searchPanelBorder;
    private PanelBorder productPanelBorder;
    private PanelBorder attributePanelBorder;
    private JPanel productPanel;
    private JPanel attributePanel;
    private JTable tableProduct;

    private JTextField txtProductId;
    private JTextField txtProductName;
    private ComboBoxSuggestion<Brand> cbProductBrand;
    private ComboBoxSuggestion<Type> cbProductType;
    private ComboBoxSuggestion<com.zzz.quanlibangiay.entity.Color> cbProductColor;
    private ComboBoxSuggestion<Material> cbProductMaterial;
    private ComboBoxSuggestion<Size> cbProductSize;
    private JTextField txtProductQuantity;
    private JTextField txtProductPrice;
    private JTextField txtProductImportPrice;
    private ImagePanel imagePanel;

    private JTextField txtSearchName;
    private ComboBoxSuggestion<Brand> cbSearchBrand;
    private ComboBoxSuggestion<Type> cbSearchType;
    private ComboBoxSuggestion<com.zzz.quanlibangiay.entity.Color> cbSearchColor;
    private ComboBoxSuggestion<Material> cbSearchMaterial;
    private ComboBoxSuggestion<Size> cbSearchSize;
    private JTextField txtSearchPriceFrom;
    private JTextField txtSearchPriceTo;

    private ButtonCustom btnSearch;
    private ButtonCustom btnReset;
    private ButtonCustom btnProductAdd;
    private ButtonCustom btnProductEdit;
    private ButtonCustom btnProductDelete;
    private ButtonCustom btnProductClear;

    private ButtonGroup sortButtonGroup;
    private JRadioButton rdoIdAsc, rdoIdDesc;
    private JRadioButton rdoNameAsc, rdoNameDesc;
    private JRadioButton rdoPriceAsc, rdoPriceDesc;
    private JRadioButton rdoImportPriceAsc, rdoImportPriceDesc;
    private JRadioButton rdoQuantityAsc, rdoQuantityDesc;

    private ButtonCustom btnTypeAdd, btnTypeEdit, btnTypeDelete, btnTypeClear;
    private ButtonCustom btnMaterialAdd, btnMaterialEdit, btnMaterialDelete, btnMaterialClear;
    private ButtonCustom btnColorAdd, btnColorEdit, btnColorDelete, btnColorClear;
    private ButtonCustom btnSizeAdd, btnSizeEdit, btnSizeDelete, btnSizeClear;
    private ButtonCustom btnBrandAdd, btnBrandEdit, btnBrandDelete, btnBrandClear;

    private JTable typeTable, materialTable, colorTable, sizeTable, brandTable;
    private JTextField txtTypeId, txtTypeName, txtMaterialId, txtMaterialName;
    private JTextField txtColorId, txtColorName, txtSizeId, txtSizeName;
    private JTextField txtBrandId, txtBrandName;


    public ProductView() {
        initComponents();
        init();
    }

    public void init() {

        vietnameseFont = new Font("Arial", Font.PLAIN, 14);

        materialTabbed = new MaterialTabbed();

        searchPanelBorder = new PanelBorder();
        productPanelBorder = new PanelBorder();
        attributePanelBorder = new PanelBorder();

        productPanel = new JPanel(new MigLayout(
                "fill",
                "0[20%][80%]0",
                "5[fill]5"
        ));
        attributePanel = new JPanel(new MigLayout("fill", "0[fill]0", "5[fill]5"));

        initSearchPanel();
        initProductPanel();
        initAttributePanel();

        productPanel.add(productPanelBorder, "cell 1 0, grow");
        productPanel.add(searchPanelBorder, "cell 0 0, grow");
        attributePanel.add(attributePanelBorder, "grow");

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
                "insets 10, wrap 2",
                "[right]10[fill, grow]",
                "[]10[]10[]10[]10[]10[]"
        ));
        filterPanel.setBackground(Color.WHITE);

        JLabel searchLabel = new JLabel("Tìm kiếm sản phẩm");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        filterPanel.add(searchLabel, "span 2, wrap, left");

        filterPanel.add(new JLabel("Tên"), "right");
        txtSearchName = new JTextField();
        filterPanel.add(txtSearchName, "growx");

        filterPanel.add(new JLabel("Thương hiệu"), "right");
        cbSearchBrand = new ComboBoxSuggestion<>();
        filterPanel.add(cbSearchBrand, "growx");

        filterPanel.add(new JLabel("Loại"), "right");
        cbSearchType = new ComboBoxSuggestion<>();
        filterPanel.add(cbSearchType, "growx");

        filterPanel.add(new JLabel("Màu sắc"), "right");
        cbSearchColor = new ComboBoxSuggestion<>();
        filterPanel.add(cbSearchColor, "growx");

        filterPanel.add(new JLabel("Chất liệu"), "right");
        cbSearchMaterial = new ComboBoxSuggestion<>();
        filterPanel.add(cbSearchMaterial, "growx");

        filterPanel.add(new JLabel("Size"), "right");
        cbSearchSize = new ComboBoxSuggestion<>();
        filterPanel.add(cbSearchSize, "growx");

        filterPanel.add(new JLabel("Giá từ–đến"), "right");

        JPanel pricePanel = new JPanel(new MigLayout("insets 0, wrap 2", "[fill, grow]5[fill, grow]"));
        txtSearchPriceFrom = new JTextField();
        txtSearchPriceFrom.setPreferredSize(new Dimension(80, 30));
        txtSearchPriceTo = new JTextField();
        txtSearchPriceTo.setPreferredSize(new Dimension(80, 30));
        pricePanel.add(txtSearchPriceFrom, "growx");
        pricePanel.add(txtSearchPriceTo, "growx");
        filterPanel.add(pricePanel, "growx");

        JPanel sortPanel = new JPanel(new MigLayout(
                "insets 0, wrap 1",
                "[fill, grow]",
                "[]10[]"
        ));
        sortPanel.setBackground(Color.WHITE);

        JLabel sortLabel = new JLabel("Sắp xếp");
        sortLabel.setFont(new Font("Arial", Font.BOLD, 14));
        sortPanel.add(sortLabel, "wrap");

        sortButtonGroup = new ButtonGroup();

        JPanel sortOptionsPanel = new JPanel(new MigLayout(
                "insets 10, wrap 2",
                "[fill]50[fill]"
        ));
        sortOptionsPanel.setBackground(Color.WHITE);

        rdoIdAsc = new JRadioButton("ID ↑");
        rdoIdDesc = new JRadioButton("ID ↓");
        rdoNameAsc = new JRadioButton("Tên ↑");
        rdoNameDesc = new JRadioButton("Tên ↓");
        rdoPriceAsc = new JRadioButton("Giá bán ↑");
        rdoPriceDesc = new JRadioButton("Giá bán ↓");
        rdoImportPriceAsc = new JRadioButton("Giá nhập ↑");
        rdoImportPriceDesc = new JRadioButton("Giá nhập ↓");
        rdoQuantityAsc = new JRadioButton("Số lượng ↑");
        rdoQuantityDesc = new JRadioButton("Số lượng ↓");

        sortButtonGroup.add(rdoIdAsc);
        sortButtonGroup.add(rdoIdDesc);
        sortButtonGroup.add(rdoNameAsc);
        sortButtonGroup.add(rdoNameDesc);
        sortButtonGroup.add(rdoPriceAsc);
        sortButtonGroup.add(rdoPriceDesc);
        sortButtonGroup.add(rdoImportPriceAsc);
        sortButtonGroup.add(rdoImportPriceDesc);
        sortButtonGroup.add(rdoQuantityAsc);
        sortButtonGroup.add(rdoQuantityDesc);

        rdoIdAsc.setSelected(true);

        sortOptionsPanel.add(rdoIdAsc);
        sortOptionsPanel.add(rdoIdDesc);
        sortOptionsPanel.add(rdoNameAsc);
        sortOptionsPanel.add(rdoNameDesc);
        sortOptionsPanel.add(rdoPriceAsc);
        sortOptionsPanel.add(rdoPriceDesc);
        sortOptionsPanel.add(rdoImportPriceAsc);
        sortOptionsPanel.add(rdoImportPriceDesc);
        sortOptionsPanel.add(rdoQuantityAsc);
        sortOptionsPanel.add(rdoQuantityDesc);

        btnSearch = new ButtonCustom();
        btnReset = new ButtonCustom();

        btnReset.setText("Reset");
        btnSearch.setText("Tìm kiếm");

        sortPanel.add(sortOptionsPanel, "growx");
        sortPanel.add(btnSearch, "split 2, growx");
        sortPanel.add(btnReset, "growx");

        searchPanelBorder.add(filterPanel, BorderLayout.NORTH);
        searchPanelBorder.add(sortPanel, BorderLayout.CENTER);
    }

    private void initProductPanel() {
        productPanelBorder.setLayout(new MigLayout("insets 10, wrap 1", "[fill]"));

        JPanel formPanel = new JPanel(new MigLayout(
                "wrap 5",
                "[right]10[fill, grow]20[right]10[fill, grow]20[200!]",
                "[]10[]10[]10[]10[]10[]"));
        formPanel.setBackground(Color.WHITE);

        cbProductBrand = new ComboBoxSuggestion<>();
        cbProductType = new ComboBoxSuggestion<>();
        cbProductColor = new ComboBoxSuggestion<>();
        cbProductMaterial = new ComboBoxSuggestion<>();
        cbProductSize = new ComboBoxSuggestion<>();
        txtProductName = new JTextField();
        txtProductId = new JTextField();
        txtProductQuantity = new JTextField();
        txtProductPrice = new JTextField();
        txtProductImportPrice = new JTextField();

        imagePanel = new ImagePanel(200, 240);
        imagePanel.setBackground(Color.LIGHT_GRAY);
        imagePanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        imagePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Chọn ảnh sản phẩm");
                chooser.setFileFilter(new FileNameExtensionFilter(
                        "Ảnh (*.jpg, *.png, *.gif)", "jpg", "jpeg", "png", "gif"));
                int result = chooser.showOpenDialog(ProductView.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedImageFile = chooser.getSelectedFile();
                    selectedImageName = selectedImageFile.getAbsolutePath();
                    imagePanel.setImage(selectedImageFile);
                }
            }
        });

        formPanel.add(new JLabel("ID"));
        txtProductId.setEditable(false);
        formPanel.add(txtProductId);
        formPanel.add(new JLabel("Tên Sản Phẩm"));
        formPanel.add(txtProductName);
        formPanel.add(imagePanel, "cell 4 0 1 6, h 240!, w 200!");

        formPanel.add(new JLabel("Thương Hiệu"));
        formPanel.add(cbProductBrand);

        formPanel.add(new JLabel("Loại Sản Phẩm"));
        formPanel.add(cbProductType);
        formPanel.add(new JLabel("Chất Liệu"));
        formPanel.add(cbProductMaterial);

        formPanel.add(new JLabel("Màu Sắc"));
        formPanel.add(cbProductColor);
        formPanel.add(new JLabel("Size"));
        formPanel.add(cbProductSize);

        formPanel.add(new JLabel("Số Lượng"));
        formPanel.add(txtProductQuantity);
        formPanel.add(new JLabel("Giá Bán"));
        formPanel.add(txtProductPrice);

        formPanel.add(new JLabel("Giá Nhập"));
        formPanel.add(txtProductImportPrice);

        btnProductAdd = new ButtonCustom();
        btnProductEdit = new ButtonCustom();
        btnProductDelete = new ButtonCustom();
        btnProductClear = new ButtonCustom();

        btnProductAdd.setText("Thêm");
        btnProductEdit.setText("Sửa");
        btnProductDelete.setText("Xóa");
        btnProductClear.setText("Làm Mới");

        formPanel.add(btnProductAdd);
        formPanel.add(btnProductEdit);
        formPanel.add(btnProductDelete);
        formPanel.add(btnProductClear);

        productPanelBorder.add(formPanel, "growx");

        tableProduct = new JTable();
        JScrollPane scrollPane = createCustomScrollPane(tableProduct);
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
                "insets 10, wrap 4",
                "[fill, grow]10[fill, grow]10[fill, grow]10[fill, grow]",
                "[]10[]10[]"));
        panel.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel(labelText);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel lblId = new JLabel("Id " + labelText.toLowerCase());
        JTextField txtId = new JTextField();
        txtId.setEditable(false);
        txtId.setPreferredSize(new Dimension(200, 30));

        JLabel lblName = new JLabel("Tên " + labelText.toLowerCase());
        JTextField txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(200, 30));

        switch (labelText) {
            case "Loại sản phẩm":
                txtTypeId = txtId;
                txtTypeName = txtName;
                break;
            case "Chất liệu":
                txtMaterialId = txtId;
                txtMaterialName = txtName;
                break;
            case "Màu sắc":
                txtColorId = txtId;
                txtColorName = txtName;
                break;
            case "Size":
                txtSizeId = txtId;
                txtSizeName = txtName;
                break;
            case "Thương hiệu":
                txtBrandId = txtId;
                txtBrandName = txtName;
                break;
        }


        ButtonCustom btnAdd, btnEdit, btnDelete, btnClear;

        switch (labelText) {
            case "Loại sản phẩm":
                btnTypeAdd = new ButtonCustom();
                btnTypeEdit = new ButtonCustom();
                btnTypeDelete = new ButtonCustom();
                btnTypeClear = new ButtonCustom();
                btnAdd = btnTypeAdd;
                btnEdit = btnTypeEdit;
                btnDelete = btnTypeDelete;
                btnClear = btnTypeClear;

                break;
            case "Chất liệu":
                btnMaterialAdd = new ButtonCustom();
                btnMaterialEdit = new ButtonCustom();
                btnMaterialDelete = new ButtonCustom();
                btnMaterialClear = new ButtonCustom();
                btnAdd = btnMaterialAdd;
                btnEdit = btnMaterialEdit;
                btnDelete = btnMaterialDelete;
                btnClear = btnMaterialClear;
                break;
            case "Màu sắc":
                btnColorAdd = new ButtonCustom();
                btnColorEdit = new ButtonCustom();
                btnColorDelete = new ButtonCustom();
                btnColorClear = new ButtonCustom();
                btnAdd = btnColorAdd;
                btnEdit = btnColorEdit;
                btnDelete = btnColorDelete;
                btnClear = btnColorClear;
                break;
            case "Size":
                btnSizeAdd = new ButtonCustom();
                btnSizeEdit = new ButtonCustom();
                btnSizeDelete = new ButtonCustom();
                btnSizeClear = new ButtonCustom();
                btnAdd = btnSizeAdd;
                btnEdit = btnSizeEdit;
                btnDelete = btnSizeDelete;
                btnClear = btnSizeClear;
                break;
            case "Thương hiệu":
                btnBrandAdd = new ButtonCustom();
                btnBrandEdit = new ButtonCustom();
                btnBrandDelete = new ButtonCustom();
                btnBrandClear = new ButtonCustom();
                btnAdd = btnBrandAdd;
                btnEdit = btnBrandEdit;
                btnDelete = btnBrandDelete;
                btnClear = btnBrandClear;
                break;
            default:
                btnAdd = new ButtonCustom();
                btnEdit = new ButtonCustom();
                btnDelete = new ButtonCustom();
                btnClear = new ButtonCustom();
        }

        btnAdd.setText("Thêm");
        btnEdit.setText("Sửa");
        btnDelete.setText("Xóa");
        btnClear.setText("Làm Mới");

        panel.add(lblTitle, "span 4, left");
        panel.add(lblId);
        panel.add(txtId);
        panel.add(btnAdd, "growx");
        panel.add(btnEdit, "growx");

        panel.add(lblName);
        panel.add(txtName);
        panel.add(btnDelete, "growx");
        panel.add(btnClear, "growx");

        String[] columns = {"ID", labelText};
        Object[][] data = {};
        DefaultTableModel model = new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    txtId.setText(table.getValueAt(selectedRow, 0).toString());
                    txtName.setText(table.getValueAt(selectedRow, 1).toString());
                }
            }
        });

        switch (labelText) {
            case "Loại sản phẩm":
                typeTable = table;
                break;
            case "Chất liệu":
                materialTable = table;
                break;
            case "Màu sắc":
                colorTable = table;
                break;
            case "Size":
                sizeTable = table;
                break;
            case "Thương hiệu":
                brandTable = table;
                break;
        }

        JScrollPane scrollPane = createCustomScrollPane(table);
        TableCustom.apply(scrollPane, TableCustom.TableType.DEFAULT);
        panel.add(scrollPane, "span 4, grow, push");

        return panel;
    }

    private JScrollPane createCustomScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setVerticalScrollBar(new ScrollbarCustom());
        scroll.setHorizontalScrollBar(new ScrollbarCustom());
        return scroll;
    }

    public void setProductFormData(Shoe shoe) {
        if (shoe == null) return;

        txtProductId.setText(String.valueOf(shoe.getId()));
        txtProductName.setText(shoe.getName());
        txtProductQuantity.setText(String.valueOf(shoe.getQuantity()));
        txtProductPrice.setText(CurrencyUtils.formatCurrency(shoe.getPrice()));
        txtProductImportPrice.setText(CurrencyUtils.formatCurrency(shoe.getImportPrice()));

        selectComboById(cbProductBrand, shoe.getBrandId());
        selectComboById(cbProductType, shoe.getTypeId());
        selectComboById(cbProductMaterial, shoe.getMaterialId());
        selectComboById(cbProductColor, shoe.getColorId());
        selectComboById(cbProductSize, shoe.getSizeId());

        String path = shoe.getImage();
        if (path != null && !path.isBlank()) {
            File f = new File(path);
            if (f.exists()) {
                imagePanel.setImage(f);
                selectedImageName = f.getAbsolutePath();
            } else {
                imagePanel.clearImage();
                selectedImageName = null;
            }
        } else {
            imagePanel.clearImage();
            selectedImageName = null;
        }

    }

    public Shoe getDataProductFromForm() {
        try {
            Shoe shoe = new Shoe();

            String idText = txtProductId.getText().trim();
            if (!idText.isEmpty()) {
                try {
                    shoe.setId(Integer.parseInt(idText));
                } catch (NumberFormatException ex) {
                    showError("ID không hợp lệ!");
                    txtProductId.requestFocus();
                    return null;
                }
            }

            String name = txtProductName.getText().trim();
            if (name.isEmpty()) {
                showError("Tên sản phẩm không được để trống!");
                txtProductName.requestFocus();
                return null;
            }
            shoe.setName(name);

            shoe.setBrandId(((Brand) cbProductBrand.getSelectedItem()).getId());
            shoe.setTypeId(((Type) cbProductType.getSelectedItem()).getId());
            shoe.setColorId(((com.zzz.quanlibangiay.entity.Color) cbProductColor.getSelectedItem()).getId());
            shoe.setMaterialId(((Material) cbProductMaterial.getSelectedItem()).getId());
            shoe.setSizeId(((Size) cbProductSize.getSelectedItem()).getId());

            String qtyText = txtProductQuantity.getText().trim();
            if (qtyText.isEmpty()) {
                showError("Số lượng không được để trống!");
                txtProductQuantity.requestFocus();
                return null;
            }
            int qty;
            try {
                qty = Integer.parseInt(qtyText);
                if (qty < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showError("Số lượng phải là số nguyên >= 0!");
                txtProductQuantity.requestFocus();
                return null;
            }
            shoe.setQuantity(qty);

            String priceText = txtProductPrice.getText().trim();
            if (priceText.isEmpty()) {
                showError("Giá bán không được để trống!");
                txtProductPrice.requestFocus();
                return null;
            }
            double price;
            try {
                price = CurrencyUtils.parseCurrency(priceText);
                if (price < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showError("Giá bán phải là số >= 0!");
                txtProductPrice.requestFocus();
                return null;
            }
            shoe.setPrice(price);

            String importText = txtProductImportPrice.getText().trim();
            if (importText.isEmpty()) {
                showError("Giá nhập không được để trống!");
                txtProductImportPrice.requestFocus();
                return null;
            }
            double importPrice;
            try {
                importPrice = CurrencyUtils.parseCurrency(importText);
                if (importPrice < 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                showError("Giá nhập phải là số >= 0!");
                txtProductImportPrice.requestFocus();
                return null;
            }
            shoe.setImportPrice(importPrice);

            if (selectedImageName != null) {
                shoe.setImage(selectedImageName);
            }

            return shoe;

        } catch (Exception e) {
            showError("Có lỗi xảy ra khi lấy dữ liệu form: " + e.getMessage());
            return null;
        }
    }

    public void clearProductForm() {
        txtProductId.setText("");
        txtProductName.setText("");
        cbProductBrand.setSelectedIndex(0);
        cbProductType.setSelectedIndex(0);
        cbProductColor.setSelectedIndex(0);
        cbProductMaterial.setSelectedIndex(0);
        cbProductSize.setSelectedIndex(0);
        txtProductQuantity.setText("");
        txtProductPrice.setText("");
        txtProductImportPrice.setText("");
        imagePanel.clearImage();
    }

    public void setProductTableData(Object[][] data) {
        String[] columnNames = new String[]{
                "Id", "Tên", "Loại", "Thương hiệu", "Chất liệu", "Màu sắc", "Kích thước", "Số lượng", "Giá bán", "Giá nhập"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableProduct.setModel(model);
    }

    public ShoeSearchCriteria getSearchCriteria() {
        ShoeSearchCriteria c = new ShoeSearchCriteria();

        String name = txtSearchName.getText().trim();
        c.setName(name.isEmpty() ? null : name);

        Brand b = (Brand) cbSearchBrand.getSelectedItem();
        c.setBrand((b != null && b.getId() != 0) ? b : null);

        Type t = (Type) cbSearchType.getSelectedItem();
        c.setType((t != null && t.getId() != 0) ? t : null);

        com.zzz.quanlibangiay.entity.Color co = (com.zzz.quanlibangiay.entity.Color) cbSearchColor.getSelectedItem();
        c.setColor((co != null && co.getId() != 0) ? co : null);

        Material m = (Material) cbSearchMaterial.getSelectedItem();
        c.setMaterial((m != null && m.getId() != 0) ? m : null);

        Size s = (Size) cbSearchSize.getSelectedItem();
        c.setSize((s != null && s.getId() != 0) ? s : null);

        String fromTxt = txtSearchPriceFrom.getText().trim();
        if (fromTxt.isEmpty()) {
            c.setPriceFrom(null);
        } else {
            try {
                c.setPriceFrom(CurrencyUtils.parseCurrency(fromTxt));
            } catch (NumberFormatException ex) {
                c.setPriceFrom(null);
            }
        }

        String toTxt = txtSearchPriceTo.getText().trim();
        if (toTxt.isEmpty()) {
            c.setPriceTo(null);
        } else {
            try {
                c.setPriceTo(CurrencyUtils.parseCurrency(toTxt));
            } catch (NumberFormatException ex) {
                c.setPriceTo(null);
            }
        }


        return c;
    }

    public void clearSearchAndSortForm() {
        txtSearchName.setText("");
        txtSearchPriceFrom.setText("");
        txtSearchPriceTo.setText("");

        if (cbSearchBrand.getItemCount() > 0) {
            cbSearchBrand.setSelectedIndex(0);
        }
        if (cbSearchType.getItemCount() > 0) {
            cbSearchType.setSelectedIndex(0);
        }
        if (cbSearchColor.getItemCount() > 0) {
            cbSearchColor.setSelectedIndex(0);
        }
        if (cbSearchMaterial.getItemCount() > 0) {
            cbSearchMaterial.setSelectedIndex(0);
        }
        if (cbSearchSize.getItemCount() > 0) {
            cbSearchSize.setSelectedIndex(0);
        }

        sortButtonGroup.setSelected(rdoIdAsc.getModel(), true);
    }

    public String getSelectedSortCriteria() {
        if (rdoIdAsc.isSelected()) return "ID_ASC";
        if (rdoIdDesc.isSelected()) return "ID_DESC";
        if (rdoNameAsc.isSelected()) return "NAME_ASC";
        if (rdoNameDesc.isSelected()) return "NAME_DESC";
        if (rdoPriceAsc.isSelected()) return "PRICE_ASC";
        if (rdoPriceDesc.isSelected()) return "PRICE_DESC";
        if (rdoImportPriceAsc.isSelected()) return "IMPORT_PRICE_ASC";
        if (rdoImportPriceDesc.isSelected()) return "IMPORT_PRICE_DESC";
        if (rdoQuantityAsc.isSelected()) return "QUANTITY_ASC";
        if (rdoQuantityDesc.isSelected()) return "QUANTITY_DESC";
        return "ID_ASC";
    }

    public void setTypeTableData(List<Type> types) {
        if (typeTable != null) {
            DefaultTableModel model = (DefaultTableModel) typeTable.getModel();
            model.setRowCount(0);
            for (Type type : types) {
                model.addRow(new Object[]{type.getId(), type.getName()});
            }
        }
    }

    public void setMaterialTableData(List<Material> materials) {
        if (materialTable != null) {
            DefaultTableModel model = (DefaultTableModel) materialTable.getModel();
            model.setRowCount(0);
            for (Material material : materials) {
                model.addRow(new Object[]{material.getId(), material.getName()});
            }
        }
    }

    public void setColorTableData(List<com.zzz.quanlibangiay.entity.Color> colors) {
        if (colorTable != null) {
            DefaultTableModel model = (DefaultTableModel) colorTable.getModel();
            model.setRowCount(0);
            for (com.zzz.quanlibangiay.entity.Color color : colors) {
                model.addRow(new Object[]{color.getId(), color.getName()});
            }
        }
    }

    public void setSizeTableData(List<Size> sizes) {
        if (sizeTable != null) {
            DefaultTableModel model = (DefaultTableModel) sizeTable.getModel();
            model.setRowCount(0);
            for (Size size : sizes) {
                model.addRow(new Object[]{size.getId(), size.getName()});
            }
        }
    }

    public void setBrandTableData(List<Brand> brands) {
        if (brandTable != null) {
            DefaultTableModel model = (DefaultTableModel) brandTable.getModel();
            model.setRowCount(0);
            for (Brand brand : brands) {
                model.addRow(new Object[]{brand.getId(), brand.getName()});
            }
        }
    }

    public Type getTypeFromForm() {
        if (txtTypeName.getText().trim().isEmpty()) {
            showError("Vui lòng nhập đầy đủ thông tin loại sản phẩm!");
            return null;
        }
        Type type = new Type();
        if (!txtTypeId.getText().trim().isEmpty()) {
            try {
                type.setId(Integer.parseInt(txtTypeId.getText().trim()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        type.setName(txtTypeName.getText().trim());
        return type;
    }

    public Material getMaterialFromForm() {
        if (txtMaterialName.getText().trim().isEmpty()) {
            showError("Vui lòng nhập tên chất liệu!");
            return null;
        }
        Material material = new Material();
        if (!txtMaterialId.getText().trim().isEmpty()) {
            try {
                material.setId(Integer.parseInt(txtMaterialId.getText().trim()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        material.setName(txtMaterialName.getText().trim());
        return material;
    }

    public com.zzz.quanlibangiay.entity.Color getColorFromForm() {
        if (txtColorName.getText().trim().isEmpty()) {
            showError("Vui lòng nhập tên màu sắc!");
            return null;
        }
        com.zzz.quanlibangiay.entity.Color color = new com.zzz.quanlibangiay.entity.Color();
        if (!txtColorId.getText().trim().isEmpty()) {
            try {
                color.setId(Integer.parseInt(txtColorId.getText().trim()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        color.setName(txtColorName.getText().trim());
        return color;
    }

    public Size getSizeFromForm() {
        if (txtSizeName.getText().trim().isEmpty()) {
            showError("Vui lòng nhập tên size!");
            return null;
        }
        Size size = new Size();
        if (!txtSizeId.getText().trim().isEmpty()) {
            try {
                size.setId(Integer.parseInt(txtSizeId.getText().trim()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        size.setName(txtSizeName.getText().trim());
        return size;
    }

    public Brand getBrandFromForm() {
        if (txtBrandName.getText().trim().isEmpty()) {
            showError("Vui lòng nhập tên thương hiệu!");
            return null;
        }
        Brand brand = new Brand();
        if (!txtBrandId.getText().trim().isEmpty()) {
            try {
                brand.setId(Integer.parseInt(txtBrandId.getText().trim()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        brand.setName(txtBrandName.getText().trim());
        return brand;
    }

    public void clearTypeForm() {
        txtTypeId.setText("");
        txtTypeName.setText("");
    }

    public void clearMaterialForm() {
        txtMaterialId.setText("");
        txtMaterialName.setText("");
    }

    public void clearColorForm() {
        txtColorId.setText("");
        txtColorName.setText("");
    }

    public void clearSizeForm() {
        txtSizeId.setText("");
        txtSizeName.setText("");
    }

    public void clearBrandForm() {
        txtBrandId.setText("");
        txtBrandName.setText("");
    }

    public int getSelectedTypeId() {
        if (typeTable.getSelectedRow() >= 0) {
            return Integer.parseInt(typeTable.getValueAt(typeTable.getSelectedRow(), 0).toString());
        }
        return -1;
    }

    public int getSelectedMaterialId() {
        if (materialTable.getSelectedRow() >= 0) {
            return Integer.parseInt(materialTable.getValueAt(materialTable.getSelectedRow(), 0).toString());
        }
        return -1;
    }

    public int getSelectedColorId() {
        if (colorTable.getSelectedRow() >= 0) {
            return Integer.parseInt(colorTable.getValueAt(colorTable.getSelectedRow(), 0).toString());
        }
        return -1;
    }

    public int getSelectedSizeId() {
        if (sizeTable.getSelectedRow() >= 0) {
            return Integer.parseInt(sizeTable.getValueAt(sizeTable.getSelectedRow(), 0).toString());
        }
        return -1;
    }

    public int getSelectedBrandId() {
        if (brandTable.getSelectedRow() >= 0) {
            return Integer.parseInt(brandTable.getValueAt(brandTable.getSelectedRow(), 0).toString());
        }
        return -1;
    }

    public void setBrandComboBoxData(List<Brand> brands) {
        DefaultComboBoxModel<Brand> model = new DefaultComboBoxModel<>();
        for (Brand brand : brands) {
            model.addElement(brand);
        }
        cbProductBrand.setModel(model);

        DefaultComboBoxModel<Brand> searchModel = new DefaultComboBoxModel<>();
        Brand allBrands = new Brand();
        allBrands.setId(0);
        allBrands.setName("Tất cả");
        searchModel.addElement(allBrands);
        for (Brand b : brands) {
            searchModel.addElement(b);
        }
        cbSearchBrand.setModel(searchModel);
        cbSearchBrand.setSelectedIndex(0);
    }

    public void setTypeComboBoxData(List<Type> types) {
        DefaultComboBoxModel<Type> productModel = new DefaultComboBoxModel<>();
        for (Type t : types) {
            productModel.addElement(t);
        }
        cbProductType.setModel(productModel);

        DefaultComboBoxModel<Type> searchModel = new DefaultComboBoxModel<>();
        Type allType = new Type();
        allType.setId(0);
        allType.setName("Tất cả");
        searchModel.addElement(allType);
        for (Type t : types) {
            searchModel.addElement(t);
        }
        cbSearchType.setModel(searchModel);
        cbSearchType.setSelectedIndex(0);
    }

    public void setMaterialComboBoxData(List<Material> materials) {
        DefaultComboBoxModel<Material> productModel = new DefaultComboBoxModel<>();
        for (Material m : materials) {
            productModel.addElement(m);
        }
        cbProductMaterial.setModel(productModel);

        DefaultComboBoxModel<Material> searchModel = new DefaultComboBoxModel<>();
        Material allMat = new Material();
        allMat.setId(0);
        allMat.setName("Tất cả");
        searchModel.addElement(allMat);
        for (Material m : materials) {
            searchModel.addElement(m);
        }
        cbSearchMaterial.setModel(searchModel);
        cbSearchMaterial.setSelectedIndex(0);
    }

    public void setColorComboBoxData(List<com.zzz.quanlibangiay.entity.Color> colors) {
        DefaultComboBoxModel<com.zzz.quanlibangiay.entity.Color> productModel = new DefaultComboBoxModel<>();
        for (com.zzz.quanlibangiay.entity.Color c : colors) {
            productModel.addElement(c);
        }
        cbProductColor.setModel(productModel);

        DefaultComboBoxModel<com.zzz.quanlibangiay.entity.Color> searchModel = new DefaultComboBoxModel<>();
        com.zzz.quanlibangiay.entity.Color allColor =
                new com.zzz.quanlibangiay.entity.Color();
        allColor.setId(0);
        allColor.setName("Tất cả");
        searchModel.addElement(allColor);
        for (com.zzz.quanlibangiay.entity.Color c : colors) {
            searchModel.addElement(c);
        }
        cbSearchColor.setModel(searchModel);
        cbSearchColor.setSelectedIndex(0);
    }

    public void setSizeComboBoxData(List<Size> sizes) {
        DefaultComboBoxModel<Size> productModel = new DefaultComboBoxModel<>();
        for (Size s : sizes) {
            productModel.addElement(s);
        }
        cbProductSize.setModel(productModel);

        DefaultComboBoxModel<Size> searchModel = new DefaultComboBoxModel<>();
        Size allSize = new Size();
        allSize.setId(0);
        allSize.setName("Tất cả");
        searchModel.addElement(allSize);
        for (Size s : sizes) {
            searchModel.addElement(s);
        }
        cbSearchSize.setModel(searchModel);
        cbSearchSize.setSelectedIndex(0);
    }

    private <T extends AbstractEntity> void selectComboById(ComboBoxSuggestion<T> combo, int id) {
        ComboBoxModel<T> model = combo.getModel();
        for (int i = 0; i < model.getSize(); i++) {
            T item = model.getElementAt(i);
            if (item.getId() == id) {
                combo.setSelectedIndex(i);
                return;
            }
        }
    }

    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccess(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
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


    // Search Panel Listeners
    public void addSearchProductListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }

    public void addResetSearchListener(ActionListener listener) {
        btnReset.addActionListener(listener);
    }

    // Product Panel Listeners
    public void addAddProductListener(ActionListener listener) {
        btnProductAdd.addActionListener(listener);
    }

    public void addEditProductListener(ActionListener listener) {
        btnProductEdit.addActionListener(listener);
    }

    public void addDeleteProductListener(ActionListener listener) {
        btnProductDelete.addActionListener(listener);
    }

    public void addClearProductListener(ActionListener listener) {
        btnProductClear.addActionListener(listener);
    }

    // Product Type Attribute Listeners
    public void addAddTypeListener(ActionListener listener) {
        btnTypeAdd.addActionListener(listener);
    }

    public void addEditTypeListener(ActionListener listener) {
        btnTypeEdit.addActionListener(listener);
    }

    public void addDeleteTypeListener(ActionListener listener) {
        btnTypeDelete.addActionListener(listener);
    }

    public void addClearTypeListener(ActionListener listener) {
        btnTypeClear.addActionListener(listener);
    }

    // Material Attribute Listeners
    public void addAddMaterialListener(ActionListener listener) {
        btnMaterialAdd.addActionListener(listener);
    }

    public void addEditMaterialListener(ActionListener listener) {
        btnMaterialEdit.addActionListener(listener);
    }

    public void addDeleteMaterialListener(ActionListener listener) {
        btnMaterialDelete.addActionListener(listener);
    }

    public void addClearMaterialListener(ActionListener listener) {
        btnMaterialClear.addActionListener(listener);
    }

    // Color Attribute Listeners
    public void addAddColorListener(ActionListener listener) {
        btnColorAdd.addActionListener(listener);
    }

    public void addEditColorListener(ActionListener listener) {
        btnColorEdit.addActionListener(listener);
    }

    public void addDeleteColorListener(ActionListener listener) {
        btnColorDelete.addActionListener(listener);
    }

    public void addClearColorListener(ActionListener listener) {
        btnColorClear.addActionListener(listener);
    }

    // Size Attribute Listeners
    public void addAddSizeListener(ActionListener listener) {
        btnSizeAdd.addActionListener(listener);
    }

    public void addEditSizeListener(ActionListener listener) {
        btnSizeEdit.addActionListener(listener);
    }

    public void addDeleteSizeListener(ActionListener listener) {
        btnSizeDelete.addActionListener(listener);
    }

    public void addClearSizeListener(ActionListener listener) {
        btnSizeClear.addActionListener(listener);
    }

    // Brand Attribute Listeners
    public void addAddBrandListener(ActionListener listener) {
        btnBrandAdd.addActionListener(listener);
    }

    public void addEditBrandListener(ActionListener listener) {
        btnBrandEdit.addActionListener(listener);
    }

    public void addDeleteBrandListener(ActionListener listener) {
        btnBrandDelete.addActionListener(listener);
    }

    public void addClearBrandListener(ActionListener listener) {
        btnBrandClear.addActionListener(listener);
    }

    // Radio Button Listeners
    public void addSortListener(ActionListener listener) {
        rdoIdAsc.addActionListener(listener);
        rdoIdDesc.addActionListener(listener);
        rdoNameAsc.addActionListener(listener);
        rdoNameDesc.addActionListener(listener);
        rdoPriceAsc.addActionListener(listener);
        rdoPriceDesc.addActionListener(listener);
        rdoImportPriceAsc.addActionListener(listener);
        rdoImportPriceDesc.addActionListener(listener);
        rdoQuantityAsc.addActionListener(listener);
        rdoQuantityDesc.addActionListener(listener);
    }

    // Table Product Listeners
    public void addProductTableSelectionListener(ListSelectionListener listener) {
        tableProduct.getSelectionModel().addListSelectionListener(listener);
    }

    public JTable getProductTable() {
        return tableProduct;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
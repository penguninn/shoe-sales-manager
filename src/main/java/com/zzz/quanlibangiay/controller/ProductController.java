package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.*;
import com.zzz.quanlibangiay.entity.*;
import com.zzz.quanlibangiay.utils.CurrencyUtils;
import com.zzz.quanlibangiay.view.ProductView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductController {

    private ProductView view;
    private ManageShoe manageShoe;
    private ManageBrand manageBrand;
    private ManageShoeType manageShoeType;
    private ManageColor manageColor;
    private ManageMaterial manageMaterial;
    private ManageSize manageSize;

    public ProductController(ProductView view) {
        this.view = view;
        this.manageShoe = new ManageShoe();
        this.manageBrand = new ManageBrand();
        this.manageShoeType = new ManageShoeType();
        this.manageColor = new ManageColor();
        this.manageMaterial = new ManageMaterial();
        this.manageSize = new ManageSize();
        initListeners();
        initializeData();
    }

    private void initListeners() {
        view.addSearchProductListener(new SearchProductListener());
        view.addResetSearchListener(new ResetSearchListener());

        view.addAddProductListener(new AddProductListener());
        view.addEditProductListener(new EditProductListener());
        view.addDeleteProductListener(new DeleteProductListener());
        view.addClearProductListener(new ClearProductListener());

        view.addAddTypeListener(new AddTypeListener());
        view.addEditTypeListener(new EditTypeListener());
        view.addDeleteTypeListener(new DeleteTypeListener());
        view.addClearTypeListener(new ClearTypeListener());

        view.addAddMaterialListener(new AddMaterialListener());
        view.addEditMaterialListener(new EditMaterialListener());
        view.addDeleteMaterialListener(new DeleteMaterialListener());
        view.addClearMaterialListener(new ClearMaterialListener());

        view.addAddColorListener(new AddColorListener());
        view.addEditColorListener(new EditColorListener());
        view.addDeleteColorListener(new DeleteColorListener());
        view.addClearColorListener(new ClearColorListener());

        view.addAddSizeListener(new AddSizeListener());
        view.addEditSizeListener(new EditSizeListener());
        view.addDeleteSizeListener(new DeleteSizeListener());
        view.addClearSizeListener(new ClearSizeListener());

        view.addAddBrandListener(new AddBrandListener());
        view.addEditBrandListener(new EditBrandListener());
        view.addDeleteBrandListener(new DeleteBrandListener());
        view.addClearBrandListener(new ClearBrandListener());

        view.addSortListener(new SortListener());
        view.addProductTableSelectionListener(new ProductTableSelectionListener());
    }

    private void initializeData() {
        loadBrandData();
        loadTypeData();
        loadMaterialData();
        loadColorData();
        loadSizeData();
        loadProductTableData();

        loadTypeTableData();
        loadMaterialTableData();
        loadColorTableData();
        loadSizeTableData();
        loadBrandTableData();

    }

    private void loadProductTableData() {
        try {
            List<Shoe> shoes = manageShoe.getAllShoes();
            Object[][] productData = toTableData(shoes);
            view.setProductTableData(productData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading product data: " + e.getMessage());
        }
    }

    private void loadTypeTableData() {
        try {
            List<ShoeType> types = manageShoeType.getAllShoeTypes();
            view.setTypeTableData(types);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading type table data: " + e.getMessage());
        }
    }

    private void loadMaterialTableData() {
        try {
            List<Material> materials = manageMaterial.getAllMaterials();
            view.setMaterialTableData(materials);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading material table data: " + e.getMessage());
        }
    }

    private void loadColorTableData() {
        try {
            List<Color> colors = manageColor.getAllColors();
            view.setColorTableData(colors);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading color table data: " + e.getMessage());
        }
    }

    private void loadSizeTableData() {
        try {
            List<Size> sizes = manageSize.getAllSizes();
            view.setSizeTableData(sizes);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading size table data: " + e.getMessage());
        }
    }

    private void loadBrandTableData() {
        try {
            List<Brand> brands = manageBrand.getAllBrands();
            view.setBrandTableData(brands);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading brand table data: " + e.getMessage());
        }
    }

    private void loadBrandData() {
        try {
            List<Brand> brands = manageBrand.getAllBrands();
            view.setBrandComboBoxData(brands);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading brand list: " + e.getMessage());
        }
    }

    private void loadTypeData() {
        try {
            List<ShoeType> types = manageShoeType.getAllShoeTypes();
            view.setTypeComboBoxData(types);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading type list: " + e.getMessage());
        }
    }

    private void loadMaterialData() {
        try {
            List<Material> materials = manageMaterial.getAllMaterials();
            view.setMaterialComboBoxData(materials);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading material list: " + e.getMessage());
        }
    }

    private void loadColorData() {
        try {
            List<Color> colors = manageColor.getAllColors();
            view.setColorComboBoxData(colors);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading color list: " + e.getMessage());
        }
    }

    private void loadSizeData() {
        try {
            List<Size> sizes = manageSize.getAllSizes();
            view.setSizeComboBoxData(sizes);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading size list: " + e.getMessage());
        }
    }

    private Object[][] toTableData(List<Shoe> shoes) {
        Object[][] data = new Object[shoes.size()][10];
        for (int i = 0; i < shoes.size(); i++) {
            Shoe s = shoes.get(i);
            data[i][0] = s.getId();
            data[i][1] = s.getName();
            data[i][2] = manageShoeType.getShoeTypeById(s.getTypeId()).getName();
            data[i][3] = manageBrand.getBrandById(s.getBrandId()).getName();
            data[i][4] = manageMaterial.getMaterialById(s.getMaterialId()).getName();
            data[i][5] = manageColor.getColorById(s.getColorId()).getName();
            data[i][6] = manageSize.getSizeById(s.getSizeId()).getSizeName();
            data[i][7] = s.getQuantity();
            data[i][8] = CurrencyUtils.formatCurrency(s.getPrice());
            data[i][9] = CurrencyUtils.formatCurrency(s.getImportPrice());
        }

        Object[][] formatted = new Object[shoes.size()][10];
        for (int i = 0; i < data.length; i++) {
            System.arraycopy(data[i], 0, formatted[i], 0, 8);
            if (data[i][8] instanceof Number) {
                formatted[i][8] = CurrencyUtils.formatCurrency(
                        ((Number) data[i][8]).doubleValue()
                );
            } else {
                formatted[i][8] = data[i][8];
            }
            if (data[i].length > 9 && data[i][9] instanceof Number) {
                formatted[i][9] = CurrencyUtils.formatCurrency(
                        ((Number) data[i][9]).doubleValue()
                );
            } else if (data[i].length > 9) {
                formatted[i][9] = data[i][9];
            }
        }
        return formatted;
    }

    class SortListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShoeSearchCriteria criteria = view.getSearchCriteria();
            String sortBy = view.getSelectedSortCriteria();
            List<Shoe> sortedShoes = manageShoe.searchAndSort(criteria, sortBy);
            Object[][] data = toTableData(sortedShoes);
            view.setProductTableData(data);
        }
    }

    class ProductTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = view.getProductTable().getSelectedRow();
                if (selectedRow >= 0) {
                    int modelRow = view.getProductTable().convertRowIndexToModel(selectedRow);
                    Object value = view.getProductTable().getModel().getValueAt(modelRow, 0);
                    if (value != null) {
                        int id = Integer.parseInt(value.toString());
                        Shoe shoe = manageShoe.getShoeById(id);
                        if (shoe != null) {
                            view.setProductFormData(shoe);
                        }
                    }
                }
            }
        }
    }

    class SearchProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShoeSearchCriteria criteria = view.getSearchCriteria();
            String sortBy = view.getSelectedSortCriteria();
            List<Shoe> sortedShoes = manageShoe.searchAndSort(criteria, sortBy);
            Object[][] data = toTableData(sortedShoes);
            view.setProductTableData(data);
        }
    }

    class ResetSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearSearchAndSortForm();
            loadProductTableData();
        }
    }

    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Shoe shoe = view.getDataProductFromForm();
            if (shoe == null) return;

            boolean ok = manageShoe.addShoe(shoe);
            if (ok) {
                view.showSuccess("Thêm sản phẩm thành công!");
            } else {
                view.showError("Thêm sản phẩm thất bại!");
            }

            loadProductTableData();
            view.clearProductForm();
        }
    }

    class EditProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Shoe shoe = view.getDataProductFromForm();
            if (shoe == null) return;

            boolean ok = manageShoe.updateShoe(shoe);
            if (ok) {
                view.showSuccess("Cập nhật sản phẩm thành công!");
            } else {
                view.showError("Cập nhật sản phẩm thất bại!");
            }

            loadProductTableData();
            view.clearProductForm();
        }


    }

    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa sản phẩm này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageShoe.deleteShoe(view.getDataProductFromForm().getId());
            if (ok) {
                view.showSuccess("Xóa sản phẩm thành công!");
            } else {
                view.showError("Xóa sản phẩm thất bại!");
            }

            loadProductTableData();
            view.clearProductForm();
        }
    }

    class ClearProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearProductForm();
        }
    }

    class AddTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShoeType type = view.getTypeFromForm();
            if (type == null) return;

            boolean ok = manageShoeType.addShoeType(type);
            if (ok) {
                view.showSuccess("Thêm loại sản phẩm thành công!");
                loadTypeTableData();
                loadTypeData();
                loadProductTableData();
                view.clearTypeForm();
            } else {
                view.showError("Thêm loại sản phẩm thất bại!");
            }
        }
    }

    class EditTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShoeType type = view.getTypeFromForm();
            if (type == null) return;

            if (type.getId() == 0) {
                view.showError("Vui lòng chọn loại sản phẩm từ danh sách để sửa!");
                return;
            }

            boolean ok = manageShoeType.updateShoeType(type);
            if (ok) {
                view.showSuccess("Cập nhật loại sản phẩm thành công!");
                loadTypeTableData();
                loadTypeData();
                loadProductTableData();
                view.clearTypeForm();
            } else {
                view.showError("Cập nhật loại sản phẩm thất bại!");
            }
        }
    }

    class DeleteTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedTypeId();
            if (selectedId == -1) {
                view.showError("Vui lòng chọn loại sản phẩm để xóa!");
                return;
            }

            if (manageShoe.isTypeInUse(selectedId)) {
                view.showError("Không thể xóa loại sản phẩm vì đang được sử dụng trong sản phẩm!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa loại sản phẩm này không? ",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageShoeType.deleteShoeType(selectedId);
            if (ok) {
                view.showSuccess("Xóa loại sản phẩm thành công!");
                loadTypeTableData();
                loadTypeData();
                loadProductTableData();
                view.clearTypeForm();
            } else {
                view.showError("Xóa loại sản phẩm thất bại!");
            }
        }
    }

    class ClearTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearTypeForm();
        }
    }

    // Material Attribute Listeners
    class AddMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Material material = view.getMaterialFromForm();
            if (material == null) return;

            boolean ok = manageMaterial.addMaterial(material);
            if (ok) {
                view.showSuccess("Thêm chất liệu thành công!");
                loadMaterialTableData();
                loadMaterialData();
                loadProductTableData();
                view.clearMaterialForm();
            } else {
                view.showError("Thêm chất liệu thất bại!");
            }
        }
    }

    class EditMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Material material = view.getMaterialFromForm();
            if (material == null) return;

            if (material.getId() == 0) {
                view.showError("Vui lòng chọn chất liệu từ danh sách để sửa!");
                return;
            }

            boolean ok = manageMaterial.updateMaterial(material);
            if (ok) {
                view.showSuccess("Cập nhật chất liệu thành công!");
                loadMaterialTableData();
                loadMaterialData();
                loadProductTableData();
                view.clearMaterialForm();
            } else {
                view.showError("Cập nhật chất liệu thất bại!");
            }
        }
    }

    class DeleteMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedMaterialId();
            if (selectedId == -1) {
                view.showError("Vui lòng chọn chất liệu để xóa!");
                return;
            }

            if (manageShoe.isMaterialInUse(selectedId)) {
                view.showError("Không thể xóa chất liệu vì đang được sử dụng trong sản phẩm!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa chất liệu này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageMaterial.deleteMaterial(selectedId);
            if (ok) {
                view.showSuccess("Xóa chất liệu thành công!");
                loadMaterialTableData();
                loadMaterialData();
                loadProductTableData();
                view.clearMaterialForm();
            } else {
                view.showError("Xóa chất liệu thất bại!");
            }
        }
    }

    class ClearMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearMaterialForm();
        }
    }

    // Color Attribute Listeners
    class AddColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = view.getColorFromForm();
            if (color == null) return;

            boolean ok = manageColor.addColor(color);
            if (ok) {
                view.showSuccess("Thêm màu sắc thành công!");
                loadColorTableData();
                loadColorData();
                loadProductTableData();
                view.clearColorForm();
            } else {
                view.showError("Thêm màu sắc thất bại!");
            }
        }
    }

    class EditColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Color color = view.getColorFromForm();
            if (color == null) return;

            if (color.getId() == 0) {
                view.showError("Vui lòng chọn màu sắc từ danh sách để sửa!");
                return;
            }

            boolean ok = manageColor.updateColor(color);
            if (ok) {
                view.showSuccess("Cập nhật màu sắc thành công!");
                loadColorTableData();
                loadColorData();
                loadProductTableData();
                view.clearColorForm();
            } else {
                view.showError("Cập nhật màu sắc thất bại!");
            }
        }
    }

    class DeleteColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedColorId();
            if (selectedId == -1) {
                view.showError("Vui lòng chọn màu sắc để xóa!");
                return;
            }
            if (manageShoe.isColorInUse(selectedId)) {
                view.showError("Không thể xóa màu sắc vì đang được sử dụng trong sản phẩm!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa màu sắc này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageColor.deleteColor(selectedId);
            if (ok) {
                view.showSuccess("Xóa màu sắc thành công!");
                loadColorTableData();
                loadColorData();
                loadProductTableData();
                view.clearColorForm();
            } else {
                view.showError("Xóa màu sắc thất bại!");
            }
        }
    }

    class ClearColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearColorForm();
        }
    }

    // Size Attribute Listeners
    class AddSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Size size = view.getSizeFromForm();
            if (size == null) return;

            boolean ok = manageSize.addSize(size);
            if (ok) {
                view.showSuccess("Thêm size thành công!");
                loadSizeTableData();
                loadSizeData();
                loadProductTableData();
                view.clearSizeForm();
            } else {
                view.showError("Thêm size thất bại!");
            }
        }
    }

    class EditSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Size size = view.getSizeFromForm();
            if (size == null) return;

            if (size.getId() == 0) {
                view.showError("Vui lòng chọn size từ danh sách để sửa!");
                return;
            }

            boolean ok = manageSize.updateSize(size);
            if (ok) {
                view.showSuccess("Cập nhật size thành công!");
                loadSizeTableData();
                loadSizeData();
                loadProductTableData();
                view.clearSizeForm();
            } else {
                view.showError("Cập nhật size thất bại!");
            }
        }
    }

    class DeleteSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedSizeId();
            if (selectedId == -1) {
                view.showError("Vui lòng chọn size để xóa!");
                return;
            }

            if (manageShoe.isSizeInUse(selectedId)) {
                view.showError("Không thể xóa size vì đang được sử dụng trong sản phẩm!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa size này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageSize.deleteSize(selectedId);
            if (ok) {
                view.showSuccess("Xóa size thành công!");
                loadSizeTableData();
                loadSizeData();
                loadProductTableData();
                view.clearSizeForm();
            } else {
                view.showError("Xóa size thất bại!");
            }
        }
    }

    class ClearSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearSizeForm();
        }
    }

    // Brand Attribute Listeners
    class AddBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Brand brand = view.getBrandFromForm();
            if (brand == null) return;

            boolean ok = manageBrand.addBrand(brand);
            if (ok) {
                view.showSuccess("Thêm thương hiệu thành công!");
                loadBrandTableData();
                loadBrandData();
                loadProductTableData();
                view.clearBrandForm();
            } else {
                view.showError("Thêm thương hiệu thất bại!");
            }
        }
    }

    class EditBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Brand brand = view.getBrandFromForm();
            if (brand == null) return;

            if (brand.getId() == 0) {
                view.showError("Vui lòng chọn thương hiệu từ danh sách để sửa!");
                return;
            }

            boolean ok = manageBrand.updateBrand(brand);
            if (ok) {
                view.showSuccess("Cập nhật thương hiệu thành công!");
                loadBrandTableData();
                loadBrandData();
                loadProductTableData();
                view.clearBrandForm();
            } else {
                view.showError("Cập nhật thương hiệu thất bại!");
            }
        }
    }

    class DeleteBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedBrandId();
            if (selectedId == -1) {
                view.showError("Vui lòng chọn thương hiệu để xóa!");
                return;
            }

            if (manageShoe.isBrandInUse(selectedId)) {
                view.showError("Không thể xóa thương hiệu vì đang được sử dụng trong sản phẩm!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    null,
                    "Bạn có chắc muốn xóa thương hiệu này không?",
                    "Xác nhận",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm != JOptionPane.YES_OPTION) return;

            boolean ok = manageBrand.deleteBrand(selectedId);
            if (ok) {
                view.showSuccess("Xóa thương hiệu thành công!");
                loadBrandTableData();
                loadBrandData();
                loadProductTableData();
                view.clearBrandForm();
            } else {
                view.showError("Xóa thương hiệu thất bại!");
            }
        }
    }

    class ClearBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearBrandForm();
        }
    }

}
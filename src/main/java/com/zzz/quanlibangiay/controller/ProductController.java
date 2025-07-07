package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.view.ProductView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductController {

    private ProductView productView;

    public ProductController(ProductView view) {
        this.productView = view;
        initListeners();
    }

    private void initListeners() {
        productView.addSearchProductListener(new SearchProductListener());
        productView.addResetSearchListener(new ResetSearchListener());

        productView.addAddProductListener(new AddProductListener());
        productView.addEditProductListener(new EditProductListener());
        productView.addDeleteProductListener(new DeleteProductListener());
        productView.addClearProductListener(new ClearProductListener());

        productView.addAddTypeListener(new AddTypeListener());
        productView.addEditTypeListener(new EditTypeListener());
        productView.addDeleteTypeListener(new DeleteTypeListener());
        productView.addClearTypeListener(new ClearTypeListener());

        productView.addAddMaterialListener(new AddMaterialListener());
        productView.addEditMaterialListener(new EditMaterialListener());
        productView.addDeleteMaterialListener(new DeleteMaterialListener());
        productView.addClearMaterialListener(new ClearMaterialListener());

        productView.addAddColorListener(new AddColorListener());
        productView.addEditColorListener(new EditColorListener());
        productView.addDeleteColorListener(new DeleteColorListener());
        productView.addClearColorListener(new ClearColorListener());

        productView.addAddSizeListener(new AddSizeListener());
        productView.addEditSizeListener(new EditSizeListener());
        productView.addDeleteSizeListener(new DeleteSizeListener());
        productView.addClearSizeListener(new ClearSizeListener());

        productView.addAddBrandListener(new AddBrandListener());
        productView.addEditBrandListener(new EditBrandListener());
        productView.addDeleteBrandListener(new DeleteBrandListener());
        productView.addClearBrandListener(new ClearBrandListener());
    }


    // Search Panel Listeners
    class SearchProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Search product clicked");
        }
    }

    class ResetSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Reset search clicked");
        }
    }

    // Product Panel Listeners
    class AddProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add product clicked");
        }
    }

    class EditProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit product clicked");
        }
    }

    class DeleteProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement delete product logic
            System.out.println("Delete product clicked");
        }
    }

    class ClearProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Implement clear form logic
            System.out.println("Clear product form clicked");
        }
    }

    // Type Attribute Listeners
    class AddTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add type clicked");
        }
    }

    class EditTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit type clicked");
        }
    }

    class DeleteTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Delete type clicked");
        }
    }

    class ClearTypeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clear type clicked");
        }
    }

    // Material Attribute Listeners
    class AddMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add material clicked");
        }
    }

    class EditMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit material clicked");
        }
    }

    class DeleteMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Delete material clicked");
        }
    }

    class ClearMaterialListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clear material clicked");
        }
    }

    // Color Attribute Listeners
    class AddColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add color clicked");
        }
    }

    class EditColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit color clicked");
        }
    }

    class DeleteColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Delete color clicked");
        }
    }

    class ClearColorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clear color clicked");
        }
    }

    // Size Attribute Listeners
    class AddSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add size clicked");
        }
    }

    class EditSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit size clicked");
        }
    }

    class DeleteSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Delete size clicked");
        }
    }

    class ClearSizeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clear size clicked");
        }
    }

    // Brand Attribute Listeners
    class AddBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add brand clicked");
        }
    }

    class EditBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Edit brand clicked");
        }
    }

    class DeleteBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Delete brand clicked");
        }
    }

    class ClearBrandListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Clear brand clicked");
        }
    }
}
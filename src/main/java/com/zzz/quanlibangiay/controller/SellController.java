package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.*;
import com.zzz.quanlibangiay.entity.*;
import com.zzz.quanlibangiay.enums.OrderStatus;
import com.zzz.quanlibangiay.enums.PaymentMethod;
import com.zzz.quanlibangiay.utils.CurrencyUtils;
import com.zzz.quanlibangiay.utils.DateUtils;
import com.zzz.quanlibangiay.utils.ValidationUtils;
import com.zzz.quanlibangiay.view.AddToCartDialog;
import com.zzz.quanlibangiay.view.CustomerSelectDialog;
import com.zzz.quanlibangiay.view.SellView;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class SellController {

    private CustomerSelectDialog dialog;
    private SellView sellView;
    private ManageCustomer manageCustomer;
    private ManageOrder manageOrder;
    private ManageShoe manageShoe;
    private ManageOrderItem manageOrderItem;
    private ManageType manageType;
    private ManageBrand manageBrand;
    private ManageSize manageSize;
    private ManageColor manageColor;
    private ManageMaterial manageMaterial;
    private ManageUser manageUser;
    private User currentUser;

    public SellController(
            SellView view, User currentUser,
            ManageCustomer manageCustomer, ManageOrder manageOrder,
            ManageShoe manageShoe, ManageOrderItem manageOrderItem,
            ManageType manageType, ManageBrand manageBrand,
            ManageSize manageSize, ManageColor manageColor,
            ManageMaterial manageMaterial, ManageUser manageUser) {
        this.sellView = view;
        this.currentUser = currentUser;
        this.manageCustomer = manageCustomer;
        this.manageOrder = manageOrder;
        this.manageShoe = manageShoe;
        this.manageOrderItem = manageOrderItem;
        this.manageType = manageType;
        this.manageBrand = manageBrand;
        this.manageSize = manageSize;
        this.manageColor = manageColor;
        this.manageMaterial = manageMaterial;
        this.manageUser = manageUser;
        initListeners();
        initData();
    }

    private void initListeners() {
        sellView.addCreateBillListener(new CreateBillListener());
        sellView.addUpdateCustomerListener(new UpdateCustomerListener());
        sellView.addPaymentListener(new PaymentListener());
        sellView.addCancelBillListener(new CancelBillListener());
        sellView.addRefreshListener(new RefreshListener());

        sellView.addProductMouseListener(new ProductMouseListener());
        sellView.addPendingBillSelectionListener(new PendingBillSelectionListener());
        sellView.addCartSelectionListener(new CartSelectionListener());

        sellView.addEditCartListener(new EditCartListener());
        sellView.addDeleteCartListener(new DeleteCartListener());
        sellView.addDeleteAllCartListener(new DeleteAllCartListener());
    }

    public void initData() {
        loadPendingBills();
        loadProducts();
    }

    public void loadPendingBills() {
        List<Order> pendingBills = manageOrder.getAllPendingOrders();
        System.out.println("Total orders: " + pendingBills.size());
        Object[][] data = toPendingBillTableData(pendingBills);
        sellView.setPendingBills(data);
    }

    public void loadCartItems(int orderId) {
        List<OrderItem> cartItems = manageOrderItem.getByOrderId(orderId);
        System.out.println("Total cart items: " + cartItems.size());
        Object[][] data = toCartTableData(cartItems);
        sellView.setCartItems(data);
    }

    public void loadProducts() {
        List<Shoe> products = manageShoe.getAllShoes();
        Object[][] data = toProductTableData(products);
        sellView.setProducts(data);
    }

    private Object[][] toPendingBillTableData(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new Object[0][5];
        }
        Object[][] data = new Object[orders.size()][5];
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            data[i][0] = order.getId();
            data[i][1] = manageCustomer.getCustomerById(order.getCustomerId()).getName();
            data[i][2] = manageUser.getUserById(order.getStaffId()).getFullName();
            data[i][3] = CurrencyUtils.formatCurrency(order.getTotalAmount());
            data[i][4] = DateUtils.formatDateToShort(order.getCreatedDate());
        }
        return data;
    }

    private Object[][] toCartTableData(List<OrderItem> details) {
        if (details == null || details.isEmpty()) {
            return new Object[0][5];
        }
        Object[][] data = new Object[details.size()][5];
        for (int i = 0; i < details.size(); i++) {
            OrderItem item = details.get(i);
            data[i][0] = item.getProductId();
            data[i][1] = manageShoe.getShoeById(item.getProductId()).getName();
            data[i][2] = CurrencyUtils.formatCurrency(item.getPrice());
            data[i][3] = item.getQuantity();
            data[i][4] = CurrencyUtils.formatCurrency(item.getPrice() * item.getQuantity());
        }
        return data;
    }

    private Object[][] toProductTableData(List<Shoe> products) {
        if (products == null || products.isEmpty()) {
            return new Object[0][9];
        }
        Object[][] data = new Object[products.size()][9];
        for (int i = 0; i < products.size(); i++) {
            Shoe shoe = products.get(i);
            data[i][0] = shoe.getId();
            data[i][1] = shoe.getName();
            data[i][2] = manageType.getShoeTypeById(shoe.getTypeId()).getName();
            data[i][3] = manageBrand.getBrandById(shoe.getBrandId()).getName();
            data[i][4] = manageColor.getColorById(shoe.getColorId()).getName();
            data[i][5] = manageMaterial.getMaterialById(shoe.getMaterialId()).getName();
            data[i][6] = manageSize.getSizeById(shoe.getSizeId()).getName();
            data[i][7] = CurrencyUtils.formatCurrency(shoe.getPrice());
            data[i][8] = shoe.getQuantity();
        }
        return data;

    }

    private void onProductDoubleClick() {
        int selRow = sellView.getPendingBillTable().getSelectedRow();
        if (selRow < 0) {
            sellView.showError("Vui lòng chọn hóa đơn trước khi thêm sản phẩm");
            return;
        }
        int modelRow = sellView.getPendingBillTable().convertRowIndexToModel(selRow);
        int orderId = (int) sellView.getPendingBillTable().getModel().getValueAt(modelRow, 0);

        int prodRow = sellView.getProductTable().getSelectedRow();
        if (prodRow < 0) return;
        int prodModelRow = sellView.getProductTable().convertRowIndexToModel(prodRow);
        int productId = (int) sellView.getProductTable().getModel().getValueAt(prodModelRow, 0);
        Shoe shoe = manageShoe.getShoeById(productId);

        if (shoe.getQuantity() <= 0) {
            sellView.showError("Sản phẩm '" + shoe.getName() + "' đã hết hàng.");
            return;
        }

        AddToCartDialog dlg = new AddToCartDialog(
                (JFrame) SwingUtilities.getWindowAncestor(sellView),
                shoe.getName(),
                shoe.getQuantity()
        );
        dlg.setVisible(true);

        int qty = dlg.getQuantity();
        if (qty <= 0) return;

        OrderItem existing = manageOrderItem.findByOrderIdAndProductId(orderId, productId);
        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + qty);
            manageOrderItem.update(existing);
        } else {
            OrderItem newItem = new OrderItem();
            newItem.setOrderId(orderId);
            newItem.setProductId(productId);
            newItem.setQuantity(qty);
            newItem.setPrice(shoe.getPrice());
            manageOrderItem.add(newItem);
        }

        shoe.setQuantity(shoe.getQuantity() - qty);
        manageShoe.updateShoe(shoe);

        Order order = manageOrder.getOrderById(orderId);
        double newTotal = manageOrderItem.getByOrderId(orderId).stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        order.setTotalAmount(newTotal);
        manageOrder.updateOrder(order);

        Customer cust = manageCustomer.getCustomerById(order.getCustomerId());
        sellView.setBillInfo(order, cust);

        loadPendingBills();
        SwingUtilities.invokeLater(() -> {
            JTable tbl = sellView.getPendingBillTable();
            for (int row = 0; row < tbl.getRowCount(); row++) {
                int id = (int) tbl.getValueAt(row, 0);
                if (id == orderId) {
                    tbl.setRowSelectionInterval(row, row);
                    break;
                }
            }
        });

        loadCartItems(orderId);
        loadProducts();
    }

    private void recalcAndRefresh(int orderId) {
        double newTotal = manageOrderItem.getByOrderId(orderId).stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();
        Order order = manageOrder.getOrderById(orderId);
        order.setTotalAmount(newTotal);
        manageOrder.updateOrder(order);

        Customer cust = manageCustomer.getCustomerById(order.getCustomerId());
        sellView.setBillInfo(order, cust);

        loadPendingBills();
        SwingUtilities.invokeLater(() -> {
            JTable tbl = sellView.getPendingBillTable();
            for (int r = 0; r < tbl.getRowCount(); r++) {
                if ((int) tbl.getValueAt(r, 0) == orderId) {
                    tbl.setRowSelectionInterval(r, r);
                    break;
                }
            }
        });

        loadCartItems(orderId);
        loadProducts();
    }

    class PendingBillSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int row = sellView.getPendingBillTable().getSelectedRow();
                if (row < 0) return;

                int modelRow = sellView.getPendingBillTable().convertRowIndexToModel(row);
                int orderId = (int) sellView.getPendingBillTable()
                        .getModel()
                        .getValueAt(modelRow, 0);
                Order order = manageOrder.getOrderById(orderId);
                Customer customer = manageCustomer.getCustomerById(order.getCustomerId());
                loadCartItems(orderId);
                sellView.setBillInfo(order, customer);
            }
        }
    }

    class CartSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                // Handle selection change
                System.out.println("Cart selection changed");
            }
        }
    }

    class ProductMouseListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 1 && !e.isConsumed()) {
                int row = sellView.getProductTable().getSelectedRow();
                if (row < 0) return;

                int modelRow = sellView.getProductTable().convertRowIndexToModel(row);
                int productId = (int) sellView.getProductTable()
                        .getModel()
                        .getValueAt(modelRow, 0);

                Shoe shoe = manageShoe.getShoeById(productId);
                if (shoe != null) {
                    sellView.setProductImage(shoe.getImage());
                } else {
                    sellView.showError("Sản phẩm không tồn tại");
                }
            } else if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                onProductDoubleClick();
            }
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {
        }
    }

    class UpdateCustomerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = sellView.getPendingBillTable().getSelectedRow();
            if (selectedRow < 0) {
                sellView.showError("Vui lòng chọn hóa đơn để cập nhật khách hàng");
                return;
            }
            int modelRow = sellView.getPendingBillTable()
                    .convertRowIndexToModel(selectedRow);
            int orderId = (int) sellView.getPendingBillTable()
                    .getModel()
                    .getValueAt(modelRow, 0);
            Order order = manageOrder.getOrderById(orderId);
            if (order == null) {
                sellView.showError("Không tìm thấy hóa đơn này");
                return;
            }

            CustomerSelectDialog dlg = new CustomerSelectDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(sellView)
            );
            dlg.addCheckListener(evt -> {
                String phone = dlg.getPhoneNumber();
                if (!ValidationUtils.isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(dlg,
                            "Số điện thoại không hợp lệ", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Customer c = manageCustomer.getCustomerByPhone(phone);
                if (c != null) {
                    dlg.setCustomer(c);
                } else {
                    JOptionPane.showMessageDialog(dlg,
                            "Không tìm thấy khách hàng", "Thông báo",
                            JOptionPane.INFORMATION_MESSAGE);
                    dlg.setCustomer(null);
                }
            });
            dlg.addSelectListener(evt -> {
                Customer newCust = dlg.getSelectedCustomer();
                if (newCust != null) {
                    order.setCustomerId(newCust.getId());
                    manageOrder.updateOrder(order);

                    sellView.showSuccess("Cập nhật khách hàng thành công");
                    loadPendingBills();
                    sellView.setBillInfo(order, newCust);
                }
                dlg.dispose();
            });
            dlg.setVisible(true);
        }
    }

    class CreateBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dialog = new CustomerSelectDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(sellView)
            );

            dialog.addCheckListener(evt -> {
                String phone = dialog.getPhoneNumber();
                if (!ValidationUtils.isValidPhone(phone)) {
                    JOptionPane.showMessageDialog(dialog, "SĐT không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Customer c = manageCustomer.getCustomerByPhone(phone);
                if (c != null) {
                    dialog.setCustomer(c);
                } else {
                    JOptionPane.showMessageDialog(dialog, "Không tìm thấy khách hàng",
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dialog.setCustomer(null);
                }
            });

            dialog.addSelectListener(evt -> {
                Customer sel = dialog.getSelectedCustomer();
                if (sel != null) {
                    sellView.setCustomerInfo(sel);
                }
                dialog.dispose();
            });

            dialog.setVisible(true);

            Customer chosen = dialog.getSelectedCustomer();
            if (chosen == null) {
                return;
            }

            Order newOrder = new Order();
            newOrder.setCustomerId(chosen.getId());
            newOrder.setStaffId(currentUser.getId());
            newOrder.setStatus(OrderStatus.PENDING);
            newOrder.setPaymentMethod(PaymentMethod.CASH);
            newOrder.setTotalAmount(0);
            manageOrder.addOrder(newOrder);

            sellView.showSuccess("Tạo hóa đơn thành công!");
            loadPendingBills();
            sellView.setBillInfo(newOrder, chosen);
            loadCartItems(newOrder.getId());
        }
    }

    class PaymentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selRow = sellView.getPendingBillTable().getSelectedRow();
            if (selRow < 0) {
                sellView.showError("Vui lòng chọn hóa đơn cần thanh toán");
                return;
            }
            int modelRow = sellView.getPendingBillTable()
                    .convertRowIndexToModel(selRow);
            int orderId = (int) sellView.getPendingBillTable()
                    .getModel().getValueAt(modelRow, 0);

            Order order = manageOrder.getOrderById(orderId);
            if (order == null) {
                sellView.showError("Hóa đơn không tồn tại");
                return;
            }

            List<OrderItem> items = manageOrderItem.getByOrderId(orderId);
            if (items.isEmpty()) {
                sellView.showError("Giỏ hàng trống, không thể thanh toán");
                return;
            }

            double finalTotal = items.stream()
                    .mapToDouble(i -> i.getPrice() * i.getQuantity())
                    .sum();
            order.setTotalAmount(finalTotal);
            order.setStatus(OrderStatus.COMPLETED);
            order.setDescription(sellView.getNote());
            order.setPaymentMethod(sellView.getPaymentMethod());
            manageOrder.updateOrder(order);

            sellView.showSuccess("Thanh toán thành công: "
                    + CurrencyUtils.formatCurrency(finalTotal));

            sellView.setCartItems(new Object[0][5]);
            sellView.clearBillInfo();
            sellView.clearCustomerInfo();

            loadPendingBills();
            loadProducts();
        }
    }

    class CancelBillListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = sellView.getPendingBillTable().getSelectedRow();
            if (selectedRow < 0) {
                sellView.showError("Vui lòng chọn hóa đơn cần hủy");
                return;
            }

            int modelRow = sellView.getPendingBillTable().convertRowIndexToModel(selectedRow);
            int orderId = (int) sellView.getPendingBillTable()
                    .getModel().getValueAt(modelRow, 0);
            Order order = manageOrder.getOrderById(orderId);

            if (order == null || !OrderStatus.PENDING.equals(order.getStatus())) {
                sellView.showError("Hóa đơn không tồn tại hoặc đã được xử lý");
                return;
            }

            List<OrderItem> items = manageOrderItem.getByOrderId(orderId);

            for (OrderItem item : items) {
                Shoe shoe = manageShoe.getShoeById(item.getProductId());
                shoe.setQuantity(shoe.getQuantity() + item.getQuantity());
                manageShoe.updateShoe(shoe);
            }

//            for (OrderItem item : items) {
//                manageOrderItem.delete(item.getId());
//            }

            order.setStatus(OrderStatus.CANCELED);
            manageOrder.updateOrder(order);

            sellView.showSuccess("Hủy hóa đơn thành công và phục hồi kho!");

            loadPendingBills();
            sellView.setCartItems(new Object[0][5]);
            loadProducts();
        }
    }


    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sellView.clearBillInfo();
        }
    }

    class EditCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = sellView.getCartTable().getSelectedRow();
            if (row < 0) {
                sellView.showError("Vui lòng chọn sản phẩm trong giỏ hàng để sửa");
                return;
            }
            int billRow = sellView.getPendingBillTable().getSelectedRow();
            int orderModelRow = sellView.getPendingBillTable().convertRowIndexToModel(billRow);
            int orderId = (int) sellView.getPendingBillTable().getModel().getValueAt(orderModelRow, 0);

            int modelRow = sellView.getCartTable().convertRowIndexToModel(row);
            int productId = (int) sellView.getCartTable().getModel().getValueAt(modelRow, 0);
            OrderItem item = manageOrderItem.findByOrderIdAndProductId(orderId, productId);
            if (item == null) {
                sellView.showError("Không tìm thấy mục giỏ hàng này");
                return;
            }
            Shoe shoe = manageShoe.getShoeById(productId);
            int maxQty = shoe.getQuantity() + item.getQuantity();

            AddToCartDialog dlg = new AddToCartDialog(
                    (JFrame) SwingUtilities.getWindowAncestor(sellView),
                    shoe.getName(),
                    maxQty
            );
            dlg.getTxtQuantity().setText(String.valueOf(item.getQuantity()));
            dlg.setVisible(true);

            int newQty = dlg.getQuantity();
            if (newQty <= 0) return;

            shoe.setQuantity(maxQty - newQty);
            manageShoe.updateShoe(shoe);

            item.setQuantity(newQty);
            manageOrderItem.update(item);

            recalcAndRefresh(orderId);
        }
    }

    class DeleteCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = sellView.getCartTable().getSelectedRow();
            if (row < 0) {
                sellView.showError("Vui lòng chọn sản phẩm trong giỏ hàng để xóa");
                return;
            }
            int billRow = sellView.getPendingBillTable().getSelectedRow();
            int orderId = (int) sellView.getPendingBillTable()
                    .getModel().getValueAt(
                            sellView.getPendingBillTable().convertRowIndexToModel(billRow),
                            0
                    );

            int modelRow = sellView.getCartTable().convertRowIndexToModel(row);
            int productId = (int) sellView.getCartTable().getModel().getValueAt(modelRow, 0);
            OrderItem item = manageOrderItem.findByOrderIdAndProductId(orderId, productId);
            if (item == null) return;

            Shoe shoe = manageShoe.getShoeById(productId);
            shoe.setQuantity(shoe.getQuantity() + item.getQuantity());
            manageShoe.updateShoe(shoe);

            manageOrderItem.delete(item.getId());

            recalcAndRefresh(orderId);
        }
    }

    class DeleteAllCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int billRow = sellView.getPendingBillTable().getSelectedRow();
            if (billRow < 0) {
                sellView.showError("Vui lòng chọn hóa đơn trước khi xóa toàn bộ giỏ hàng");
                return;
            }
            int orderId = (int) sellView.getPendingBillTable()
                    .getModel().getValueAt(
                            sellView.getPendingBillTable().convertRowIndexToModel(billRow),
                            0
                    );

            List<OrderItem> items = manageOrderItem.getByOrderId(orderId);
            for (OrderItem item : items) {
                Shoe shoe = manageShoe.getShoeById(item.getProductId());
                shoe.setQuantity(shoe.getQuantity() + item.getQuantity());
                manageShoe.updateShoe(shoe);

                manageOrderItem.delete(item.getId());
            }
            recalcAndRefresh(orderId);
        }
    }
}
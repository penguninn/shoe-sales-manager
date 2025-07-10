package com.zzz.quanlibangiay.controller;

import com.zzz.quanlibangiay.action.*;
import com.zzz.quanlibangiay.entity.*;
import com.zzz.quanlibangiay.enums.OrderStatus;
import com.zzz.quanlibangiay.enums.PaymentMethod;
import com.zzz.quanlibangiay.utils.CurrencyUtils;
import com.zzz.quanlibangiay.utils.DateUtils;
import com.zzz.quanlibangiay.view.OrderView;

import javax.swing.event.ListSelectionListener;
import java.util.List;

public class OrderController {

    private OrderView orderView;
    private ManageOrder manageOrder;
    private ManageCustomer manageCustomer;
    private ManageUser manageUser;
    private ManageShoe manageShoe;
    private ManageType manageType;
    private ManageOrderItem manageOrderItem;
    private ManageBrand manageBrand;
    private ManageSize manageSize;
    private ManageColor manageColor;
    private ManageMaterial manageMaterial;

    private User currentUser;

    public OrderController(OrderView view, User currentUser,
                           ManageOrder manageOrder, ManageCustomer manageCustomer,
                           ManageUser manageUser, ManageShoe manageShoe,
                           ManageType manageType, ManageOrderItem manageOrderItem,
                           ManageBrand manageBrand, ManageSize manageSize,
                           ManageColor manageColor, ManageMaterial manageMaterial) {
        this.orderView = view;
        this.currentUser = currentUser;
        this.manageOrder = manageOrder;
        this.manageCustomer = manageCustomer;
        this.manageUser = manageUser;
        this.manageShoe = manageShoe;
        this.manageType = manageType;
        this.manageOrderItem = manageOrderItem;
        this.manageBrand = manageBrand;
        this.manageSize = manageSize;
        this.manageColor = manageColor;
        this.manageMaterial = manageMaterial;

        initListeners();
        initData();
    }

    private void initListeners() {
        orderView.addInvoiceTableSelectionListener(new OrderTableSelectionListener());
    }

    public void initData() {
        loadBillTableData();
    }

    private void loadBillTableData() {
        try {
            List<Order> allBills = manageOrder.getAllOrders();
            System.out.println("Total orders: " + allBills.size());
            Object[][] data = toOrderTableData(allBills);
            orderView.setInvoiceTableData(data);
        } catch (Exception ex) {
            ex.printStackTrace();
            orderView.showError("Không thể tải danh sách hóa đơn!");
        }
    }

    private Object[][] toOrderTableData(List<Order> orders) {
        if (orders == null || orders.isEmpty()) {
            return new Object[0][7];
        }
        Object[][] data = new Object[orders.size()][7];
        for (int i = 0; i < orders.size(); i++) {
            Order b = orders.get(i);
            Customer customer = manageCustomer.getCustomerById(b.getCustomerId());
            User user = manageUser.getUserById(b.getStaffId());
            data[i][0] = b.getId();
            data[i][1] = customer != null ? customer.getName() : "Khách hàng không tồn tại";
            data[i][2] = user != null ? user.getFullName() : "Nhân viên không tồn tại";
            data[i][3] = CurrencyUtils.formatCurrency(b.getTotalAmount());
            data[i][4] = b.getPaymentMethod() != null ? PaymentMethod.paymentMethodToDisplay(b.getPaymentMethod()) : "Unknown";
            data[i][5] = b.getStatus() != null ? OrderStatus.orderStatusToDisplay(b.getStatus()) : "Unknown";
            data[i][6] = b.getCreatedDate() != null ? DateUtils.formatDateToShort(b.getCreatedDate()) : "Unknown";
        }
        return data;
    }

    private Object[][] toProductTableData(List<OrderItem> details) {
        if (details == null || details.isEmpty()) {
            return new Object[0][10];
        }

        Object[][] data = new Object[details.size()][10];
        for (int i = 0; i < details.size(); i++) {
            OrderItem d = details.get(i);
            Shoe s = manageShoe.getShoeById(d.getProductId());
            if (s == null) {
                data[i][1] = "(Sản phẩm không tồn tại)";
            }
            if (s != null) {
                data[i][0] = d.getId();
                data[i][1] = s.getName();
                data[i][2] = manageType.getShoeTypeById(s.getTypeId()).getName();
                data[i][3] = manageBrand.getBrandById(s.getBrandId()).getName();
                data[i][4] = manageSize.getSizeById(s.getSizeId()).getName();
                data[i][5] = manageColor.getColorById(s.getColorId()).getName();
                data[i][6] = manageMaterial.getMaterialById(s.getMaterialId()).getName();
                data[i][7] = d.getQuantity();
                data[i][8] = CurrencyUtils.formatCurrency(d.getPrice());
                data[i][9] = CurrencyUtils.formatCurrency(d.getPrice() * d.getQuantity());
            }
        }
        return data;
    }

    class OrderTableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(javax.swing.event.ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int row = orderView.getOrderTable().getSelectedRow();
                if (row < 0) return;

                int modelRow = orderView.getOrderTable().convertRowIndexToModel(row);
                int orderId = Integer.parseInt(orderView.getOrderTable()
                        .getModel()
                        .getValueAt(modelRow, 0)
                        .toString());

                Order order = manageOrder.getOrderById(orderId);
                if (order == null) return;

                List<OrderItem> items = manageOrderItem.getByOrderId(orderId);
                Object[][] prodData = toProductTableData(items);
                orderView.setProductTableData(prodData);

                Customer c = manageCustomer.getCustomerById(order.getCustomerId());
                if (c == null) {
                    orderView.setCustomerInfo(
                            "",
                            "",
                            "",
                            "",
                            ""
                    );
                    return;
                }
                orderView.setCustomerInfo(
                        String.valueOf(c.getId()),
                        c.getName(),
                        c.getPhoneNumber(),
                        c.isGender() ? "Nam" : "Nữ",
                        c.getAddress()
                );

            }
        }
    }
}
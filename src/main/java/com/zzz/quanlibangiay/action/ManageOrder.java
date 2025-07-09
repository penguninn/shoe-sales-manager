package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Order;
import com.zzz.quanlibangiay.entity.xml.OrderXML;
import com.zzz.quanlibangiay.enums.OrderStatus;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManageOrder {

    private static final String ORDERS_FILE = "Orders.xml";

    private List<Order> orderList;

    public ManageOrder() {
        OrderXML ox = (OrderXML) FileUtils.readXMLFile(ORDERS_FILE, OrderXML.class);
        this.orderList = (ox == null || ox.getOrders() == null)
                ? new ArrayList<>()
                : ox.getOrders();
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public List<Order> getAllPendingOrders() {
        List<Order> pending = new ArrayList<>();
        for (Order o : orderList) {
            if (o.getStatus() == OrderStatus.PENDING) {
                pending.add(o);
            }
        }
        return pending;
    }

    public Order getOrderById(int id) {
        for (Order o : orderList) {
            if (o.getId() == id) return o;
        }
        return null;
    }

    public boolean addOrder(Order order) {
        order.setId(getNextOrderId());
        order.setCreatedDate(new Date());
        orderList.add(order);
        saveAll();
        return true;
    }

    public boolean updateOrder(Order updated) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == updated.getId()) {
                orderList.set(i, updated);
                saveAll();
                return true;
            }
        }
        return false;
    }

    public boolean deleteOrder(int id) {
        Order toRemove = getOrderById(id);
        if (toRemove != null) {
            orderList.remove(toRemove);
            saveAll();
            return true;
        }
        return false;
    }

    private int getNextOrderId() {
        int max = 0;
        for (Order o : orderList) {
            if (o.getId() > max) {
                max = o.getId();
            }
        }
        return max + 1;
    }

    private void saveAll() {
        OrderXML ox = new OrderXML();
        ox.setOrders(orderList);
        FileUtils.writeXMLtoFile(ORDERS_FILE, ox);
    }
}

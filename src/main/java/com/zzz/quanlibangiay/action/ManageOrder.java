/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Order;
import com.zzz.quanlibangiay.entity.xml.OrderXML;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coole
 */
public class ManageOrder {
    
private static final String FILE_NAME = "Orders.xml";
    private List<Order> orderList;

    public ManageOrder() {
        OrderXML orderXML = (OrderXML) FileUtils.readXMLFile(FILE_NAME, OrderXML.class);
        if (orderXML == null || orderXML.getOrders() == null) {
            orderList = new ArrayList<>();
        } else {
            orderList = orderXML.getOrders();
        }
    }

    public List<Order> getAllOrders() {
        return orderList;
    }

    public Order getOrderById(int id) {
        for (Order order : orderList) {
            if (order.getId() == id) return order;
        }
        return null;
    }

    public boolean addOrder(Order order) {
        if (order.getId() == 0) {
            order.setId(getNextId());
        } else if (getOrderById(order.getId()) != null) {
            return false; 
        }

        orderList.add(order);
        saveToFile();
        return true;
    }

    public boolean updateOrder(Order updatedOrder) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == updatedOrder.getId()) {
                orderList.set(i, updatedOrder);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean deleteOrder(int id) {
        Order order = getOrderById(id);
        if (order != null) {
            orderList.remove(order);
            saveToFile();
            return true;
        }
        return false;
    }

    private int getNextId() {
        int max = 0;
        for (Order order : orderList) {
            if (order.getId() > max) {
                max = order.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        OrderXML orderXML = new OrderXML();
        orderXML.setOrders(orderList);
        FileUtils.writeXMLtoFile(FILE_NAME, orderXML);
    }
}

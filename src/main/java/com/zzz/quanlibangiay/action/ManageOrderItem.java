/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.OrderItem;
import com.zzz.quanlibangiay.entity.xml.OrderItemXML;
import com.zzz.quanlibangiay.utils.FileUtils;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author coole
 */
public class ManageOrderItem {
    
private static final String FILE_NAME = "OrderItems.xml";
    private List<OrderItem> orderItemList;

    public ManageOrderItem() {
        OrderItemXML wrapper = (OrderItemXML) FileUtils.readXMLFile(FILE_NAME, OrderItemXML.class);
        if (wrapper == null || wrapper.getOrderItems() == null) {
            orderItemList = new ArrayList<>();
        } else {
            orderItemList = wrapper.getOrderItems();
        }
    }

    public List<OrderItem> getAll() {
        return orderItemList;
    }

    public OrderItem getById(int id) {
        for (OrderItem item : orderItemList) {
            if (item.getId() == id) return item;
        }
        return null;
    }

    public boolean add(OrderItem item) {
        if (item.getId() == 0) {
            item.setId(getNextId());
        } else if (getById(item.getId()) != null) {
            return false; 
        }
        orderItemList.add(item);
        saveToFile();
        return true;
    }

    public boolean update(OrderItem updatedItem) {
        for (int i = 0; i < orderItemList.size(); i++) {
            if (orderItemList.get(i).getId() == updatedItem.getId()) {
                orderItemList.set(i, updatedItem);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        OrderItem item = getById(id);
        if (item != null) {
            orderItemList.remove(item);
            saveToFile();
            return true;
        }
        return false;
    }

    public List<OrderItem> getByOrderId(int orderId) {
        List<OrderItem> result = new ArrayList<>();
        for (OrderItem item : orderItemList) {
            if (item.getOrderId() == orderId) {
                result.add(item);
            }
        }
        return result;
    }

    private int getNextId() {
        int max = 0;
        for (OrderItem item : orderItemList) {
            if (item.getId() > max) {
                max = item.getId();
            }
        }
        return max + 1;
    }

    private void saveToFile() {
        OrderItemXML wrapper = new OrderItemXML();
        wrapper.setOrderItems(orderItemList);
        FileUtils.writeXMLtoFile(FILE_NAME, wrapper);
    }
}

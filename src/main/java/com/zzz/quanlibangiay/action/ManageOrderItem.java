/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.action;

import com.zzz.quanlibangiay.entity.Order;
import com.zzz.quanlibangiay.entity.OrderItem;
import com.zzz.quanlibangiay.entity.Shoe;
import com.zzz.quanlibangiay.entity.xml.OrderItemXML;
import com.zzz.quanlibangiay.enums.OrderStatus;
import com.zzz.quanlibangiay.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author coole
 */
public class ManageOrderItem {

    private static final String FILE_NAME = "OrderItems.xml";
    private List<OrderItem> orderItemList;

    private ManageOrder manageOrder;
    private ManageShoe manageShoe;

    public ManageOrderItem(ManageOrder manageOrder, ManageShoe manageShoe) {
        OrderItemXML wrapper = (OrderItemXML) FileUtils.readXMLFile(FILE_NAME, OrderItemXML.class);
        if (wrapper == null || wrapper.getOrderItems() == null) {
            orderItemList = new ArrayList<>();
        } else {
            orderItemList = wrapper.getOrderItems();
        }
        this.manageOrder = manageOrder;
        this.manageShoe = manageShoe;
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
        item.setId(getNextId());
        item.setCreatedDate(new java.util.Date());
        orderItemList.add(item);
        saveToFile();
        return true;
    }

    public OrderItem findByOrderIdAndProductId(int orderId, int productId) {
        for (OrderItem item : orderItemList) {
            if (item.getOrderId() == orderId && item.getProductId() == productId) {
                return item;
            }
        }
        return null;
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

    public void deleteByProductId(int productId) {
        boolean changed = orderItemList.removeIf(item -> item.getProductId() == productId);
        if (changed) {
            saveToFile();
        }
    }

    public boolean isProductInUse(int productId) {
        return orderItemList.stream()
                .anyMatch(item -> item.getProductId() == productId);
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

    public int countDistinctProductTypes() {
        return (int) orderItemList.stream()
                .map(OrderItem::getProductId)
                .distinct()
                .count();
    }

    public int countTotalProducts() {
        return orderItemList.stream()
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public int countSoldProducts() {
        return orderItemList.stream()
                .filter(item -> {
                    Order order = manageOrder.getOrderById(item.getOrderId());
                    return order != null && order.getStatus() == OrderStatus.COMPLETED;
                })
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public long getTotalRevenue() {
        return orderItemList.stream()
                .mapToLong(item -> (long) (item.getPrice() * item.getQuantity()))
                .sum();
    }

    public double getTotalCost() {
        return orderItemList.stream()
                .filter(item -> {
                    Order order = manageOrder.getOrderById(item.getOrderId());
                    return order != null && order.getStatus() == OrderStatus.COMPLETED;
                })
                .mapToDouble(item -> {
                    Shoe shoe = manageShoe.getShoeById(item.getProductId());
                    double importPrice = (shoe != null) ? shoe.getImportPrice() : 0;
                    return (double) (importPrice * item.getQuantity());
                }).sum();
    }

    public int getSoldQuantityByProductId(int productId) {
        return orderItemList.stream()
                .filter(item -> item.getProductId() == productId)
                .filter(item -> {
                    Order order = manageOrder.getOrderById(item.getOrderId());
                    return order != null && order.getStatus() == OrderStatus.COMPLETED;
                })
                .mapToInt(OrderItem::getQuantity)
                .sum();
    }

    public double getTotalRevenueByStaffId(int staffId) {
        return orderItemList.stream()
                .filter(item -> {
                    Order order = manageOrder.getOrderById(item.getOrderId());
                    return order != null && order.getStatus() == OrderStatus.COMPLETED
                            && order.getStaffId() == staffId;
                })
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public int getOrderCountByStaffId(int staffId) {
        return (int) orderItemList.stream()
                .map(OrderItem::getOrderId)
                .distinct()
                .filter(orderId -> {
                    Order order = manageOrder.getOrderById(orderId);
                    return order != null && order.getStaffId() == staffId;
                })
                .count();
    }
}
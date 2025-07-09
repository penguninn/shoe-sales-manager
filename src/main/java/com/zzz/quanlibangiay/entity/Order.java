/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.entity;

import com.zzz.quanlibangiay.enums.OrderStatus;
import com.zzz.quanlibangiay.enums.PaymentMethod;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order extends AbstractEntity {
    
    private int customerId;
    private int staffId;
    private double totalAmount;
    private PaymentMethod paymentMethod;
    private OrderStatus status;

    public Order(int customerId, int staffId, double totalAmount, PaymentMethod paymentMethod, OrderStatus status, int id, Date createdDate, String description) {
        super(id, createdDate, description);
        this.customerId = customerId;
        this.staffId = staffId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Order() {
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    
}

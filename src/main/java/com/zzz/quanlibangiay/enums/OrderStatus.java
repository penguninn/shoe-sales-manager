/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.enums;


/**
 *
 * @author coole
 */
public enum OrderStatus {
    PENDING,
    COMPLETED,
    CANCELED;

    public static String orderStatusToDisplay(OrderStatus status) {
        return switch (status) {
            case PENDING -> "Đang chờ xử lý";
            case COMPLETED -> "Đã hoàn thành";
            case CANCELED -> "Đã hủy";
        };
    }

    public static OrderStatus displayToOrderStatus(String display) {
        return switch (display) {
            case "Đang chờ xử lý" -> OrderStatus.PENDING;
            case "Đã hoàn thành" -> OrderStatus.COMPLETED;
            case "Đã hủy" -> OrderStatus.CANCELED;
            default -> null;
        };
    }
}

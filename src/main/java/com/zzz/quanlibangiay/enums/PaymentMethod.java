/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.enums;

/**
 *
 * @author coole
 */
public enum PaymentMethod {
    BANKING,
    CASH;

    public static String paymentMethodToDisplay(PaymentMethod method) {
        return switch (method) {
            case BANKING -> "Chuyển khoản";
            case CASH -> "Tiền mặt";
        };
    }

    public static PaymentMethod displayToPaymentMethod(String display) {
        return switch (display) {
            case "Chuyển khoản" -> PaymentMethod.BANKING;
            case "Tiền mặt" -> PaymentMethod.CASH;
            default -> null;
        };
    }
}

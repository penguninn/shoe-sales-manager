/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.enums;

/**
 * @author coole
 */
public enum UserStatus {
    WORKING,
    RETIRED;

    public static String statusToDisplay(UserStatus status) {
        return switch (status) {
            case WORKING -> "Đang làm việc";
            case RETIRED -> "Đã nghỉ việc";
        };
    }

    public static UserStatus displayToStatus(String display) {
        return switch (display) {
            case "Đang làm việc" -> UserStatus.WORKING;
            case "Đã nghỉ việc" -> UserStatus.RETIRED;
            default -> null;
        };
    }

}

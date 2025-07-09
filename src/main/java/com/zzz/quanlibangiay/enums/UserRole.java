/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.zzz.quanlibangiay.enums;

/**
 *
 * @author coole
 */
public enum UserRole {
    ADMIN,
    STAFF;

    public static String roleToDisplay(UserRole role) {
        return switch (role) {
            case ADMIN -> "Quản lý";
            case STAFF -> "Nhân viên";
        };
    }

    public static UserRole displayToRole(String display) {
        return switch (display) {
            case "Quản lý" -> UserRole.ADMIN;
            case "Nhân viên" -> UserRole.STAFF;
            default -> null;
        };
    }
}

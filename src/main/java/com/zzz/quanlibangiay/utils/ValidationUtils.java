package com.zzz.quanlibangiay.utils;

import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^(\\+84|0)[3|5|7|8|9][0-9]{8}$");
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    
    public static boolean isValidPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    
    public static boolean isNotEmpty(String text) {
        return text != null && !text.trim().isEmpty();
    }
    
    public static boolean isPositiveNumber(String text) {
        try {
            double number = Double.parseDouble(text);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
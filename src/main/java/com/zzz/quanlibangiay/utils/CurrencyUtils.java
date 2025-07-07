package com.zzz.quanlibangiay.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {
    private static final NumberFormat CURRENCY_FORMAT = 
        NumberFormat.getInstance(new Locale("vi", "VN"));
    
    static {
        CURRENCY_FORMAT.setMaximumFractionDigits(0);
    }
    
    public static String formatCurrency(double amount) {
        return CURRENCY_FORMAT.format(amount);
    }
    
    public static String formatNumber(double number) {
        return CURRENCY_FORMAT.format(number);
    }
}
package com.zzz.quanlibangiay.utils;

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

    public static double parseCurrency(String currencyStr) {
        if (currencyStr == null || currencyStr.trim().isEmpty()) {
            return 0;
        }
        try {
            String normalized = currencyStr.replaceAll("[\\.\\s]", "").replace(",", ".");
            return Double.parseDouble(normalized);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
package com.zzz.quanlibangiay.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String formatDateToShort(Date date) {
        if (date == null) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }
}

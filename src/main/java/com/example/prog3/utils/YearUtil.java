package com.example.prog3.utils;

import java.time.LocalDate;

public class YearUtil {
    public static String getLastDigitofTheYear(){
        String currentYear = String.valueOf(LocalDate.now().getYear()).substring(2);
        return currentYear;
    }
}

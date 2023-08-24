package com.example.prog3.utils;

public class MatriculeGenerator {
    public static String generateMatricule(int lastEmployee){
        return "EMP"+ YearUtil.getLastDigitofTheYear() + lastEmployee;
    }
}

package com.example.prog3.utils;

import java.time.LocalDate;

public class AgeCalculator {
    public static int age(LocalDate birthdate,LocalDate currentDate){
        int age ;
        if((birthdate.getMonthValue() == currentDate.getMonthValue() && birthdate.getDayOfMonth() <= currentDate.getDayOfMonth()) ||
                birthdate.getMonthValue() <= currentDate.getMonthValue()){
            age = currentDate.getYear() - birthdate.getYear();
        } else{
            age = currentDate.getYear() - birthdate.getYear() - 1;
        }
        return age;
    }
}

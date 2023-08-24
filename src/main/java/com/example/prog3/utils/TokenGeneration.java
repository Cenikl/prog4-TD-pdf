package com.example.prog3.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TokenGeneration {
   public String generateToken(Long id){
       return UUID.randomUUID().toString() + id.toString();
   }
}

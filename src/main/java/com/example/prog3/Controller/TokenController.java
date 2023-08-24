package com.example.prog3.Controller;

import com.example.prog3.utils.TokenGeneration;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
public class TokenController {
    private TokenGeneration tokenGeneration;

   @ModelAttribute
    public void validateToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();
        boolean validToken = false;
        if(cookies != null){
            for (Cookie cookie: cookies){
                if(cookie.getName().equals("token")){
                    String token = cookie.getValue();
                    if(!token.isEmpty()){
                        validToken = true;
                        break;
                    }
                }
            }
        }
        if(!validToken){
            response.sendRedirect("/");
        }
    }
}

package com.example.prog3.Service.last1;

import com.example.prog3.Repository.last1.UserRepository;
import com.example.prog3.model.last1.Users;
import com.example.prog3.utils.TokenGeneration;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final TokenGeneration tokenGeneration;

    public boolean checkUser(String username,String password){
        return repository.findByUsernameAndPassword(username,password) != null;
    }
    public Users getUser(String username, String password){
        return repository.findByUsernameAndPassword(username, password);
    }

    public String generateTokenForUserId(Long id){
        return tokenGeneration.generateToken(id);
    }
}

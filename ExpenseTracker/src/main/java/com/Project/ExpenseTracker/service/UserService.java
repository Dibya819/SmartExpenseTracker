package com.Project.ExpenseTracker.service;

import com.Project.ExpenseTracker.model.User;
import com.Project.ExpenseTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<String>RegisterUser(User user){
       if(userRepository.findByUsername(user.getUsername()).isPresent()){
           return ResponseEntity.badRequest().body("UserName already exists ");
       }
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       userRepository.save(user);
       return ResponseEntity.ok("USer registered successfully");
    }

    public User login(String username,String Password){
        Optional<User> optionalUser=userRepository.findByUsername(username);
            if(optionalUser.isPresent() && passwordEncoder.matches(Password, optionalUser.get().getPassword())){
                return optionalUser.get();
            }
            return null;
    }
}

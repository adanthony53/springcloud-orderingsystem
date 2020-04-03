package com.example.controller;

import com.example.repository.AdminRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountHandler {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username,
                         @PathVariable("password") String password,
                         @PathVariable("type") String type) {
        Object obj = null;
        if (type.equals("user")) {
            obj = userRepository.login(username, password);
        } else if (type.equals("admin")) {
            obj = adminRepository.login(username, password);
        }
        return obj;
    }
}

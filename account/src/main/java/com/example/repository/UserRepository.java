package com.example.repository;

import com.example.entity.User;

import java.util.List;

public interface UserRepository {
    public User login(String username, String password);
}

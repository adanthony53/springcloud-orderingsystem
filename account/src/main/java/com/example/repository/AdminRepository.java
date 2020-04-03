package com.example.repository;

import com.example.entity.Admin;

public interface AdminRepository {
    public Admin login(String username, String password);
}

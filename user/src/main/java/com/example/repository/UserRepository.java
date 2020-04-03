package com.example.repository;

import com.example.entity.User;

import java.util.List;

public interface UserRepository {
    public List<User> findAll(int index, int limit);
    public int count();
    public User findById(long id);
    public void save(User user);
    public void update(User user);
    public void deleteById(long id);
}

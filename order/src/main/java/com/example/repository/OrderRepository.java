package com.example.repository;

import com.example.entity.Order;

import java.util.List;

public interface OrderRepository {
    public void save(Order order);
    public List<Order> findByUid(long uid, int index, int limit);
    public int count();
    public int countByUid(long id);
    public List<Order> findAll(int index, int limit);
    public void updateState(long id, long aid);
}

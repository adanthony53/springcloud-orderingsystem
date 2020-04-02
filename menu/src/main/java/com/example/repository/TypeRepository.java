package com.example.repository;

import com.example.entity.Type;

import java.util.List;

public interface TypeRepository {
    public Type findById(long id);
    public List<Type> findAll();
}

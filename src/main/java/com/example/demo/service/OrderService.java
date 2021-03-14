package com.example.demo.service;

import com.example.demo.model.shop.Order;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;

    public List<Order> findAll() {
        return repository.findAll();
    }

    public Order findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Order order) {
        repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package com.example.demo.service;

import com.example.demo.model.shop.OrderDetail;
import com.example.demo.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository repository;

    public List<OrderDetail> findAll() {
        return repository.findAll();
    }

    public OrderDetail findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(OrderDetail orderDetail) {
        repository.save(orderDetail);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

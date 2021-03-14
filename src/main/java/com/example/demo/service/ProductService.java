package com.example.demo.service;

import com.example.demo.model.shop.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Product product) {
        repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

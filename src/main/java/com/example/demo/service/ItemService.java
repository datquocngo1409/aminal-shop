package com.example.demo.service;

import com.example.demo.model.shop.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository repository;

    public List<Item> findAll() {
        return repository.findAll();
    }

    public Item findById(Long id) {
        return repository.findById(id).get();
    }

    public void save(Item product) {
        repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}

package com.example.demo.controller;

import com.example.demo.model.shop.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class OrderController {
    @Autowired
    public OrderService service;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public ResponseEntity<List<Order>> listAll() {
        List<Order> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Order>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Order>>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getById(@PathVariable("id") Long id) {
        System.out.println("Fetching Order with id " + id);
        Order account = service.findById(id);
        if (account == null) {
            System.out.println("Order with id " + id + " not found");
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Order>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Order order, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Order " + order.getId());
        service.save(order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/order/{id}").buildAndExpand(order.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Order> updateAdmin(@PathVariable("id") Long id, @RequestBody Order order) {
        System.out.println("Updating Order " + id);

        Order current = service.findById(id);

        if (current == null) {
            System.out.println("Order with id " + id + " not found");
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        current = order;

        service.save(current);
        return new ResponseEntity<Order>(current, HttpStatus.OK);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Order> delete(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Order with id " + id);

        Order order = service.findById(id);
        if (order == null) {
            System.out.println("Unable to delete. Order with id " + id + " not found");
            return new ResponseEntity<Order>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Order>(HttpStatus.NO_CONTENT);
    }
}

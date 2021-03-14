package com.example.demo.controller;

import com.example.demo.model.shop.OrderDetail;
import com.example.demo.service.OrderDetailService;
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
public class OrderDetailController {
    @Autowired
    public OrderDetailService service;

    @RequestMapping(value = "/orderDetail", method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> listAll() {
        List<OrderDetail> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<OrderDetail>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<OrderDetail>>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDetail> getById(@PathVariable("id") Long id) {
        System.out.println("Fetching OrderDetail with id " + id);
        OrderDetail account = service.findById(id);
        if (account == null) {
            System.out.println("OrderDetail with id " + id + " not found");
            return new ResponseEntity<OrderDetail>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<OrderDetail>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderDetail", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody OrderDetail orderDetail, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating OrderDetail " + orderDetail.getId());
        service.save(orderDetail);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/orderDetail/{id}").buildAndExpand(orderDetail.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<OrderDetail> updateAdmin(@PathVariable("id") Long id, @RequestBody OrderDetail orderDetail) {
        System.out.println("Updating OrderDetail " + id);

        OrderDetail current = service.findById(id);

        if (current == null) {
            System.out.println("OrderDetail with id " + id + " not found");
            return new ResponseEntity<OrderDetail>(HttpStatus.NOT_FOUND);
        }

        current = orderDetail;

        service.save(current);
        return new ResponseEntity<OrderDetail>(current, HttpStatus.OK);
    }

    @RequestMapping(value = "/orderDetail/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<OrderDetail> delete(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting OrderDetail with id " + id);

        OrderDetail orderDetail = service.findById(id);
        if (orderDetail == null) {
            System.out.println("Unable to delete. OrderDetail with id " + id + " not found");
            return new ResponseEntity<OrderDetail>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<OrderDetail>(HttpStatus.NO_CONTENT);
    }
}

package com.example.demo.controller;

import com.example.demo.model.shop.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@CrossOrigin(value = "*", maxAge = 3600)
public class ItemController {
    @Autowired
    public ItemService service;

    @RequestMapping(value = "/item", method = RequestMethod.GET)
    public ResponseEntity<List<Item>> listAll() {
        List<Item> accounts = service.findAll();
        if (accounts.isEmpty()) {
            return new ResponseEntity<List<Item>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Item>>(accounts, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getById(@PathVariable("id") Long id) {
        System.out.println("Fetching Product with id " + id);
        Item account = service.findById(id);
        if (account == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Item>(account, HttpStatus.OK);
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody Item item, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Product " + item.getName());
        service.save(item);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/item/{id}").buildAndExpand(item.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Item> updateAdmin(@PathVariable("id") Long id, @RequestBody Item item) {
        System.out.println("Updating Product " + id);

        Item current = service.findById(id);

        if (current == null) {
            System.out.println("Product with id " + id + " not found");
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }

        current = item;

        service.save(current);
        return new ResponseEntity<Item>(current, HttpStatus.OK);
    }

    @RequestMapping(value = "/item/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Item> delete(@PathVariable("id") Long id) {
        System.out.println("Fetching & Deleting Product with id " + id);

        Item item = service.findById(id);
        if (item == null) {
            System.out.println("Unable to delete. Product with id " + id + " not found");
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }

        service.delete(id);
        return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
    }
}

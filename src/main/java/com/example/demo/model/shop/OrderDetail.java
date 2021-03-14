package com.example.demo.model.shop;

import com.example.demo.model.User;

import javax.persistence.*;

@Entity
@Table(name = "tbl_OrderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int quantity;

    @ManyToOne
    private User customer;

    @ManyToOne
    private Item item;

    public OrderDetail() {
    }

    public OrderDetail(int quantity, User customer, Item item) {
        this.quantity = quantity;
        this.customer = customer;
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Item getProduct() {
        return item;
    }

    public void setProduct(Item item) {
        this.item = item;
    }
}

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
    private Product product;

    public OrderDetail() {
    }

    public OrderDetail(int quantity, User customer, Product product) {
        this.quantity = quantity;
        this.customer = customer;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

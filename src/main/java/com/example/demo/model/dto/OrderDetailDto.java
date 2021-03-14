package com.example.demo.model.dto;

import com.example.demo.model.shop.OrderDetail;
import lombok.Data;

@Data
public class OrderDetailDto {
    private Long id;
    private int quantity;
    private Long customerId;
    private String customerName;
    private Long productId;
    private String productName;

    public OrderDetailDto() {
    }

    public OrderDetailDto(OrderDetail orderDetail) {
        this.id = orderDetail.getId();
        this.quantity = orderDetail.getQuantity();
        this.customerId = orderDetail.getCustomer().getId();
        this.customerName = orderDetail.getCustomer().getName();
        this.productId = orderDetail.getProduct().getId();
        this.productName = orderDetail.getProduct().getName();
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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

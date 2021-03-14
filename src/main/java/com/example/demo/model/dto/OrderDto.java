package com.example.demo.model.dto;

import com.example.demo.model.shop.Order;
import com.example.demo.model.shop.OrderDetail;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private Long customerId;
    private String customerName;
    private List<OrderDetailDto> orderDetailDtos;
    private String status;

    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.customerId = order.getCustomer().getId();
        this.customerName = order.getCustomer().getName();
        List<OrderDetailDto> dtos = new ArrayList<>();
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            OrderDetailDto dto = new OrderDetailDto(orderDetail);
            dtos.add(dto);
        }
        this.orderDetailDtos = dtos;
        this.status = order.getStatus();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<OrderDetailDto> getOrderDetailDtos() {
        return orderDetailDtos;
    }

    public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
        this.orderDetailDtos = orderDetailDtos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

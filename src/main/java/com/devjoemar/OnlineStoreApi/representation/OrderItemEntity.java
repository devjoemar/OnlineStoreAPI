package com.devjoemar.OnlineStoreApi.representation;

import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;
import java.util.List;

public class OrderItemEntity extends RepresentationModel<OrderItemEntity> {

    private Long userId;

    private String name;

    private List<ProductEntity> products;

    private LocalDate localDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }


    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


}

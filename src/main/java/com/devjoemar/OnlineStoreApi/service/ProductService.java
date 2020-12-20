package com.devjoemar.OnlineStoreApi.service;

import com.devjoemar.OnlineStoreApi.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(long id);

    Product createProduct(Product product);
}

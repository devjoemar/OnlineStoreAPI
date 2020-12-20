package com.devjoemar.OnlineStoreApi.repository;

import com.devjoemar.OnlineStoreApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
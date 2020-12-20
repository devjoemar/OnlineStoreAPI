package com.devjoemar.OnlineStoreApi.service;


import com.devjoemar.OnlineStoreApi.exception.ResourceNotFoundException;
import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product not found with id %d", id)));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}

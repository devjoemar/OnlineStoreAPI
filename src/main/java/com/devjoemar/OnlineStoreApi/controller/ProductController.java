package com.devjoemar.OnlineStoreApi.controller;

import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.representation.ProductEntity;
import com.devjoemar.OnlineStoreApi.representation.ProductModelAssembler;
import com.devjoemar.OnlineStoreApi.service.ProductService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    private final ProductModelAssembler productModelAssembler;

    public ProductController(ProductService productService, ProductModelAssembler productModelAssembler) {
        this.productService = productService;
        this.productModelAssembler = productModelAssembler;
    }

    @GetMapping
    public CollectionModel<ProductEntity> getProducts() {
        List<Product> products = productService.getAllProducts();

        List<ProductEntity> productEntities = products.stream()
                .map(productModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productEntities, linkTo(methodOn(ProductController.class).getProducts()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> doAddProduct(@RequestBody ProductEntity newProduct) {

        Product product = newProduct.toProduct();
        Product saved = productService.createProduct(product);

        ProductEntity res = productModelAssembler.toModel(saved);

        return ResponseEntity.created(res.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(product);
    }

    @GetMapping("/{id}")
    public EntityModel<Product> getOne(@PathVariable Long id) {

        Product product = productService.getProduct(id);

        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).getOne(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
    }
}

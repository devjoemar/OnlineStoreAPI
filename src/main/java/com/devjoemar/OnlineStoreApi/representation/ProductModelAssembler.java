package com.devjoemar.OnlineStoreApi.representation;


import com.devjoemar.OnlineStoreApi.controller.ProductController;
import com.devjoemar.OnlineStoreApi.model.Product;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, ProductEntity> {

    @Override
    public ProductEntity toModel(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setDescription(product.getDescription());
        productEntity.setName(product.getName());
        productEntity.setTags(product.getTags());
        productEntity.setPrice(product.getPrice());
        productEntity.add(linkTo(methodOn(ProductController.class).getOne(product.getId())).withSelfRel());
        productEntity.add(linkTo(methodOn(ProductController.class).getProducts()).withRel("products"));
        return productEntity;
    }
}

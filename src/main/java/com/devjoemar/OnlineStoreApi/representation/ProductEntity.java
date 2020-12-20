package com.devjoemar.OnlineStoreApi.representation;

import com.devjoemar.OnlineStoreApi.model.Product;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

public class ProductEntity extends RepresentationModel<ProductEntity> {

    private Long id;

    private String name;

    private BigDecimal price;

    private String description;

    private List<String> tags;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Product toProduct() {
        Product product = new Product();
        product.setName(this.name);
        product.setDescription(this.description);
        product.setTags(this.tags);
        product.setPrice(this.price);
        return product;
    }

}
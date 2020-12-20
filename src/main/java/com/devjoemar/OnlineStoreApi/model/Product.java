package com.devjoemar.OnlineStoreApi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 2243488232815821308L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    private String name;

    @Basic(optional = false)
    private String description;

    @ElementCollection
    private List<String> tags = new ArrayList<>();

    @Basic(optional = false)
    private BigDecimal price;

    public Product() {
    }

    public Product(String name, String description, List<String> tags, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.price = price;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id) &&
                name.equals(product.name) &&
                description.equals(product.description) &&
                tags.equals(product.tags) &&
                price.equals(product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, tags, price);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("description='" + description + "'")
                .add("tags=" + tags)
                .add("price=" + price)
                .toString();
    }
}

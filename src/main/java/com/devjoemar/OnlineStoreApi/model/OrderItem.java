package com.devjoemar.OnlineStoreApi.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
public class OrderItem implements Serializable {

    private static final long serialVersionUID = -1867288138641296018L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user")
    private User user;


    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;


    @Column(name = "local_date", columnDefinition = "DATE")
    private LocalDate localDate;

    public OrderItem() {

    }

    public OrderItem(User user, List<Product> products, LocalDate localDate) {
        this.user = user;
        this.products = products;
        this.localDate = localDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) &&
                Objects.equals(user, orderItem.user) &&
                Objects.equals(products, orderItem.products) &&
                Objects.equals(localDate, orderItem.localDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, products, localDate);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderItem.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("user=" + user)
                .add("products=" + products)
                .add("localDate=" + localDate)
                .toString();
    }
}

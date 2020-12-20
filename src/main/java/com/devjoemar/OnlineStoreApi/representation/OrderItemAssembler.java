package com.devjoemar.OnlineStoreApi.representation;

import com.devjoemar.OnlineStoreApi.controller.OrderController;
import com.devjoemar.OnlineStoreApi.model.OrderItem;
import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.model.User;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderItemAssembler implements RepresentationModelAssembler<OrderItem, OrderItemEntity> {

    @Override
    public OrderItemEntity toModel(OrderItem orderItem) {

        List<ProductEntity> productEntities = new ArrayList<>();
        List<Product> products = orderItem.getProducts();
        products.forEach(product -> {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(product.getId());
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setTags(product.getTags());
            productEntities.add(productEntity);
        });

        User user = orderItem.getUser();

        OrderItemEntity orderItemEntity = new OrderItemEntity();
        orderItemEntity.setUserId(user.getId());
        orderItemEntity.setName(user.getName());
        orderItemEntity.setProducts(productEntities);
        orderItemEntity.add(linkTo(methodOn(OrderController.class).getOrderByUser(user.getId())).withRel(IanaLinkRelations.RELATED));
        orderItemEntity.setLocalDate(orderItem.getLocalDate());
        return orderItemEntity;
    }
}

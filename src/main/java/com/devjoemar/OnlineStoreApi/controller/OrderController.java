package com.devjoemar.OnlineStoreApi.controller;

import com.devjoemar.OnlineStoreApi.model.OrderItem;
import com.devjoemar.OnlineStoreApi.representation.OrderItemAssembler;
import com.devjoemar.OnlineStoreApi.representation.OrderItemEntity;
import com.devjoemar.OnlineStoreApi.service.OrderService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    private final OrderItemAssembler orderItemAssembler;

    public OrderController(OrderService orderService, OrderItemAssembler orderItemAssembler) {
        this.orderService = orderService;
        this.orderItemAssembler = orderItemAssembler;
    }

    @GetMapping
    public CollectionModel<OrderItemEntity> getUserOrdersByDateRange(@RequestParam Long id,

                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     @RequestParam LocalDate fromDate,

                                                                     @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                                     @RequestParam LocalDate toDate) {

        List<OrderItem> orderItems = orderService.getUserOrdersByDateRange(id, fromDate, toDate);

        List<OrderItemEntity> productEntities = orderItems.stream()
                .map(orderItemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productEntities, linkTo(methodOn(OrderController.class).getOrderByUser(id))
                .withSelfRel());
    }

    @GetMapping("/user/{id}")
    public CollectionModel<OrderItemEntity> getOrderByUser(@PathVariable Long id) {
        List<OrderItem> orderItems = orderService.getOrdersByUserId(id);

        List<OrderItemEntity> productEntities = orderItems.stream()
                .map(orderItemAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productEntities, linkTo(methodOn(OrderController.class).getOrderByUser(id))
                .withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> doAddOrder(@RequestBody OrderItemEntity orderItemEntity) {

        OrderItem saved = orderService.createOrder(orderItemEntity);

        OrderItemEntity res = new OrderItemAssembler().toModel(saved);

        return ResponseEntity.created(res.getRequiredLink(IanaLinkRelations.RELATED).toUri())
                .body(res);
    }
}

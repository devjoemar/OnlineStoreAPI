package com.devjoemar.OnlineStoreApi.service;

import com.devjoemar.OnlineStoreApi.model.OrderItem;
import com.devjoemar.OnlineStoreApi.model.Product;
import com.devjoemar.OnlineStoreApi.model.User;
import com.devjoemar.OnlineStoreApi.repository.OrderRepository;
import com.devjoemar.OnlineStoreApi.representation.OrderItemEntity;
import com.devjoemar.OnlineStoreApi.representation.ProductEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final UserService userService;

    private final ProductService productService;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(UserService userService, ProductService productService, OrderRepository orderRepository) {
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderItem> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderItem> getOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUser(userId);
    }

    @Override
    public List<OrderItem> getUserOrdersByDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        return orderRepository.findUserOrdersByDateRange(userId, fromDate, toDate);
    }

    @Override
    public OrderItem createOrder(OrderItemEntity orderItemEntity) {
        Long userId = orderItemEntity.getUserId();
        User user = userService.getUser(userId);

        List<Product> products = new ArrayList<>();

        List<ProductEntity> productEntities = orderItemEntity.getProducts();
        productEntities.forEach(productEntity ->
                products.add(productService.getProduct(productEntity.getId())));

        OrderItem orderItem = new OrderItem(user, products, orderItemEntity.getLocalDate());
        return orderRepository.save(orderItem);
    }

}

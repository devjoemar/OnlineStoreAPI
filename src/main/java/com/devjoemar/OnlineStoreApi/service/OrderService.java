package com.devjoemar.OnlineStoreApi.service;

import com.devjoemar.OnlineStoreApi.model.OrderItem;
import com.devjoemar.OnlineStoreApi.representation.OrderItemEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<OrderItem> getAllOrders();

    List<OrderItem> getOrdersByUserId(Long userId);

    OrderItem createOrder(OrderItemEntity orderItemEntity);

    List<OrderItem> getUserOrdersByDateRange(Long userId, LocalDate fromDate, LocalDate toDate);
}

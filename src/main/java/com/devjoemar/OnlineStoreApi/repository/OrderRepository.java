package com.devjoemar.OnlineStoreApi.repository;

import com.devjoemar.OnlineStoreApi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o FROM OrderItem o JOIN o.user c WHERE (o.user.id = :userId)")
    List<OrderItem> findOrdersByUser(@Param("userId") Long userId);

    @Query("SELECT o FROM OrderItem o JOIN o.user c WHERE (o.user.id = :userId) AND local_date >= :fromDate AND local_date <= :toDate")
    List<OrderItem> findUserOrdersByDateRange(@Param("userId") Long userId,
                                              @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
